package com.zqkh.archive.feign.dto.gene;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 检测位点DTO
 *
 * @author 东来
 * @create 2018/1/31 0031
 */
@Getter
@Setter
@NoArgsConstructor
public class LocusInfoDto {

    /**
     * 位点Rs编码
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
     * 位点图片链接地址
     */
    private String locusImageUrl;
}
