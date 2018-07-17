package com.zqkh.archive.context.controller;

import com.zqkh.archive.context.appservice.inter.FamilyMemberService;
import com.zqkh.archive.feign.FamilyMemberClient;
import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.archive.feign.dto.FamilyMemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hty
 * @create 2018-01-30 17:03
 **/
@RestController
public class FamilyMemberController implements FamilyMemberClient {

    @Autowired
    FamilyMemberService familyMemberService;

    @Override
    public List<FamilyMemberDto> getFamilyMemberList(@RequestParam("createId") String createId) {
        return familyMemberService.getFamilyMemberList(createId);
    }

    @Override
    public void removeFamilyMemberById(@RequestParam("memberId") String memberId) {
        familyMemberService.removeFamilyMemberById(memberId);
    }

    @Override
    public void changeAppellation(@RequestBody FamilyMemberDto familyMemberDto) {
        familyMemberService.changeAppellation(familyMemberDto);
    }

    @Override
    public void changeAvatar(@RequestBody FamilyMemberDto familyMemberDto) {
        familyMemberService.changeAvatar(familyMemberDto);
    }

    @Override
    public BasicArchiveDto getFamilyMemberDetail(@RequestParam("memberId")String memberId) {
        return familyMemberService.getFamilyMemberDetail(memberId);
    }

    @Override
    public BasicArchiveDto addBasciArchive(@RequestBody BasicArchiveDto basicArchiveDto) {
        return familyMemberService.addBasicArchive(basicArchiveDto);

    }

    @Override
    public BasicArchiveDto updateBasicArchive(@RequestBody BasicArchiveDto basicArchiveDto) {
        return familyMemberService.updateBasicArchive(basicArchiveDto);
    }

    @Override
    public List<String> searchFamilyIdByName(String name) {
        return familyMemberService.searchFamilyIdByName(name);
    }
}
