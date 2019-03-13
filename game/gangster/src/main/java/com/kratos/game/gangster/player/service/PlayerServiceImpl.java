package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.common.utils.IdGenerator;
import com.kratos.engine.framework.crud.BaseCrudService;
import com.kratos.engine.framework.event.OnFire;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.game.gangster.config.GlobalData;
import com.kratos.game.gangster.player.domain.Player;
import com.kratos.game.gangster.player.event.PlayerLoginEvent;
import com.kratos.game.gangster.player.message.ResPlayerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlayerServiceImpl extends BaseCrudService<Long, Player> implements PlayerService {
	public static final short CMD_REQ_WECHAT_OPENID_LOGIN = 1; // 请求openid登录
	public static final short CMD_REQ_WECHAT_CODE_LOGIN = 2; // 请求code登录
	public static final short CMD_REQ_EDIT_NAME = 3; // 请求修改名字
	public static final short CMD_RES_LOGIN = 201; // 登录响应

	@Autowired
	private GlobalData globalData;
	@Autowired
	private RoleIdService roleIdService;
	@Autowired
	private RoleNameService roleNameService;

	@Override
	public void logon(IoSession session, String openId, String token) {
//		SessionManager.getInstance().registerSession(playerId, session);
		OnFire.fire(new PlayerLoginEvent(new Player()));
        Player player = register(openId);
        ResPlayerLogin resPlayerLogin = new ResPlayerLogin();
		resPlayerLogin.setRoleId(player.getRoleId());
		session.sendPacket(resPlayerLogin);
	}

	@Override
	public void logon(IoSession session, String code) {
//		SessionManager.getInstance().registerSession(playerId, session);
	}

    @Override
	public void checkDailyReset(Player player) {
		long resetTimestamp = globalData.dailyResetTimestamp;
		if (player.getLastDailyReset() < resetTimestamp) {
			player.setLastDailyReset(globalData.dailyResetTimestamp);
			cache(player.getId(), player);
			onDailyReset(player);
		}
	}

	@Override
	public void editName(IoSession session, String name) {

	}

	private Player register(String openid) {
        Player player = new Player();
        player.setId(IdGenerator.getNextId());
        player.setWechatOpenid(openid);
        player.setToken(UUID.randomUUID().toString());
        player.setRoleId(roleIdService.getNextRoleId());
        player.setName(roleNameService.getNextRoleName());
        this.cacheAndPersist(player.getId(), player);
        return player;
    }

	/**
	 * 各个模块的业务日重置
	 *
	 * @param player
	 */
	private void onDailyReset(Player player) {
        System.err.println("各个模块的业务日重置");
	}
}
