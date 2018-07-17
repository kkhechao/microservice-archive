package com.zqkh.archive.feign.dto.gene;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * 个人资料
 *
 * @author 东来
 * @create 2018/3/1 0001
 */
@Getter
@NoArgsConstructor
public class PersonDatumDto implements Serializable {

    /**
     * 健康档案编号
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 名族
     */
    private String nation;

    /**
     * 血型
     */
    private String  bloodType;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 体征
     */
    private SignsDto signs;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
      this.gender=gender;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setBloodType(String bloodType) {
        this.bloodType=bloodType;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setSigns(SignsDto signs) {
        this.signs = signs;
    }
}
