package com.zqkh.archive.feign.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 批量审核基因报告VO
 * @author 东来
 * @create 2018/2/2 0002
 */
@Getter
@Setter
@NoArgsConstructor
public class BatchAuditGeneArchiveVo implements Serializable {

    /**
     * 基因订单编号
     */
    private Set<String> geneOrderId;



}
