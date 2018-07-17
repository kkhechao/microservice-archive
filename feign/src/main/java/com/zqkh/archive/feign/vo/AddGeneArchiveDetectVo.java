package com.zqkh.archive.feign.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 添加基因报告检测项VO
 * @author 东来
 * @create 2018/2/24 0024
 */
@NoArgsConstructor
@Getter
@Setter
public class AddGeneArchiveDetectVo {

    public enum Risk{
        /**
         * 高风险
         */
        HIGH,
        /**
         * 低风险
         */
        LOW,
        /**
         * 中风险
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
    private String diseaseId;

    /**
     * 风险
     */
    private Risk risk;

    /**
     * 检测位点
     */
    private List<AddGeneArchiveLocusInfoVo> locusInfo;
}
