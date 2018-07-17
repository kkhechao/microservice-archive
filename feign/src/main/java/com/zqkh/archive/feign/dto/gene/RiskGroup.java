package com.zqkh.archive.feign.dto.gene;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 风险分组
 *
 * @author 东来
 * @create 2018/3/2 0002
 */
@Getter
@Setter
@NoArgsConstructor
public class RiskGroup implements Serializable {

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

        public static RiskGroup.Risk getRisk(String riskName){
            for (RiskGroup.Risk risk: RiskGroup.Risk.values()) {
                if(risk.name().equals(riskName)){
                    return risk;
                }
            }
            return null;
        }
    }

    /**
     * 风险
     */
    private Risk risk;

    /**
     * 疾病名称
     */
    private Set<String> diseaseName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskGroup riskGroup = (RiskGroup) o;
        return risk == riskGroup.risk;
    }

    @Override
    public int hashCode() {

        return Objects.hash(risk);
    }
}
