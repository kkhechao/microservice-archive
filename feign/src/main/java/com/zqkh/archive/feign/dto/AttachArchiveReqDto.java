package com.zqkh.archive.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wenjie
 * @date 2018/1/30 0030 18:17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachArchiveReqDto {

    private String memberId;

    private String type;

    private Integer pageIndex;

    private Integer pageSize;
}
