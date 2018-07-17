package com.zqkh.archive.feign;

import com.zqkh.archive.feign.dto.AttachArchiveDto;
import com.zqkh.archive.feign.dto.AttachArchiveReqDto;
import com.zqkh.common.result.PageResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wenjie
 * @date 2017/12/11 0011 16:14
 */
@FeignClient("microservice-archive-context")
public interface AttachArchiveClient {


    @PostMapping("/attachArchive/add")
    void addAttachArchive(@RequestBody AttachArchiveDto attachArchiveDto);

    @GetMapping("/attachArchive/")
    PageResult<AttachArchiveDto> getAttachArchive(@RequestBody AttachArchiveReqDto attachArchiveReqDto);

    @PostMapping("/attachArchive/edit")
    void editAttachArchive(@RequestBody AttachArchiveDto attachArchiveDto);

    @PostMapping("/attachArchive/del")
    void delAttachArchive(@RequestParam("id") String id);
}
