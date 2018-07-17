package com.zqkh.archive.feign.dto.gene;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 体征
 *
 * @author 东来
 * @create 2018/3/1 0001
 */
@Setter
@Getter
@NoArgsConstructor
public class SignsDto implements Serializable {

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 出生日期
     */
    private String birthDate;
}
