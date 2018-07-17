package com.zqkh.archive.feign.dto.gene;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * 检测项DTO
 * @author 东来
 * @create 2018/1/31 0031
 */
@Getter
@Setter
@NoArgsConstructor
public class DetectItemDto {

    public enum Risk{
        /**
         * 高
         */
        HIGH,
        /**
         * 低
         */
        LOW,
        /**
         * 中
         */
        MIDDLE,
        /**
         * 正常
         */
        NORMAL;

        public static Risk getRisk(String riskName){
            for (Risk risk:Risk.values()) {
                if(risk.name().equals(riskName)){
                    return risk;
                }
            }
            return null;
        }
    }

    /**
     * 疾病编号
     */
    private String id;

    /**
     * 疾病名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 基本图标链接地址
     */
    private String icoUrl;


    /**
     * 风险
     */
    private Risk risk;

    /**
     * 检测位点
     */
    private List<LocusInfoDto> locusInfo;

    /**
     * 症状
     */
    private String symptom;

    /**
     * 涉及相关检查
     */
    private String examination;

    /**
     * 预防保健以及建议
     */
    private String healthAdvice;


}
