package com.zqkh.archive.context.appservice.impl.domain.repository.mappers;

import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.AttachArchiveDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.AttachArchiveDmoWithBLOBs;

public interface AttachArchiveDmoMapper {
    int deleteByPrimaryKey(String id);

    int insert(AttachArchiveDmoWithBLOBs record);

    int insertSelective(AttachArchiveDmoWithBLOBs record);

    AttachArchiveDmoWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttachArchiveDmoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AttachArchiveDmoWithBLOBs record);

    int updateByPrimaryKey(AttachArchiveDmo record);
}