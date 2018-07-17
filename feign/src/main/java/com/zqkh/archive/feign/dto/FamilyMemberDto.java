package com.zqkh.archive.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hty
 * @create 2018-01-30 16:52
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMemberDto {

    /**
     * 成员id
     */
    private String id;

    /**
     * 称呼
     */
    private String appellation;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 身份证
     */
    private String idCard;

    private String name;

    private String sex;

    private String bloodType;

    private BodyInfoDto bodyInfo;

}
