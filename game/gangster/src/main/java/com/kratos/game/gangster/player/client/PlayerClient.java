package com.kratos.game.gangster.player.client;

import com.kratos.engine.framework.net.socket.ProxyClient;
import com.kratos.engine.framework.net.socket.annotation.RequestMapping;
import com.kratos.game.gangster.Modules;
import com.kratos.game.gangster.player.message.ResPlayerLogin;
import com.kratos.game.gangster.player.service.PlayerService;
import org.springframework.stereotype.Component;

@Component
public interface PlayerClient extends ProxyClient {
    @RequestMapping(module = Modules.PLAYER, cmd = PlayerService.CMD_RES_LOGIN)
    void onPlayerLoginSuccess(ResPlayerLogin resPlayerLogin);
}
