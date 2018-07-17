package com.zqkh.archive.context.event;

import com.jovezhao.nest.ddd.event.EventHandler;
import com.zqkh.archive.context.appservice.impl.domain.BasicArchive;
import com.zqkh.archive.context.appservice.inter.FamilyMemberService;
import com.zqkh.archive.context.uitl.CardUtil;
import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.user.event.dto.UserAuthEventDto;
import com.zqkh.user.event.dto.UserFirstLoginEventDto;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wenjie
 * @date 2017/12/2 0002 15:09
 */
@Slf4j
public class UserAuthEventHandler implements EventHandler<UserAuthEventDto> {

    public static final String ME = "自己";

    private FamilyMemberService familyMemberService;

    public UserAuthEventHandler(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @Override
    public String getEventName() {
        return UserAuthEventDto.EVENT_NAME;
    }

    @Override
    public Class<UserAuthEventDto> getTClass() {
        return UserAuthEventDto.class;
    }

    @Override
    public void handle(UserAuthEventDto userAuthEventDto) {
        BasicArchiveDto basicArchiveDto = new BasicArchiveDto();
        basicArchiveDto.setCreater(userAuthEventDto.getUserId());
        basicArchiveDto.setAppellation(ME);
        basicArchiveDto.setIdCard(userAuthEventDto.getIdCardNumber());
        basicArchiveDto.setName(userAuthEventDto.getName());
        familyMemberService.addBasicArchiveFromMQ(basicArchiveDto);
    }
}
