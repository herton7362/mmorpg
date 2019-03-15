package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.crud.ICrudService;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.game.gangster.player.domain.Player;
import com.kratos.game.gangster.player.message.ReqPlayerEdit;
import com.kratos.game.gangster.player.message.ResPlayerProfile;

public interface PlayerService extends ICrudService<Long, Player> {
    /**
     * 根据微信验证code登录
     * @param session 会话
     * @param token 用户token
     */
    void logonWebSocket(IoSession session, String token);

    /**
     * 根据微信验证code登录
     * @param code 信验证code登录
     * @return 是否为创建用户
     */
    boolean login(String code);

    /**
     * 根据token查询
     * @param token token
     * @return 用户
     */
    Player findByToken(String token);

    /**
     * 根据token查询
     * @param token token
     * @return 用户
     */
    Player findByTokenFromDB(String token);

    /**
     * 每日重置
     * @param player 用户
     */
    void checkDailyReset(Player player);

    /**
     * 重命名
     * @param reqPlayerEdit 用户信息
     */
    void edit(ReqPlayerEdit reqPlayerEdit);

    /**
     * 获取用户信息
     */
    ResPlayerProfile getProfile();
}
