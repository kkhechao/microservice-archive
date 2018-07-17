package com.zqkh.archive.context.appservice.impl.domain;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wenjie
 * 位点信息
 * @date 2018/1/30 0030 14:36
 */
@Getter
@Setter
public class LocusInfo extends ValueObject{

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
     * 位点图片编号,来自于资源服务返回的MD5值
     */
    private String locusImageId;

}
