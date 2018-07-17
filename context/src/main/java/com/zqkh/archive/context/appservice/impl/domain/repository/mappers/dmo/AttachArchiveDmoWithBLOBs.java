package com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachArchiveDmoWithBLOBs extends AttachArchiveDmo {
    private String description;

    private String attach;
}