package com.zqkh.archive.feign.dto.gene;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 东来
 * @create 2018/3/1 0001
 */
@Setter
@Getter
@NoArgsConstructor
public class GeneArchiveInfoDto {

    /**
     * 个人信息
     */
    private PersonDatumDto personDatum;

    /**
     * 检测类型
     */
    private String sampleType;

    /**
     * 样本编号
     */
    private String sampleId;

    /**
     * 样本接收时间
     */
    private Date receiveTime;

    /**
     * 报告日期
     */
    private Date reportTime;

    /**
     * 报告审核时间
     */
    private Date auditTime;

    /**
     * 建议
     */
    private String suggest;


    /**
     * 套餐名
     */
    private String packageName;

    /**
     *  检测项
     */
    private List<DetectItemDto> detectItem;

    /**
     * 风险分组
     */
    private Set<RiskGroup> riskGroup;


    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 头像链接地址
     */
    private String headUrl;
}
