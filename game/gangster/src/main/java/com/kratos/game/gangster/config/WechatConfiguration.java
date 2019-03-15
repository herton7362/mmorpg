package com.kratos.game.gangster.config;

import com.kratos.engine.framework.wechat.WechatManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class WechatConfiguration {
    public static final String WX_APP_ID = "wxac5659766bb48844";
    public static final String WX_SECRET = "147b077ada82e8b54023b5f40856a027";

    @Autowired
    private WechatManager wechatManager;

    @PostConstruct
    public void config() {
        wechatManager.create(WX_APP_ID, WX_SECRET);
    }
}
