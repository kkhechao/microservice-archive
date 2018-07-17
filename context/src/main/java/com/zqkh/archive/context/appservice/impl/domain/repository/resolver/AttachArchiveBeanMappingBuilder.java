package com.zqkh.archive.context.appservice.impl.domain.repository.resolver;

import com.zqkh.archive.context.appservice.impl.domain.AttachArchive;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.AttachArchiveDmoWithBLOBs;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * @author wenjie
 * @date 2017/11/27 0027 9:52
 */
@Component
public class AttachArchiveBeanMappingBuilder extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(type(AttachArchive.class).accessible(true), AttachArchiveDmoWithBLOBs.class)
                .fields("type", "type")
                .fields("date", "date")
                .fields("createTime", "createTime")
                .fields("description", "description")
                .fields("familyMember", "familyMemberId")
        ;
    }
}
