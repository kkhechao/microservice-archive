package com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachArchiveDmo {
    private String id;

    private String type;

    private Date date;

    private Date createTime;

    private String familyMemberId;
}