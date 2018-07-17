package com.zqkh.archive.context.appservice.impl.domain.repository.mappers;

import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.BodyInfoDmo;

public interface BodyInfoDmoMapper {
    int deleteByPrimaryKey(String basicArchiveId);

    int insert(BodyInfoDmo record);

    int insertSelective(BodyInfoDmo record);

    BodyInfoDmo selectByBasicArchiveId(String basicArchiveId);

    int updateByPrimaryKeySelective(BodyInfoDmo record);

    int update(BodyInfoDmo record);
}