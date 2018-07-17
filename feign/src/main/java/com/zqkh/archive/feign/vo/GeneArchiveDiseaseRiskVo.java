package com.zqkh.archive.feign.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基因报告疾病风险VO
 *
 * @author 东来
 * @create 2018/3/6 0006
 */
@Getter
@Setter
@NoArgsConstructor
public class GeneArchiveDiseaseRiskVo implements Serializable {

    /**
     * 疾病编号
     */
    private String id;

    /**
     * 疾病风险
     */
    private Risk risk;

    public enum Risk {
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

        public static GeneArchiveDiseaseRiskVo.Risk getRisk(String riskName) {
            for (GeneArchiveDiseaseRiskVo.Risk risk : GeneArchiveDiseaseRiskVo.Risk.values()) {
                if (risk.name().equals(riskName)) {
                    return risk;
                }
            }
            return null;
        }
    }

}
