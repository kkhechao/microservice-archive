package com.zqkh.archive.context.appservice.impl.domain;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @author wenjie
 * 体征
 * @date 2018/1/30 0030 14:29
 */
@Getter
public class BodyInfo extends ValueObject {

    private String height="175";

    private String weight= "60";

    private String birthDate = "1985-1-1";


    public void init(String height, String weight, String brithday) {
        this.height = height;
        this.weight = weight;
        this.birthDate = brithday;
    }
}
