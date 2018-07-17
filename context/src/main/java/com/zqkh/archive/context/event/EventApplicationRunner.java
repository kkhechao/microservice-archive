package com.zqkh.archive.context.event;

import com.jovezhao.nest.ddd.event.EventBus;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.archive.context.appservice.inter.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wenjie
 * @date 2017/11/27 0027 16:54
 */
@Component
@AppService
public class EventApplicationRunner implements ApplicationRunner {

    @Autowired
    private FamilyMemberService familyMemberService;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        //创建用户档案EventBus监听
        EventBus.registerHandler(new UserAuthEventHandler(familyMemberService));
    }
}
