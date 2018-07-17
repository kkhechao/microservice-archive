package com.zqkh.archive.feign.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 添加基因报告位点信息
 * @author 东来
 * @create 2018/2/24 0024
 */
@NoArgsConstructor
@Getter
@Setter
public class AddGeneArchiveLocusInfoVo {

    /**
     * 位点RS编码
     */
    private String locusRsValue;

    /**
     * 位点自定义编号
     */
    private String locusId;

    /**
     * 基因
     */
    private String gene;

    /**
     * 染色体定位
     */
    private String chromosomePosition;

    /**
     * 基因型
     */
    private String geneType;

    /**
     * 位点图片编号,来自于资源服务返回的MD5值
     */
    private String locusImageId;



}
