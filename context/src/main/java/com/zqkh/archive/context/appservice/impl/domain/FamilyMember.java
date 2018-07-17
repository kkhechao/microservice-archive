package com.zqkh.archive.context.appservice.impl.domain;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;

/**
 * @author wenjie
 * @date 2018/1/30 0030 14:07
 */
@Getter
public class FamilyMember extends EntityObject {

    /**
     * 建档人
     */
    private String creater;

    /**
     * 称呼
     */
    private String appellation;

    /**
     * 关联用户
     */
    private String user;

    /**
     * 头像
     */
    private String avatar = "http://www.qqzhi.com/uploadpic/2015-01-27/121838355.jpg";


    public void init(String creater, String appellation, String user, String avatar) {
        this.creater = creater;
        this.appellation = appellation;
        this.user = user;
        this.avatar = avatar;
    }

    public void changeAppellation(String appellation) {
        this.appellation = appellation;
    }

    public void changeAvatar(String avatar) {
        this.avatar = avatar;
    }
}
