package com.zqkh.archive.context.appservice.impl.domain.repository.mappers;

import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.BasicArchiveDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.BasicArchiveDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicArchiveDmoMapper {
    int countByExample(BasicArchiveDmoExample example);

    int deleteByExample(BasicArchiveDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(BasicArchiveDmo record);

    int insertSelective(BasicArchiveDmo record);

    List<BasicArchiveDmo> selectByExample(BasicArchiveDmoExample example);

    BasicArchiveDmo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BasicArchiveDmo record, @Param("example") BasicArchiveDmoExample example);

    int updateByExample(@Param("record") BasicArchiveDmo record, @Param("example") BasicArchiveDmoExample example);

    int updateByPrimaryKeySelective(BasicArchiveDmo record);

    int updateByPrimaryKey(BasicArchiveDmo record);
}