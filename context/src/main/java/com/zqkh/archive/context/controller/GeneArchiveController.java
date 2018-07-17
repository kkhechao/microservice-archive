package com.zqkh.archive.context.controller;

import com.zqkh.archive.context.appservice.inter.GeneArchiveService;
import com.zqkh.archive.feign.GeneArchiveClient;
import com.zqkh.archive.feign.dto.gene.GeneArchiveInfoDto;
import com.zqkh.archive.feign.vo.AddGeneArchiveVo;
import com.zqkh.archive.feign.vo.AuditGeneArchiveVo;
import com.zqkh.archive.feign.vo.BatchAuditGeneArchiveVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 基因档案控制层
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
@RestController
public class GeneArchiveController  implements GeneArchiveClient {

    @Resource
    private GeneArchiveService geneArchiveService;


    @Override
    public GeneArchiveInfoDto getGeneArchiveInfoByOrderId(String geneOrderId) {
        return geneArchiveService.getGeneArchiveInfoByOrderId(geneOrderId);
    }

    @Override
    public void batchAuditGeneArchive(@RequestBody BatchAuditGeneArchiveVo batchAuditGeneArchiveVo) {
        geneArchiveService.batchAuditGeneArchive(batchAuditGeneArchiveVo);
    }

    @Override
    public void auditGeneArchive(@RequestBody AuditGeneArchiveVo auditGeneArchiveVo) {
        geneArchiveService.auditGeneArchive(auditGeneArchiveVo);
    }

    @Override
    public void addGeneArchive(@RequestBody AddGeneArchiveVo addGeneArchiveVo) {
        geneArchiveService.addGeneArchive(addGeneArchiveVo);
    }
}
