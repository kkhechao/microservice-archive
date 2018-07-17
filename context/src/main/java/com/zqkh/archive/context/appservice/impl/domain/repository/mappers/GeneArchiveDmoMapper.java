package com.zqkh.archive.context.appservice.impl.domain.repository.mappers;

import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.GeneArchiveDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.GeneArchiveDmoExample;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.GeneArchiveDmoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GeneArchiveDmoMapper {
    int countByExample(GeneArchiveDmoExample example);

    int deleteByExample(GeneArchiveDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(GeneArchiveDmoWithBLOBs record);

    int insertSelective(GeneArchiveDmoWithBLOBs record);

    List<GeneArchiveDmoWithBLOBs> selectByExampleWithBLOBs(GeneArchiveDmoExample example);

    List<GeneArchiveDmo> selectByExample(GeneArchiveDmoExample example);

    GeneArchiveDmoWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GeneArchiveDmoWithBLOBs record, @Param("example") GeneArchiveDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") GeneArchiveDmoWithBLOBs record, @Param("example") GeneArchiveDmoExample example);

    int updateByExample(@Param("record") GeneArchiveDmo record, @Param("example") GeneArchiveDmoExample example);

    int updateByPrimaryKeySelective(GeneArchiveDmoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GeneArchiveDmoWithBLOBs record);

    int updateByPrimaryKey(GeneArchiveDmo record);
}