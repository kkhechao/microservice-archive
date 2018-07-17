package com.zqkh.archive.context.appservice.impl.domain.repository.model;

import com.google.common.base.Joiner;
import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.zqkh.archive.context.appservice.impl.domain.AttachArchive;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.AttachArchiveDmoMapper;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.AttachArchiveDmoWithBLOBs;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wenjie
 * @date 2017/12/7 0007 14:53
 */
@Repository("AttachArchive_Repository")
public class AttachArchiveRepository implements IRepository<AttachArchive>{

    public static final String ATTACH_LIST_FIELD_NAME = "attachList";

    public static final String SPLIT_BY_COMMA = ",";

    @Autowired
    private AttachArchiveDmoMapper attachArchiveDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public AttachArchive getEntityById(Identifier id, EntityLoader<AttachArchive> entityLoader) {
        AttachArchiveDmoWithBLOBs attachArchiveDmoWithBLOBs = attachArchiveDmoMapper.selectByPrimaryKey(id.toValue());
        if (attachArchiveDmoWithBLOBs == null) {
            return null;
        }

        AttachArchive attachArchive = entityLoader.create(id);
        modelMapper.map(attachArchiveDmoWithBLOBs, attachArchive);

        EntityObjectUtils.setValue(AttachArchive.class, attachArchive, ATTACH_LIST_FIELD_NAME, Arrays.asList(attachArchiveDmoWithBLOBs.getAttach().split(SPLIT_BY_COMMA)));


        return attachArchive;
    }

    @Override
    public void save(AttachArchive attachArchive) {
        if (attachArchive == null) {
            return;
        }
        AttachArchiveDmoWithBLOBs attachArchiveDmoWithBLOBs = modelMapper.map(attachArchive, AttachArchiveDmoWithBLOBs.class);
        List<String> strings = Optional.of(attachArchive).map(AttachArchive::getAttachList).orElse(Collections.emptyList());
        attachArchiveDmoWithBLOBs.setAttach(Joiner.on(SPLIT_BY_COMMA).join(strings));
        if(attachArchiveDmoMapper.updateByPrimaryKeyWithBLOBs(attachArchiveDmoWithBLOBs) == 0){
            attachArchiveDmoMapper.insert(attachArchiveDmoWithBLOBs);
        }
    }

    @Override
    public void remove(AttachArchive attachArchive) {
        if (attachArchive == null) {
            return;
        }
        attachArchiveDmoMapper.deleteByPrimaryKey(attachArchive.getId().toValue());
    }
}
