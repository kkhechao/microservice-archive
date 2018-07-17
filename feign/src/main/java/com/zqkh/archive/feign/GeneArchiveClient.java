package com.zqkh.archive.feign;

import com.zqkh.archive.feign.dto.gene.GeneArchiveInfoDto;
import com.zqkh.archive.feign.vo.AddGeneArchiveVo;
import com.zqkh.archive.feign.vo.AuditGeneArchiveVo;
import com.zqkh.archive.feign.vo.BatchAuditGeneArchiveVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基因档案Feign接口
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
@FeignClient("microservice-archive-context")
public interface GeneArchiveClient {

    /**
     * 根据基因订单编号查询基因报告详情
     * @param geneOrderId:基因订单编号
     * @return
     */
    @RequestMapping("/gene/archive/manager/info")
    GeneArchiveInfoDto getGeneArchiveInfoByOrderId(@RequestParam("geneOrderId") String geneOrderId);


    /**
     * 批量审核基因报告
     * @param batchAuditGeneArchiveVo
     */
    @PostMapping("/gene/archive/audit/batch")
    void batchAuditGeneArchive(@RequestBody BatchAuditGeneArchiveVo batchAuditGeneArchiveVo);

    /**
     * 审核基因报告
     * @param auditGeneArchiveVo
     */
    @PostMapping("/gene/archive/audit")
    void auditGeneArchive(@RequestBody AuditGeneArchiveVo auditGeneArchiveVo);

    /**
     * 添加基因档案
     * @param addGeneArchiveVo
     */
    @PostMapping("/gene/archive")
    void addGeneArchive(@RequestBody AddGeneArchiveVo addGeneArchiveVo);

}
