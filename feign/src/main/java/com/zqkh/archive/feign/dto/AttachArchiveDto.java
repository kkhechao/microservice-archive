package com.zqkh.archive.feign.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author wenjie
 * @date 2018/1/30 0030 16:48
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachArchiveDto {

    private String id;

    private String type;

    private Date date;

    private String desc;

    private String memberId;

    private List<String> attachList;

}
