package com.kratos.game.gangster.player.message;

import com.kratos.engine.framework.net.socket.message.Message;

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
