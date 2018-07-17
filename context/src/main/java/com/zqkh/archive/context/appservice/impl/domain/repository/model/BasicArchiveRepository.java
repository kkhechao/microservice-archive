package com.zqkh.archive.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.zqkh.archive.context.appservice.impl.domain.BasicArchive;
import com.zqkh.archive.context.appservice.impl.domain.BodyInfo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.BasicArchiveDmoMapper;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.BodyInfoDmoMapper;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.BasicArchiveDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.BodyInfoDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wenjie
 * @date 2017/12/7 0007 14:53
 */
@Repository("BasicArchive_Repository")
public class BasicArchiveRepository implements IRepository<BasicArchive>{

    @Autowired
    private BasicArchiveDmoMapper basicArchiveDmoMapper;

    @Autowired
    private BodyInfoDmoMapper bodyInfoDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public BasicArchive getEntityById(Identifier id, EntityLoader<BasicArchive> entityLoader) {
        BasicArchiveDmo basicArchiveDmo = basicArchiveDmoMapper.selectByPrimaryKey(id.toValue());
        if (basicArchiveDmo == null) {
            return null;
        }

        BasicArchive basicArchive = entityLoader.create(id);
        modelMapper.map(basicArchiveDmo, basicArchive);

        String idValue = id.toValue();

        BodyInfoDmo bodyInfoDmo = bodyInfoDmoMapper.selectByBasicArchiveId(idValue);
        if (bodyInfoDmo != null) {
            EntityObjectUtils.setValue(BasicArchive.class, basicArchive, "bodyInfo", modelMapper.map(bodyInfoDmo, BodyInfo.class));
        }


        return basicArchive;
    }

    @Override
    public void save(BasicArchive basicArchive) {
        if (basicArchive == null) {
            return;
        }
        BasicArchiveDmo basicArchiveDmo = modelMapper.map(basicArchive, BasicArchiveDmo.class);
        if(basicArchiveDmoMapper.updateByPrimaryKey(basicArchiveDmo) == 0){
            basicArchiveDmoMapper.insert(basicArchiveDmo);
        }

        String basicArchiveIdValue = basicArchive.getId().toValue();


        BodyInfo bodyInfo = basicArchive.getBodyInfo();
        if (bodyInfo != null) {
            BodyInfoDmo bodyInfoDmo = modelMapper.map(bodyInfo, BodyInfoDmo.class);
            bodyInfoDmo.setBasicArchiveId(basicArchiveIdValue);
            if(bodyInfoDmoMapper.update(bodyInfoDmo) == 0) {
                bodyInfoDmoMapper.insert(bodyInfoDmo);
            }
        }

    }

    @Override
    public void remove(BasicArchive basicArchive) {
        if (basicArchive == null) {
            return;
        }
        basicArchiveDmoMapper.deleteByPrimaryKey(basicArchive.getId().toValue());
    }
}
