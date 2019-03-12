package com.kratos.game.gangster.player.facade;

import com.google.common.eventbus.Subscribe;
import com.kratos.engine.framework.common.utils.IdGenerator;
import com.kratos.engine.framework.event.OnFire;
import com.kratos.engine.framework.gm.GmHandler;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.engine.framework.net.socket.SessionManager;
import com.kratos.engine.framework.net.socket.annotation.MessageHandler;
import com.kratos.game.gangster.GmCommands;
import com.kratos.game.gangster.player.domain.Player;
import com.kratos.game.gangster.player.event.PlayerLoginEvent;
import com.kratos.game.gangster.player.message.ReqPlayerLogin;
import com.kratos.game.gangster.player.message.ResPlayerLogin;
import com.kratos.game.gangster.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerFacade {
    @Autowired
    private PlayerService playerService;

	@MessageHandler
	public void reqPlayerLogin(IoSession session, ReqPlayerLogin request) {
		long playerId = request.getPlayerId();
		System.out.println("角色[" + playerId + "]登录");
		SessionManager.getInstance().registerSession(playerId, session);
        Player player = new Player();
        player.setLevel(22);
        playerService.cacheAndPersist(IdGenerator.getNextId(), player);
        session.sendPacket(new ResPlayerLogin());
        OnFire.fire(new PlayerLoginEvent(new Player()));
	}

	@GmHandler(cmd = GmCommands.LEVEL)
	public void gmSetLevel(long playerId, int level) {
		System.err.println("[gm]修改玩家等级为" + level);
	}

	@GmHandler(cmd = GmCommands.CONFIG)
	public void gmSetConfig(long playerId, String cmd, String file) {
	}

	@Subscribe
	public void onPlayerLevelUp(PlayerLoginEvent loginEvent) {

		System.err.println("检测到登录事件");
	}
}
