package com.kratos.game.gangster.player.facade;

import com.google.common.eventbus.Subscribe;
import com.kratos.engine.framework.gm.GmHandler;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.engine.framework.net.socket.annotation.MessageHandler;
import com.kratos.game.gangster.GmCommands;
import com.kratos.game.gangster.player.event.PlayerLoginEvent;
import com.kratos.game.gangster.player.message.ReqPlayerLogin;
import com.kratos.game.gangster.player.service.PlayerService;
import com.kratos.game.gangster.player.service.RoleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerFacade {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private RoleNameService roleNameService;

	@MessageHandler
	public void wechatOpenidLogin(IoSession session, ReqPlayerLogin request) {
		playerService.logonWebSocket(session, request.getToken());
	}

    @GmHandler(cmd = GmCommands.LEVEL)
	public void gmSetLevel(long playerId, int level) {
		System.err.println("[gm]修改玩家等级为" + level);
	}

	@Subscribe
	public void onPlayerLevelUp(PlayerLoginEvent loginEvent) {

		System.err.println("检测到登录事件");
	}
}
