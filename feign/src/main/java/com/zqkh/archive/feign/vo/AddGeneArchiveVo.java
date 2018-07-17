package com.zqkh.archive.feign.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 添加基因报告VO
 *
 * @author 东来
 * @create 2018/2/24 0024
 */
@NoArgsConstructor
@Getter
@Setter
public class AddGeneArchiveVo {

    /**
     * 套餐名
     */
    private String packageName;

    /**
     * 成员编号
     */
    private String familyMemberId;

    /**
     * 订单编号
     */
    private String geneOrderId;

    /**
     * 样本编号
     */
    private String sampleId;

    /**
     * 样本类型
     */
    private String sampleType;

    /**
     * 检测项
     */
    private List<AddGeneArchiveDetectVo> detect;


    /**
     * 检测原始数据JSON
     */
    private String detectionDataJson;

    /**
     * 样本接收时间
     */
    private Date receiveTime;

}
