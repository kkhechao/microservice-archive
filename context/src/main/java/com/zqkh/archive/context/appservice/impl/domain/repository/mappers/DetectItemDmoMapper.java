package com.zqkh.archive.context.appservice.impl.domain.repository.mappers;

import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.DetectItemDmo;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.DetectItemDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DetectItemDmoMapper {
    int countByExample(DetectItemDmoExample example);

    List<DetectItemDmo> selectByGeneArchiveId(String id);

    int deleteByExample(DetectItemDmoExample example);

    int insert(DetectItemDmo record);

    int insertSelective(DetectItemDmo record);

    List<DetectItemDmo> selectByExample(DetectItemDmoExample example);

    int updateByExampleSelective(@Param("record") DetectItemDmo record, @Param("example") DetectItemDmoExample example);

    int updateByExample(@Param("record") DetectItemDmo record, @Param("example") DetectItemDmoExample example);

    void batchInsert(@Param("id")String geneArchiveId, @Param("items")List<DetectItemDmo> detectItemDmoList);



}