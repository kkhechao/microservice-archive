package com.zqkh.archive.context.appservice.inter;

import com.zqkh.archive.feign.dto.gene.GeneArchiveInfoDto;
import com.zqkh.archive.feign.vo.AddGeneArchiveVo;
import com.zqkh.archive.feign.vo.AuditGeneArchiveVo;
import com.zqkh.archive.feign.vo.BatchAuditGeneArchiveVo;

/**
 * 基因档案业务接口
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
public interface GeneArchiveService {



    /**
     * 根据基因订单编号查询基因报告详情
     * @param geneOrderId:基因订单编号
     * @return
     */
    GeneArchiveInfoDto getGeneArchiveInfoByOrderId(String geneOrderId);




    /**
     * 批量审核基因报告
     * @param batchAuditGeneArchiveVo
     */
    void batchAuditGeneArchive(BatchAuditGeneArchiveVo batchAuditGeneArchiveVo);


    /**
     * 审核基因报告
     * @param auditGeneArchiveVo
     */
    void auditGeneArchive(AuditGeneArchiveVo auditGeneArchiveVo);

    /**
     * 添加基因档案
     * @param addGeneArchiveVo
     */
    void addGeneArchive(AddGeneArchiveVo addGeneArchiveVo);
}
