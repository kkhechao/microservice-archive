package com.zqkh.archive.context.appservice.inter;

import com.zqkh.archive.feign.dto.AttachArchiveDto;
import com.zqkh.archive.feign.dto.AttachArchiveReqDto;
import com.zqkh.common.result.PageResult;
import com.zqkh.archive.feign.dto.BasicArchiveDto;

/**
 * @author wenjie
 * @date 2018/1/30 0030 16:34
 */
public interface AttachArchiveService {

    void addAttachArchive(AttachArchiveDto attachArchiveDto);

    PageResult<AttachArchiveDto> getAttachArchiveByType(AttachArchiveReqDto memberId);

    void editAttachArchive(AttachArchiveDto attachArchiveDto);


    void delAttachArchive(String id);
}
