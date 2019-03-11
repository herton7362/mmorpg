package com.kratos.game.gangster.player.message;

import com.kratos.engine.framework.net.socket.annotation.MessageMeta;
import com.kratos.engine.framework.net.socket.message.Message;
import com.kratos.game.gangster.Modules;
import com.kratos.game.gangster.player.service.PlayerService;

@MessageMeta(module = Modules.PLAYER, cmd = PlayerService.CMD_REQ_LOGIN)
public class ReqPlayerLogin extends Message {
	
	private long playerId;

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	@Override
	public String toString() {
		return "ReqPlayerLogin [playerId=" + playerId + "]";
	}
}
