package com.zqkh.archive.context.appservice.impl.domain;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author wenjie
 * 附件档案
 * @date 2018/1/30 0030 14:18
 */
@Getter
public class AttachArchive extends EntityObject{

    public enum Type{
        //病例
        CASE,
        //手术
        SURGERY,
        //用药
        DRUG,
        //体检
        PHYSICAL
    }

    /**
     * 类型
     */
    private Type type;

    /**
     * 病例世间
     */
    private LocalDate date;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 描述
     */
    private String description;

    /**
     * 成员
     */
    private String familyMember;

    /**
     * 附件
     */
    private List<String> attachList;

    public void init(String type, Date date, String description, String familyMember, List<String> attachList) {
        this.type = AttachArchive.Type.valueOf(type);
        this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.createTime = LocalDateTime.now();
        this.description = description;
        this.familyMember = familyMember;
        this.attachList = attachList;
    }

    public void update(Date date, String description, List<String> attachList) {
        if(date != null) {
            this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        if(StringUtils.isNotEmpty(description)) {
            this.description = description;
        }
        if(attachList != null) {
            this.attachList = attachList;
        }
    }
}
