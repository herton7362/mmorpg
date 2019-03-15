package com.kratos.engine.framework.wechat;

import com.kratos.engine.framework.wechat.bean.ResAccessToken;

public interface WechatService {
    ResAccessToken getAccessToken(String code);
}
