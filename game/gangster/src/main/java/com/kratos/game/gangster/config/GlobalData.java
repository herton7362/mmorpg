package com.kratos.game.gangster.config;

import com.kratos.engine.framework.global.BaseGlobalData;
import org.springframework.stereotype.Component;

@Component
public class GlobalData extends BaseGlobalData {
    /**
     * 每日重置的时间戳
     */
    public volatile long dailyResetTimestamp;
}
