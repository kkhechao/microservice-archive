package com.zqkh.archive.context.appservice.impl.domain;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author wenjie
 * 检测项
 * @date 2018/1/30 0030 14:31
 */
@Getter
@Setter
@ToString
public class DetectItem extends ValueObject{

    public enum Risk{
        HIGH,
        LOW,
        MIDDLE,
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
    private List<LocusInfo> locusInfo;



}
