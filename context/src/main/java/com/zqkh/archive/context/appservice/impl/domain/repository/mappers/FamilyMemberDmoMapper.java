package com.zqkh.archive.context.appservice.impl.domain.repository.mappers;

import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.FamilyMemberDmo;

public interface FamilyMemberDmoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FamilyMemberDmo record);

    int insertSelective(FamilyMemberDmo record);

    FamilyMemberDmo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FamilyMemberDmo record);

    int updateByPrimaryKey(FamilyMemberDmo record);
}