package com.zqkh.archive.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasicArchiveDto implements Serializable {


    private String id;
    /**
     * 血型
     */
    private String bloodType;

    /**
     * 职业
     */
    private String profession;

    private String idCard;

    private String avatar;

    private String sex;

    /**
     * 姓名
     */
    private String name;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 民族
     */
    private String ethnic;

    /**
     * 成员
     */
    private String familyMember;

    /**
     * 称呼
     */
    private String appellation;
    /**
     * 创建人
     */
    private String creater;

    private BodyInfoDto bodyInfo;
    /**
     * 档案编号
     */
    private String archiveNo;

}
