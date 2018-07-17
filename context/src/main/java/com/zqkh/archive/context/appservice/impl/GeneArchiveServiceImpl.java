package com.zqkh.archive.context.appservice.impl;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.event.EventBus;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.archive.context.appservice.impl.domain.*;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.FamilyMemberDmoMapper;
import com.zqkh.archive.context.appservice.impl.domain.repository.query.BasicArchiveQuery;
import com.zqkh.archive.context.appservice.impl.domain.repository.query.GeneArchiveQuery;
import com.zqkh.archive.context.appservice.inter.GeneArchiveService;
import com.zqkh.archive.feign.dto.gene.*;
import com.zqkh.archive.feign.vo.AddGeneArchiveVo;
import com.zqkh.archive.feign.vo.AuditGeneArchiveVo;
import com.zqkh.archive.feign.vo.BatchAuditGeneArchiveVo;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.file.dto.ResourceInfoDto;
import com.zqkh.file.feign.FileFeign;
import com.zqkh.gene.event.dto.GeneOrderStatusEventDto;
import com.zqkh.gene.feign.DiseaseClient;
import com.zqkh.gene.feign.GeneOrderClient;
import com.zqkh.gene.feign.dto.genepackage.DiseaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 东来
 * @create 2018/1/31 0031
 */
@AppService
@Slf4j
public class GeneArchiveServiceImpl implements GeneArchiveService {


    private EntityLoader<GeneArchive> geneArchiveEntityLoader = new RepositoryLoader<>(GeneArchive.class);


    private ConstructLoader<GeneArchive> geneArchiveConstructLoader = new ConstructLoader<>(GeneArchive.class);


    private EntityLoader<BasicArchive> basicArchiveEntityLoader = new RepositoryLoader<>(BasicArchive.class);

    private EntityLoader<FamilyMember> familyMemberEntityLoader = new RepositoryLoader<>(FamilyMember.class);


    @Resource
    protected GeneOrderClient geneOrderClient;

    @Resource
    private GeneArchiveQuery geneArchiveQuery;


    @Resource
    private BasicArchiveQuery basicArchiveQuery;




    @Resource
    private DiseaseClient diseaseClient;

    @Resource
    private FileFeign fileFeign;

    @Override
    public GeneArchiveInfoDto getGeneArchiveInfoByOrderId(String geneOrderId) {

        String id = geneArchiveQuery.getPrimaryKeyByOrderId(geneOrderId);

        GeneArchive geneArchive = geneArchiveEntityLoader.create(StringIdentifier.valueOf(id));
        if (ObjectUtils.isEmpty(geneArchive)) {
            throw new BusinessException("基因档案不存在", geneOrderId);
        }
        GeneArchiveInfoDto geneArchiveInfoDto = new GeneArchiveInfoDto();
        /**
         * 获取基本档案信息
         */
        geneArchiveInfoDto.setPersonDatum(this.initPersonDatum(geneArchive.getFamilyMember()));
        geneArchiveInfoDto.setPackageName(geneArchive.getName());
        geneArchiveInfoDto.setSampleId(geneArchive.getSampleId());
        geneArchiveInfoDto.setSampleType(geneArchive.getSampleType());
        geneArchiveInfoDto.setReportTime(geneArchive.getReportDate());
        geneArchiveInfoDto.setReceiveTime(geneArchive.getReceiveDate());
        geneArchiveInfoDto.setAuditTime(geneArchive.getAuditTime());


        FamilyMember familyMember=familyMemberEntityLoader.create(StringIdentifier.valueOf(geneArchive.getFamilyMember()));
        if(!ObjectUtils.isEmpty(familyMember)){
            geneArchiveInfoDto.setHeadUrl(familyMember.getAvatar());
        }

        String basicArchiveId= basicArchiveQuery.selectBasicArchiveIdByMemberId(geneArchive.getFamilyMember());
        if(!ObjectUtils.isEmpty(basicArchiveId)){
           BasicArchive basicArchive= basicArchiveEntityLoader.create(StringIdentifier.valueOf(basicArchiveId));
           if(!ObjectUtils.isEmpty(basicArchive)){
               geneArchiveInfoDto.setNickName(basicArchive.getName());
           }
        }


        Set<String> resourceId = new HashSet<>();
        geneArchive.getDetectItemList().forEach(n -> {
            n.getLocusInfo().forEach(n1 -> {
                resourceId.add(n1.getLocusImageId());
            });
        });

        List<ResourceInfoDto> resourceInfoDtos = fileFeign.getResourceInfo(new ArrayList<>(resourceId));
        /**
         * 获取疾病和位点位图
         */
        List<DetectItemDto> detectItem = new ArrayList<>();
        log.debug("档案编号为{}的疾病信息为:{}",geneArchive.getId(),geneArchive.getDetectItemList());
        geneArchive.getDetectItemList().forEach(n -> {
            log.debug("疾病编号为:{}的疾病信息为:{}",n.getDiseaseId(),n.toString());
            DiseaseDto diseaseDto = diseaseClient.getDiseaseDetailById(n.getDiseaseId());
            DetectItemDto detectItemDto = new DetectItemDto();
            detectItemDto.setId(n.getDiseaseId());
            detectItemDto.setIcoUrl( fileFeign.getUrlByMd5(diseaseDto.getIcon()));
            detectItemDto.setRisk(DetectItemDto.Risk.getRisk(n.getRisk().name()));
            detectItemDto.setName(diseaseDto.getName());
            detectItemDto.setEnglishName(diseaseDto.getEnglishName());
            detectItemDto.setSymptom(diseaseDto.getSymptom());
            detectItemDto.setHealthAdvice(diseaseDto.getHealthAdvice());
            detectItemDto.setExamination(diseaseDto.getExamination());
            detectItemDto.setLocusInfo(n.getLocusInfo().stream().map(m -> {
                LocusInfoDto locusInfoDto = new LocusInfoDto();
                BeanUtils.copyProperties(m, locusInfoDto);
                if (!ObjectUtils.isEmpty(m.getLocusImageId())) {
                    if (!ObjectUtils.isEmpty(resourceInfoDtos)) {
                        resourceInfoDtos.forEach(r -> {
                            if (r.getId().equals(m.getLocusImageId())) {
                                locusInfoDto.setLocusImageUrl(r.getUrl());
                            }
                        });
                    }
                }
                return locusInfoDto;
            }).collect(Collectors.toList()));
            detectItem.add(detectItemDto);
            geneArchiveInfoDto.setDetectItem(detectItem);

        });
        /**
         * 对疾病患病风险进行分组
         */
        Map<DetectItemDto.Risk, List<DetectItemDto>> group = geneArchiveInfoDto.getDetectItem().stream().collect(Collectors.groupingBy(DetectItemDto::getRisk));
        Set<RiskGroup> riskGroups = new HashSet<>();
        group.entrySet().stream().forEach(n -> {
            RiskGroup riskGroup = new RiskGroup();
            riskGroup.setRisk(RiskGroup.Risk.getRisk(n.getKey().name()));
            riskGroup.setDiseaseName(n.getValue().stream().map(n1 -> n1.getName()).collect(Collectors.toSet()));
            riskGroups.add(riskGroup);
        });
        geneArchiveInfoDto.setRiskGroup(riskGroups);
        return geneArchiveInfoDto;
    }


    @Override
    public void batchAuditGeneArchive(BatchAuditGeneArchiveVo batchAuditGeneArchiveVo) {
        if (ObjectUtils.isEmpty(batchAuditGeneArchiveVo)) {
            throw new BusinessException("审核基因报告失败,参数未传", batchAuditGeneArchiveVo);
        }
        if (ObjectUtils.isEmpty(batchAuditGeneArchiveVo.getGeneOrderId())) {
            throw new BusinessException("审核基因报告失败,基因订单编号未传", batchAuditGeneArchiveVo);
        }

        batchAuditGeneArchiveVo.getGeneOrderId().forEach(n -> {
            String geneArchiveId = geneArchiveQuery.getPrimaryKeyByOrderId(n);
            if (!ObjectUtils.isEmpty(geneArchiveId)) {
                //根据基因订单编号查询基因档案编号
                GeneArchive geneArchive = geneArchiveEntityLoader.create(StringIdentifier.valueOf(geneArchiveId));
                if (!ObjectUtils.isEmpty(geneArchive)) {
                    geneArchive.auditGeneArchive();
                    GeneOrderStatusEventDto geneOrderStatusEventDto = new GeneOrderStatusEventDto();
                    geneOrderStatusEventDto.setId(n);
                    geneOrderStatusEventDto.setStatus(GeneOrderStatusEventDto.Status.DONE);
                    EventBus.publish(GeneOrderStatusEventDto.EVENT_NAME, geneOrderStatusEventDto);
                }
            }
        });
    }


    @Override
    public void auditGeneArchive(AuditGeneArchiveVo auditGeneArchiveVo) {
        if (ObjectUtils.isEmpty(auditGeneArchiveVo)) {
            throw new BusinessException("参数为空");
        }

        if (ObjectUtils.isEmpty(auditGeneArchiveVo.getGeneOrderId())) {
            throw new BusinessException("审核基因报告失败,基因订单编号未传", auditGeneArchiveVo);
        }

        String geneArchiveId = geneArchiveQuery.getPrimaryKeyByOrderId(auditGeneArchiveVo.getGeneOrderId());
        if (ObjectUtils.isEmpty(geneArchiveId)) {
            throw new BusinessException("基因档案不存在");
        }

        GeneArchive geneArchive = geneArchiveEntityLoader.create(StringIdentifier.valueOf(geneArchiveId));
        if (ObjectUtils.isEmpty(geneArchive)) {
            throw new BusinessException("基因档案不存在");
        }

        geneArchive.auditGeneArchive();
        if (!ObjectUtils.isEmpty(auditGeneArchiveVo.getDiseaseRisk())) {
            auditGeneArchiveVo.getDiseaseRisk().forEach(n -> {

                geneArchive.getDetectItemList().forEach(n1 -> {
                    if (n.getId().equals(n1.getDiseaseId())) {
                        n1.setRisk(DetectItem.Risk.getRisk(n.getRisk().name()));
                        /*geneArchive.addDetectItem(n1);*/
                    }
                });
            });
        }

        geneArchive.auditGeneArchive();
        GeneOrderStatusEventDto geneOrderStatusEventDto = new GeneOrderStatusEventDto();
        geneOrderStatusEventDto.setId(auditGeneArchiveVo.getGeneOrderId());
        geneOrderStatusEventDto.setStatus(GeneOrderStatusEventDto.Status.DONE);
        EventBus.publish(GeneOrderStatusEventDto.EVENT_NAME, geneOrderStatusEventDto);
    }

    @Override
    public void addGeneArchive(AddGeneArchiveVo addGeneArchiveVo) {
        if (ObjectUtils.isEmpty(addGeneArchiveVo)) {
            throw new BusinessException("参数为空");
        }

        GeneArchive geneArchive = null;
        String geneArchiveId = geneArchiveQuery.getPrimaryKeyBySampleIdAndFamilyMemberId(addGeneArchiveVo.getSampleId(), addGeneArchiveVo.getFamilyMemberId());
        if (!ObjectUtils.isEmpty(geneArchiveId)) {
            geneArchive = geneArchiveEntityLoader.create(StringIdentifier.valueOf(geneArchiveId));
            geneArchive.edit(addGeneArchiveVo.getPackageName(), addGeneArchiveVo.getSampleType(), addGeneArchiveVo.getFamilyMemberId(), addGeneArchiveVo.getGeneOrderId(), addGeneArchiveVo.getSampleId(),addGeneArchiveVo.getDetectionDataJson());
        } else {
            geneArchive = geneArchiveConstructLoader.create(IdGenerator.getInstance().generate(GeneArchive.class));
            geneArchive.init(addGeneArchiveVo.getPackageName(), addGeneArchiveVo.getSampleType(), addGeneArchiveVo.getFamilyMemberId(), addGeneArchiveVo.getGeneOrderId(), addGeneArchiveVo.getSampleId(),addGeneArchiveVo.getDetectionDataJson());

        }

        for (int i = 0; i < addGeneArchiveVo.getDetect().size(); i++) {
            DetectItem detectItem = new DetectItem();
            detectItem.setRisk(DetectItem.Risk.getRisk(addGeneArchiveVo.getDetect().get(i).getRisk().name()));
            detectItem.setDiseaseId(addGeneArchiveVo.getDetect().get(i).getDiseaseId());
            List<LocusInfo> locusInfos = addGeneArchiveVo.getDetect().get(i).getLocusInfo().stream().map(m -> {
                LocusInfo locusInfo = new LocusInfo();
                BeanUtils.copyProperties(m, locusInfo);
                return locusInfo;
            }).collect(Collectors.toList());
            detectItem.setLocusInfo(locusInfos);
            geneArchive.addDetectItem(detectItem);
        }

        GeneOrderStatusEventDto geneOrderStatusEventDto = new GeneOrderStatusEventDto();
        geneOrderStatusEventDto.setId(addGeneArchiveVo.getGeneOrderId());
        geneOrderStatusEventDto.setStatus(GeneOrderStatusEventDto.Status.WAIT_AUDITING);
        EventBus.publish(GeneOrderStatusEventDto.EVENT_NAME, geneOrderStatusEventDto);


    }

    private PersonDatumDto initPersonDatum(String familyMemberId) {



        String basicArchiveId=basicArchiveQuery.selectBasicArchiveIdByMemberId(familyMemberId);
        /**
         * 获取基本档案信息
         */
        BasicArchive basicArchive = basicArchiveEntityLoader.create(StringIdentifier.valueOf(basicArchiveId));
        if (!ObjectUtils.isEmpty(basicArchive)) {
            PersonDatumDto personDatumDto = new PersonDatumDto();
            personDatumDto.setBloodType(basicArchive.getBloodType().name());
            personDatumDto.setGender(basicArchive.getSex().name());
            personDatumDto.setName(basicArchive.getName());
            personDatumDto.setId(basicArchive.getFamilyMember());
            if (!ObjectUtils.isEmpty(basicArchive.getBodyInfo())) {
                SignsDto signsDto = new SignsDto();
                signsDto.setBirthDate(basicArchive.getBodyInfo().getBirthDate());
                signsDto.setHeight(basicArchive.getBodyInfo().getHeight());
                signsDto.setWeight(basicArchive.getBodyInfo().getWeight());
                personDatumDto.setSigns(signsDto);
            }
            personDatumDto.setNation(basicArchive.getEthnic());
            personDatumDto.setOccupation(basicArchive.getProfession());
            return personDatumDto;
        }
        return null;
    }


}
