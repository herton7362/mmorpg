package com.kratos.game.gangster.player.message;

import com.kratos.engine.framework.net.socket.message.Message;


public class ResPlayerLogin extends Message {
	
	private byte status;

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "角色登录 [status=" + status + "]";
	}
}
