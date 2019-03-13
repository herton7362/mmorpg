package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.crud.ICrudService;
import com.kratos.game.gangster.player.domain.RoleId;

public interface RoleIdService extends ICrudService<String, RoleId> {
    String getNextRoleId();
}
