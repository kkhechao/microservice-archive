package com.zqkh.archive.context.appservice.impl;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.archive.context.appservice.impl.domain.BasicArchive;
import com.zqkh.archive.context.appservice.impl.domain.BodyInfo;
import com.zqkh.archive.context.appservice.impl.domain.FamilyMember;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.FamilyMemberDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.query.BasicArchiveQuery;
import com.zqkh.archive.context.appservice.impl.domain.repository.query.FamilyMemberQuery;
import com.zqkh.archive.context.appservice.inter.FamilyMemberService;
import com.zqkh.archive.context.uitl.BillNumberGenerate;
import com.zqkh.archive.context.uitl.CardUtil;
import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.archive.feign.dto.BodyInfoDto;
import com.zqkh.archive.feign.dto.FamilyMemberDto;
import com.zqkh.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hty
 * @create 2018-01-30 17:06
 **/
@AppService
@Slf4j
public class FamilyMemberServiceImpl implements FamilyMemberService {

    public static final String APPELLATION = "自己";

    public static final String HEIGHT = "175";

    public static final String IDCARD_GET_SEX_FAILED = "身份证读取性别失败";

    public static final String WEIGHT = "60";

    public static final String BIRTH_DATE = "1985-1-1";

    private static final String AVATAR = "http://www.qqzhi.com/uploadpic/2015-01-27/121838355.jpg";
    public static final String IDCARD_GET_BIRTHDAY_FAILED = "根据身份证获取生日失败";

    private ConstructLoader<FamilyMember> familyMemberConstructLoader = new ConstructLoader<>(FamilyMember.class);

    private ConstructLoader<BasicArchive> basicArchiveConstructLoader = new ConstructLoader<>(BasicArchive.class);

    private EntityLoader<BasicArchive> basicArchiveEntityLoader = new RepositoryLoader<>(BasicArchive.class);

    private EntityLoader<FamilyMember> familyMemberEntityLoader = new RepositoryLoader<>(FamilyMember.class);



    FamilyMemberQuery familyMemberQuery;


    BasicArchiveQuery basicArchiveQuery;

    @Autowired
    DozerBeanMapper dozerBeanMapper;


    @Override
    public List<FamilyMemberDto> getFamilyMemberList(String createId) {
        List<String> familyMemberIdByCreateId = familyMemberQuery.getFamilyMemberIdByCreateId(createId);
        if(ObjectUtils.isEmpty(familyMemberIdByCreateId)){
           return Collections.EMPTY_LIST;
        }

        List<FamilyMemberDto> familyMemberDtoList=familyMemberIdByCreateId.stream().map(n->{
            FamilyMember familyMember = familyMemberEntityLoader.create(StringIdentifier.valueOf(n));
            if(ObjectUtils.isEmpty(familyMember)){
                return null;
            }
            FamilyMemberDto familyMemberDto=new FamilyMemberDto();
            familyMemberDto.setId(familyMember.getId().toValue());
            familyMemberDto.setAvatar(familyMember.getAvatar());
            familyMemberDto.setAppellation(familyMember.getAppellation());
            //查询基本档案
            String basicId = basicArchiveQuery.selectBasicArchiveIdByMemberId(n);
            if(!ObjectUtils.isEmpty(basicId)){
                BasicArchive basicArchive = basicArchiveEntityLoader.create(StringIdentifier.valueOf(basicId));
                familyMemberDto.setBloodType(basicArchive.getBloodType().toString());
                familyMemberDto.setIdCard(basicArchive.getIdCard());
                familyMemberDto.setBodyInfo(dozerBeanMapper.map(basicArchive.getBodyInfo(), BodyInfoDto.class));
                familyMemberDto.setName(basicArchive.getName());
                familyMemberDto.setSex(basicArchive.getSex().toString());
            }

            return familyMemberDto;
        }).filter(n->!ObjectUtils.isEmpty(n)).collect(Collectors.toList());

        return familyMemberDtoList;
    }

    /**
     * 创建自己档案 返回列表
     *
     * @param createrId
     * @return
     */
    private List<FamilyMemberDto> getFamilyMemberDtos(String createrId) {
        List<FamilyMemberDto> familyMemberDtoList = new ArrayList<>();
        BasicArchiveDto basicArchiveDto = new BasicArchiveDto();
        basicArchiveDto.setAppellation(APPELLATION);
        basicArchiveDto.setCreater(createrId);
        String id = initbasicArchive(basicArchiveDto);

        FamilyMemberDto familyMemberDto = new FamilyMemberDto();
        familyMemberDto.setId(id);
        familyMemberDto.setAppellation(APPELLATION);
        familyMemberDto.setAvatar(AVATAR);
        familyMemberDto.setSex(BasicArchive.Sex.UN_KNOW.toString());
        familyMemberDto.setBloodType(BasicArchive.BloodType.UN_KNOW.toString());
        familyMemberDto.setBodyInfo(new BodyInfoDto(HEIGHT, WEIGHT, BIRTH_DATE));
        familyMemberDtoList.add(familyMemberDto);
        return familyMemberDtoList;
    }

    @Override
    public void removeFamilyMemberById(String memberId) {
        FamilyMember familyMember = familyMemberEntityLoader.create(StringIdentifier.valueOf(memberId));
        familyMember.delete();
    }

    @Override
    public void changeAppellation(FamilyMemberDto familyMemberDto) {
        updateAppellation(familyMemberDto);
    }

    private void updateAppellation(FamilyMemberDto familyMemberDto) {
        FamilyMember familyMember = familyMemberEntityLoader.create(StringIdentifier.valueOf(familyMemberDto.getId()));
        familyMember.changeAppellation(familyMemberDto.getAppellation());
    }

    @Override
    public void changeAvatar(FamilyMemberDto familyMemberDto) {
        FamilyMember familyMember = familyMemberEntityLoader.create(StringIdentifier.valueOf(familyMemberDto.getId()));
        familyMember.changeAppellation(familyMemberDto.getAppellation());
    }

    private void updateAvatar(FamilyMemberDto familyMemberDto) {
        FamilyMember familyMember = familyMemberEntityLoader.create(StringIdentifier.valueOf(familyMemberDto.getId()));
        familyMember.changeAvatar(familyMemberDto.getAvatar());
    }


    /**
     * 基本档案添加
     *
     * @param basicArchiveDto
     */
    @Override
    public BasicArchiveDto addBasicArchive(BasicArchiveDto basicArchiveDto) {
        initbasicArchive(basicArchiveDto);
        return basicArchiveDto;
    }

    private String initbasicArchive(BasicArchiveDto basicArchiveDto) {
        Identifier id = new StringIdentifier(BillNumberGenerate.getBillNo());
        String idValue = id.toValue();
        FamilyMember familyMember = familyMemberConstructLoader.create(id);
        familyMember.init(basicArchiveDto.getCreater(), basicArchiveDto.getAppellation(), null, basicArchiveDto.getAvatar());
        Identifier basicId = id;
        String idCard = basicArchiveDto.getIdCard();
        String birthday = "1985-01-01";
        if (StringUtils.isNotBlank(idCard)) {
            try {
                birthday = CardUtil.getBirthdayFormat(idCard);
            }catch (Exception e){
                log.warn(IDCARD_GET_BIRTHDAY_FAILED);
            }
        }
        BasicArchive basicArchive = basicArchiveConstructLoader.create(basicId);
        BodyInfo bodyInfo = new BodyInfo();
        if (basicArchiveDto.getBodyInfo() != null) {
            bodyInfo.init(basicArchiveDto.getBodyInfo().getHeight(), basicArchiveDto.getBodyInfo().getWeight(), birthday);
        }
        BasicArchive.Sex sex = BasicArchive.Sex.UN_KNOW;
        try {
            sex = CardUtil.isGirl(idCard) ? BasicArchive.Sex.WOMAN : BasicArchive.Sex.MAN;
        } catch (Exception e) {
            log.warn(IDCARD_GET_SEX_FAILED);
        }

        basicArchive.init(BasicArchive.BloodType.getBloodType(basicArchiveDto.getBloodType()), basicArchiveDto.getProfession(), sex,
                idCard, basicArchiveDto.getName(), basicArchiveDto.getNativePlace(), basicArchiveDto.getEthnic(), familyMember.getId().toString(), bodyInfo);
        basicArchiveDto.setId(idValue);
        basicArchiveDto.setFamilyMember(familyMember.getId().toString());
        basicArchiveDto.setArchiveNo(idValue);

        return idValue;
    }

    @Override
    public BasicArchiveDto updateBasicArchive(BasicArchiveDto basicArchiveDto) {

        BasicArchive basicArchive = basicArchiveEntityLoader.create(new StringIdentifier(basicArchiveDto.getId()));
        String familyMemberId = basicArchive.getFamilyMember();
        if (basicArchiveDto != null) {
            if (basicArchiveDto.getBodyInfo() != null) {
                BodyInfo bodyInfo = new BodyInfo();
                bodyInfo.init(basicArchiveDto.getBodyInfo().getHeight(), basicArchiveDto.getBodyInfo().getWeight(), basicArchiveDto.getBodyInfo().getBirthDate());
                basicArchive.changeBodyInfo(bodyInfo);
            }
        }
        String avatar = basicArchiveDto.getAvatar();
        if (avatar != null) {
            FamilyMemberDto familyMemberDto = new FamilyMemberDto();
            familyMemberDto.setId(familyMemberId);
            familyMemberDto.setAvatar(avatar);
            updateAvatar(familyMemberDto);
        }
        String appellation = basicArchiveDto.getAppellation();
        if (appellation != null) {
            FamilyMemberDto familyMemberDto = new FamilyMemberDto();
            familyMemberDto.setId(familyMemberId);
            familyMemberDto.setAppellation(appellation);
            updateAppellation(familyMemberDto);
        }


        String ethnic = basicArchiveDto.getEthnic();
        if (StringUtils.isNotEmpty(ethnic)) {
            basicArchive.changeEthnic(ethnic);
        }
        String name = basicArchiveDto.getName();
        if (StringUtils.isNotEmpty(name)) {
            basicArchive.changeName(name);
        }
        String nativePlace = basicArchiveDto.getNativePlace();
        if (StringUtils.isNotEmpty(nativePlace)) {
            basicArchive.changeNativePlace(nativePlace);
        }
        String profession = basicArchiveDto.getProfession();
        if (StringUtils.isNotEmpty(profession)) {
            basicArchive.changeProfession(profession);
        }
        String bloodType = basicArchiveDto.getBloodType();
        if (StringUtils.isNotEmpty(bloodType)) {
            basicArchive.changeBloodType(bloodType);
        }
        String idCard = basicArchiveDto.getIdCard();
        if (StringUtils.isNotEmpty(idCard)) {
            BasicArchive.Sex sex = BasicArchive.Sex.UN_KNOW;
            try {
                sex = CardUtil.isGirl(idCard) ? BasicArchive.Sex.WOMAN : BasicArchive.Sex.MAN;
            } catch (Exception e) {
                log.warn(IDCARD_GET_SEX_FAILED);
            }
            basicArchive.changeSex(sex.toString());
            basicArchive.changeIdCard(idCard);
        }
        BasicArchiveDto map = dozerBeanMapper.map(basicArchive, BasicArchiveDto.class);
        FamilyMember familyMember = familyMemberEntityLoader.create(new StringIdentifier(familyMemberId));
        map.setAvatar(familyMember.getAvatar());
        map.setAppellation(familyMember.getAppellation());
        map.setCreater(familyMember.getCreater());
        map.setArchiveNo(familyMember.getId().toValue());
        return map;
    }

    @Override
    public BasicArchiveDto getFamilyMemberDetail(String memberId) {
        FamilyMember familyMember = familyMemberEntityLoader.create(StringIdentifier.valueOf(memberId));
        if(ObjectUtils.isEmpty(familyMember)){
            throw new BusinessException("家庭成员不存在");
        }
        BasicArchive basicArchive = basicArchiveEntityLoader.create(StringIdentifier.valueOf(basicArchiveQuery.selectBasicArchiveIdByMemberId(memberId)));
        BasicArchiveDto basicArchiveDto = dozerBeanMapper.map(basicArchive, BasicArchiveDto.class);
        basicArchiveDto.setAppellation(familyMember.getAppellation());
        basicArchiveDto.setAvatar(familyMember.getAvatar());
        basicArchiveDto.setCreater(familyMember.getCreater());
        basicArchiveDto.setArchiveNo(familyMember.getId().toString());
        return basicArchiveDto;
    }

    @Override
    public void addBasicArchiveFromMQ(BasicArchiveDto basicArchiveDto) {
        List<FamilyMemberDmo> familyMemberDmos = familyMemberQuery.selectFamilyMemberByCreaterId(basicArchiveDto.getCreater());
        if (familyMemberDmos != null && familyMemberDmos.size() > 0) {
            //没有自己就创建自己
            if (!familyMemberDmos.stream().anyMatch(f -> f.getAppellation().equals(APPELLATION))) {
                initbasicArchive(basicArchiveDto);
            }
            ;
        } else {
            //没有成员就初始化
            initbasicArchive(basicArchiveDto);
        }
    }

    @Override
    public List<String> searchFamilyIdByName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return null;
        }
        return familyMemberQuery.searchFamilyIdByName(name);
    }
}
