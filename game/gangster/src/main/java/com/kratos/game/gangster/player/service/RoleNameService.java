package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.crud.ICrudService;
import com.kratos.game.gangster.player.domain.RoleName;

public interface RoleNameService extends ICrudService<String, RoleName> {
    String getNextRoleName();
}
