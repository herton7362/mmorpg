package com.kratos.game.gangster.player.message;

import com.kratos.engine.framework.net.socket.annotation.MessageMeta;
import com.kratos.engine.framework.net.socket.message.Message;
import com.kratos.game.gangster.Modules;
import com.kratos.game.gangster.player.service.PlayerServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@MessageMeta(module = Modules.PLAYER, cmd = PlayerServiceImpl.CMD_REQ_WECHAT_CODE_LOGIN)
public class ReqPlayerWechatCodeLogin extends Message {
	private String code;
}
