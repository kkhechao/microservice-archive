package com.zqkh.archive.context.appservice.impl.domain.repository.query;

import org.apache.ibatis.annotations.Param;

/**
 * 基因档案查询Mapping
 *
 * @author 东来
 * @create 2018/3/1 0001
 */
public interface GeneArchiveQuery {
    /**
     * 根据基因订单编号查询基因报告编号
     * @param geneOrderId:基因订单编号
     * @return
     */
    String getPrimaryKeyByOrderId(@Param("geneOrderId")String geneOrderId);

    /**
     * 根据采集器编号查询基因报告编号
     * @param sampleId:采集器编号
     * @param familyMemberId :成员编号
     * @return
     */
    String getPrimaryKeyBySampleIdAndFamilyMemberId(@Param("sampleId")String sampleId,@Param("familyMemberId")String familyMemberId);
}
