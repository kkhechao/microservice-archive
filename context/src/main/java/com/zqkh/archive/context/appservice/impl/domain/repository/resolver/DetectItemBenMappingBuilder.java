package com.zqkh.archive.context.appservice.impl.domain.repository.resolver;

import com.zqkh.archive.context.appservice.impl.domain.DetectItem;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.DetectItemDmo;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * @author 东来
 * @create 2018/1/31 0031
 */
@Component
public class DetectItemBenMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(type(DetectItem.class).accessible(true), DetectItemDmo.class)
                .fields("diseaseId","disease")
                .fields("risk","risk");
    }
}
