package com.zqkh.archive.context.appservice.inter;

import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.archive.feign.dto.FamilyMemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author hty
 * @create 2018-01-30 17:05
 **/

public interface FamilyMemberService {
    List<FamilyMemberDto> getFamilyMemberList(String createId);

    void removeFamilyMemberById(String memberId);

    void changeAppellation(FamilyMemberDto familyMemberDto);

    void changeAvatar(FamilyMemberDto familyMemberDto);


    BasicArchiveDto addBasicArchive(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto updateBasicArchive(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto getFamilyMemberDetail(String memberId);

    void addBasicArchiveFromMQ(BasicArchiveDto basicArchiveDto);


    /**
     * 根据成员姓名模糊搜索成员编号
     * @param name:成员姓名
     * @return
     */
    List<String> searchFamilyIdByName(String name);
}
