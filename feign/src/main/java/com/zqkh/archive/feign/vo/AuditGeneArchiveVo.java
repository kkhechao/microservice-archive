package com.zqkh.archive.feign.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * 审核疾病报告
 *
 * @author 东来
 * @create 2018/3/6 0006
 */
@Getter
@Setter
@NoArgsConstructor
public class AuditGeneArchiveVo implements Serializable {
    /**
     * 基因订单编号
     */
    private String geneOrderId;


    /**
     * 疾病风险
     */
    private Set<GeneArchiveDiseaseRiskVo> diseaseRisk;


}
