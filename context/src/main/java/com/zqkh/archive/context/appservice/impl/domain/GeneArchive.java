package com.zqkh.archive.context.appservice.impl.domain;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import org.assertj.core.util.Lists;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wenjie
 * @date 2018/1/30 0030 14:25
 */
@Getter
public class GeneArchive extends EntityObject {


    /**
     * 套餐名
     */
    private String name;

    /**
     * 检测类型
     */
    private String sampleType;

    /**
     * 接收时间
     */
    private Date receiveDate;

    /**
     * 报告时间
     */
    private Date reportDate;

    /**
     * 成员
     */
    private String familyMember;

    /**
     * 检测项
     */
    private List<DetectItem> detectItemList;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 样本编号
     */
    private String sampleId;

    /**
     * 检测原始JSON数据
     */
    private String detectionData;

    /**
     * 建议
     */
    private String suggest;


    /**
     * 初始化
     *
     * @param name:套餐名
     * @param sampleType:检测类型
     * @param familyMember:家庭成员编号
     * @param orderId:基因订单编号
     * @param sampleId :样本编号
     */
    public void init(String name, String sampleType, String familyMember, String orderId, String sampleId,String detectionData) {
        this.name = name;
        this.sampleType = sampleType;
        this.familyMember = familyMember;
        this.orderId = orderId;
        this.sampleType = sampleType;
        this.sampleId = sampleId;
        this.reportDate=new Date();
        this.detectionData=detectionData;
    }

    /**
     * 修改
     * @param name:套餐名
     * @param sampleType:检测类型
     * @param familyMember:家庭成员编号
     * @param orderId:基因订单编号
     * @param sampleId:样本编号
     */
    public void edit(String name, String sampleType, String familyMember, String orderId, String sampleId,String detectionData) {
        this.name = name;
        this.sampleType = sampleType;
        this.familyMember = familyMember;
        this.orderId = orderId;
        this.sampleType = sampleType;
        this.sampleId = sampleId;
        this.detectionData=detectionData;
    }



    /**
     * 接收到样本
     *
     * @param
     */
    public void receiveGeneArchive(Date receiveDate) {
        this.receiveDate = receiveDate;

    }

    /**
     * 审核报告
     *
     */
    public void auditGeneArchive() {
        this.auditTime = new Date();
    }

    /**
     * 添加检测项
     *
     * @param detectItem
     */
    public void addDetectItem(DetectItem detectItem) {
        if (ObjectUtils.isEmpty(detectItem)) {
            return;
        }
        if (ObjectUtils.isEmpty(this.detectItemList)) {
            this.detectItemList = new CopyOnWriteArrayList<>();
        }

        Iterator<DetectItem> it = this.detectItemList.iterator();
        while (it.hasNext()) {
            DetectItem detectItem1 = it.next();
            if (detectItem1.getDiseaseId().equals(detectItem.getDiseaseId())) {
                it.remove();
            }
        }
        this.detectItemList.add(detectItem);


    }


}
