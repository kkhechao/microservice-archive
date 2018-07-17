package com.zqkh.archive.feign;

import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.archive.feign.dto.FamilyMemberDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hty
 * @create 2018-01-30 17:04
 **/
@FeignClient("microservice-archive-context")
public interface FamilyMemberClient {

    /**
     * 成员列表
     * @param createId
     * @return
     */
    @GetMapping("/familyMember/getFamilyMemberList")
    List<FamilyMemberDto> getFamilyMemberList(@RequestParam("createId")String createId);

    /**
     * 删除成员
     * @param memberId
     */
    @GetMapping("/familyMember/removeFamilyMemberById")
    void removeFamilyMemberById(@RequestParam("memberId") String memberId);

    /**
     * 修改称呼
     * @param familyMemberDto
     */
    @PostMapping("/familyMember/changeAppellation")
    void changeAppellation(@RequestBody @Valid FamilyMemberDto familyMemberDto);

    /**
     * 修改头像
     * @param familyMemberDto
     */
    @PostMapping("/familyMember/changeAvatar")
    void changeAvatar(@RequestBody @Valid FamilyMemberDto familyMemberDto);


    @GetMapping("/familyMember/getFamilyMemberDetail")
    BasicArchiveDto getFamilyMemberDetail(@RequestParam("memberId")String memberId);

    /**
     * 增加基本档案
     * @param basicArchiveDto
     * @return
     */
    @PostMapping("/familyMember/addBasicArchive")
    BasicArchiveDto addBasciArchive(@RequestBody BasicArchiveDto basicArchiveDto);

    /**
     * 修改基本档案
     * @param basicArchiveDto
     * @return
     */
    @PostMapping("/familyMember/updateBasicArchive")
    BasicArchiveDto updateBasicArchive(@RequestBody BasicArchiveDto basicArchiveDto);

    /**
     * 根据成员姓名模糊搜索成员编号
     * @param name:成员姓名
     * @return
     */
    @GetMapping("/familyMember/idSearch")
    List<String> searchFamilyIdByName(@RequestParam("name")String name);

}
