package com.zqkh.archive.context.appservice.impl.domain.repository.resolver;

import com.zqkh.archive.context.appservice.impl.domain.GeneArchive;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.GeneArchiveDmoWithBLOBs;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * @author 东来
 * @create 2018/1/31 0031
 */
@Component
public class GeneArchiveBeanMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(type(GeneArchive.class).accessible(true), GeneArchiveDmoWithBLOBs.class)
                .fields("id","id")
                .fields("name","name")
                .fields("sampleType","sampleType")
                .fields("receiveDate","receiveDate")
                .fields("reportDate","reportDate")
                .fields("familyMember","familyMemberId")
                .fields("orderId","orderId")
                .fields("auditTime","auditTime")
                .fields("sampleId","sampleId")
                .fields("detectionData","detectionData")
                .fields("suggest","suggest");
    }
}
