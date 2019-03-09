package com.kratos.game.gangster.player.facade;

import com.kratos.engine.framework.gm.GmHandler;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.engine.framework.net.socket.ProxyClient;
import com.kratos.engine.framework.net.socket.SessionManager;
import com.kratos.engine.framework.net.socket.annotation.MessageMeta;
import com.kratos.engine.framework.net.socket.annotation.RequestMapping;
import com.kratos.game.gangster.GmCommands;
import com.kratos.game.gangster.Modules;
import com.kratos.game.gangster.player.client.PlayerClient;
import com.kratos.game.gangster.player.message.ReqPlayerLogin;
import com.kratos.game.gangster.player.message.ResPlayerLogin;
import com.kratos.game.gangster.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerFacade {
	@Autowired
    private PlayerClient playerClient;

	@RequestMapping(module = Modules.PLAYER, cmd = PlayerService.CMD_REQ_LOGIN)
	public void reqPlayerLogin(IoSession session, @MessageMeta ReqPlayerLogin request) {
		long playerId = request.getPlayerId();
		System.out.println("角色[" + playerId + "]登录");
		SessionManager.getInstance().registerSession(playerId, session);
        playerClient.onPlayerLoginSuccess(new ResPlayerLogin());
	}

	@GmHandler(cmd = GmCommands.LEVEL)
	public void gmSetLevel(long playerId, int level) {
		System.err.println("[gm]修改玩家等级为" + level);
	}
}
