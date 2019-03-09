package com.kratos.engine.framework.gm.facade;

import com.kratos.engine.framework.net.Modules;
import com.kratos.engine.framework.gm.GmDispatcher;
import com.kratos.engine.framework.gm.message.ReqGmCommand;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.engine.framework.net.socket.annotation.MessageMeta;
import com.kratos.engine.framework.net.socket.annotation.RequestMapping;
import com.kratos.engine.framework.net.socket.task.DefaultMessageDispatcher;

public class GmFacade {
	
	private GmDispatcher gmDispatcher = GmDispatcher.getInstance();

	
	@RequestMapping(module = Modules.GM, cmd = 1)
	public void reqGmExec(IoSession session, @MessageMeta ReqGmCommand req) {
		long playerId = session.getPlayerId();
		String[] params = req.getParams().split("\\s+");
		gmDispatcher.dispatch(playerId, params);
	}

	public static void registerGMHandler() {
        DefaultMessageDispatcher.getInstance().registerMethodInvoke(new GmFacade());
	}
}
