package com.zqkh.archive.context.appservice.impl.domain;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

/**
 * @author wenjie
 * @date 2018/1/30 0030 14:13
 */
@Getter
public class BasicArchive extends EntityObject{

    public static final String UN_KNOW = "未知";


    public enum BloodType {
        A,
        B,
        AB,
        O,
        UN_KNOW;


        public static BloodType getBloodType(String bloodTypeName) {
            if (ObjectUtils.isEmpty(bloodTypeName)) {
                return UN_KNOW;
            }

            for (BloodType bloodType : BloodType.values()) {
                if (bloodType.name().equals(bloodTypeName)) {
                    return bloodType;
                }
            }

            return UN_KNOW;
        }
    }

    public enum Sex {
        MAN,
        WOMAN,
        UN_KNOW;

        public static Sex getSex(String sexname) {
            if (ObjectUtils.isEmpty(sexname)) {
                return UN_KNOW;
            }

            for (Sex sex : Sex.values()) {
                if (sex.name().equals(sexname)) {
                    return sex;
                }
            }
            return UN_KNOW;
        }
    }

    /**
     * 血型
     */
    private BloodType bloodType = BloodType.UN_KNOW;

    /**
     * 职业
     */
    private String profession = UN_KNOW;

    /**
     * 身份证
     */
    private String idCard;

    private Sex sex = Sex.MAN;

    /**
     * 姓名
     */
    private String name;

    /**
     * 籍贯
     */
    private String nativePlace = UN_KNOW;

    /**
     * 民族
     */
    private String ethnic = UN_KNOW;

    /**
     * 成员
     */
    private String familyMember;

    /**
     * 体征
     */
    private BodyInfo bodyInfo;


    public void init(BloodType bloodType, String profession, Sex sex, String idCard, String name, String nativePlace, String ethnic, String familyMember, BodyInfo bodyInfo) {
        this.bloodType = bloodType;
        this.profession = profession;
        this.sex = sex;
        this.idCard = idCard;
        this.name = name;
        this.nativePlace = nativePlace;
        this.ethnic = ethnic;
        this.familyMember = familyMember;
        this.bodyInfo = bodyInfo;
    }

    public void changeBodyInfo(BodyInfo bodyInfo) {
        this.bodyInfo = bodyInfo;
    }

    public void changeProfession(String profession) {
        this.profession = profession;
    }

    public void changeBloodType(String bloodType) {
        this.bloodType = BloodType.valueOf(bloodType);
    }

    public void changeIdCard(String idCard) {
        this.idCard = idCard;
    }


    public void changeSex(String sex) {
        this.sex = Sex.valueOf(sex);
    }

    public void changeEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public void changeNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void changeName(String name) {
        this.name = name;
    }


}
