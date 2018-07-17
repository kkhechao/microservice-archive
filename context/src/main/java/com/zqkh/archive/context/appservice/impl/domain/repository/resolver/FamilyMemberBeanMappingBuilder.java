package com.zqkh.archive.context.appservice.impl.domain.repository.resolver;

import com.zqkh.archive.context.appservice.impl.domain.BasicArchive;
import com.zqkh.archive.context.appservice.impl.domain.BodyInfo;
import com.zqkh.archive.context.appservice.impl.domain.FamilyMember;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.BasicArchiveDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.BodyInfoDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.FamilyMemberDmo;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * @author hty
 * @create 2018-01-30 17:43
 **/
@Component
public class FamilyMemberBeanMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(type(FamilyMember.class).accessible(true), FamilyMemberDmo.class)
                .fields("creater", "createrId")
                .fields("appellation", "appellation")
                .fields("user", "userId")
                .fields("avatar", "avatar")
        ;

        mapping(type(BasicArchive.class).accessible(true), BasicArchiveDmo.class)
                .fields("bloodType", "bloodType")
                .fields("profession", "profession")
                .fields("sex", "sex")
                .fields("name", "name")
                .fields("nativePlace", "nativePlace")
                .fields("ethnic", "ethnic")
                .fields("familyMember", "familyMemberId")
                .fields("idCard","idCard")
        ;
        mapping(type(BodyInfo.class).accessible(true), BodyInfoDmo.class)
                .fields("height", "height")
                .fields("weight", "weight")
                .fields("birthDate", "birthday")
        ;
    }
}
