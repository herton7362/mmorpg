package com.kratos.game.gangster.player.facade;

import com.google.common.eventbus.Subscribe;
import com.kratos.engine.framework.gm.GmHandler;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.engine.framework.net.socket.annotation.MessageHandler;
import com.kratos.game.gangster.GmCommands;
import com.kratos.game.gangster.player.event.PlayerLoginEvent;
import com.kratos.game.gangster.player.message.ReqPlayerEditName;
import com.kratos.game.gangster.player.message.ReqPlayerWechatCodeLogin;
import com.kratos.game.gangster.player.message.ReqPlayerWechatOpenIdLogin;
import com.kratos.game.gangster.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerFacade {
    @Autowired
    private PlayerService playerService;

	@MessageHandler
	public void wechatOpenidLogin(IoSession session, ReqPlayerWechatOpenIdLogin request) {
		playerService.logon(session, request.getOpenid(), request.getToken());
	}

    @MessageHandler
    public void wechatCodeLogin(IoSession session, ReqPlayerWechatCodeLogin request) {
        playerService.logon(session, request.getCode());
    }

    @MessageHandler
    public void editName(IoSession session, ReqPlayerEditName request) {
        playerService.editName(session, request.getName());
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
