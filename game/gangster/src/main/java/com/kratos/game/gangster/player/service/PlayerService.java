package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.crud.ICrudService;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.game.gangster.player.domain.Player;

public interface PlayerService extends ICrudService<Long, Player> {
    /**
     * 根据openid和token登录
     * @param session 会话
     * @param openId 微信openid
     * @param token 验证token
     */
    void logon(IoSession session, String openId, String token);

    /**
     * 根据微信验证code登录
     * @param session 会话
     * @param code 信验证code登录
     */
    void logon(IoSession session, String code);

    /**
     * 每日重置
     * @param player 用户
     */
    void checkDailyReset(Player player);

    /**
     * 重命名
     * @param session 会话
     * @param name 名字
     */
    void editName(IoSession session, String name);
}
