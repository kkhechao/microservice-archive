package com.zqkh.archive.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.archive.context.appservice.impl.domain.DetectItem;
import com.zqkh.archive.context.appservice.impl.domain.GeneArchive;
import com.zqkh.archive.context.appservice.impl.domain.LocusInfo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.DetectItemDmoMapper;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.GeneArchiveDmoMapper;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.DetectItemDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.DetectItemDmoExample;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.GeneArchiveDmoExample;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.GeneArchiveDmoWithBLOBs;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenjie
 * @date 2017/12/7 0007 14:53
 */
@Repository("GeneArchive_Repository")
public class GeneArchiveRepository implements IRepository<GeneArchive> {

    public static final String DETECT_ITEM_LIST_FIELD_NAME = "detectItemList";
    @Autowired
    private GeneArchiveDmoMapper geneArchiveDmoMapper;

    @Autowired
    private DetectItemDmoMapper detectItemDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public GeneArchive getEntityById(Identifier id, EntityLoader<GeneArchive> entityLoader) {
        GeneArchiveDmoWithBLOBs geneArchiveDmo = geneArchiveDmoMapper.selectByPrimaryKey(id.toValue());
        if (geneArchiveDmo == null) {
            return null;
        }

        GeneArchive geneArchive = entityLoader.create(id);
        modelMapper.map(geneArchiveDmo, geneArchive);

        String idValue = id.toValue();

        List<DetectItemDmo> detectItemDmoList = detectItemDmoMapper.selectByGeneArchiveId(idValue);


        if (detectItemDmoList != null) {
            EntityObjectUtils.setValue(GeneArchive.class, geneArchive, DETECT_ITEM_LIST_FIELD_NAME, detectItemDmoList.stream().map(p ->
                    {
                        DetectItem detectItem = new DetectItem();

                        detectItem.setDiseaseId(p.getDisease());
                        detectItem.setRisk(DetectItem.Risk.getRisk(p.getRisk()));
                        List<LocusInfo> locusInfo = null;
                        if (!ObjectUtils.isEmpty(p.getLocusInfo())) {
                            locusInfo = JsonUtils.toListObj(p.getLocusInfo(), LocusInfo.class);
                        }
                        detectItem.setLocusInfo(locusInfo);
                        return detectItem;
                    }

            ).collect(Collectors.toList()));
        }

        return geneArchive;
    }

    @Override
    public void save(GeneArchive geneArchive) {
        if (geneArchive == null) {
            return;
        }
        GeneArchiveDmoWithBLOBs geneArchiveDmo = modelMapper.map(geneArchive, GeneArchiveDmoWithBLOBs.class);
        GeneArchiveDmoExample geneArchiveDmoExample = new GeneArchiveDmoExample();
        geneArchiveDmoExample.createCriteria().andOrderIdEqualTo(geneArchive.getOrderId());
        geneArchiveDmoMapper.deleteByExample(geneArchiveDmoExample);
        geneArchiveDmoMapper.insert(geneArchiveDmo);
        String geneArchiveIdValue = geneArchive.getId().toValue();
        List<DetectItem> detectItemList = geneArchive.getDetectItemList();

        //值对象更改
        DetectItemDmoExample detectItemDmoExample = new DetectItemDmoExample();
        detectItemDmoExample.createCriteria().andGeneArchiveIdEqualTo(geneArchiveIdValue);
        detectItemDmoMapper.deleteByExample(detectItemDmoExample);
        List<DetectItemDmo> detectItemDmoList = detectItemList.stream().map(p -> {
            DetectItemDmo detectItemDmo = modelMapper.map(p, DetectItemDmo.class);
            if (!ObjectUtils.isEmpty(p.getLocusInfo())) {
                detectItemDmo.setLocusInfo(JsonUtils.toJsonString(p.getLocusInfo()));
            }
            return detectItemDmo;
        }).collect(Collectors.toList());
        detectItemDmoMapper.batchInsert(geneArchiveIdValue, detectItemDmoList);


    }

    @Override
    public void remove(GeneArchive geneArchive) {
        if (geneArchive == null) {
            return;
        }
        geneArchiveDmoMapper.deleteByPrimaryKey(geneArchive.getId().toValue());
    }
}
