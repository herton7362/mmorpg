package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.crud.BaseCrudService;
import com.kratos.game.gangster.player.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerService extends BaseCrudService<Long, Player> {
	public static final short CMD_REQ_LOGIN = 1;
	public static final short CMD_RES_LOGIN = 201;


}
