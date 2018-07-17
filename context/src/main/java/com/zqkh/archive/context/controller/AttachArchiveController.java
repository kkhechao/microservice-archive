package com.zqkh.archive.context.controller;

import com.zqkh.archive.context.appservice.inter.AttachArchiveService;
import com.zqkh.archive.context.appservice.inter.FamilyMemberService;
import com.zqkh.archive.feign.AttachArchiveClient;
import com.zqkh.archive.feign.dto.AttachArchiveDto;
import com.zqkh.archive.feign.dto.AttachArchiveReqDto;
import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.common.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenjie
 * @date 2018/1/30 0030 16:50
 */
@RestController
public class AttachArchiveController implements AttachArchiveClient {


    @Autowired
    AttachArchiveService attachArchiveService;



    @Override
    public void addAttachArchive(@RequestBody AttachArchiveDto attachArchiveDto) {
        attachArchiveService.addAttachArchive(attachArchiveDto);
    }





    @Override
    public PageResult<AttachArchiveDto> getAttachArchive(@RequestBody AttachArchiveReqDto attachArchiveReqDto) {
        return attachArchiveService.getAttachArchiveByType(attachArchiveReqDto);
    }

    @Override
    public void editAttachArchive(@RequestBody AttachArchiveDto attachArchiveDto) {
        attachArchiveService.editAttachArchive(attachArchiveDto);
    }

    @Override
    public void delAttachArchive(@RequestParam("id") String id) {
        attachArchiveService.delAttachArchive(id);
    }
}
