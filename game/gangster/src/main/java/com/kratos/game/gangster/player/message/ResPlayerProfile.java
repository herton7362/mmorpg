package com.kratos.game.gangster.player.message;

import com.kratos.engine.framework.net.socket.annotation.MessageMeta;
import com.kratos.engine.framework.net.socket.message.Message;
import com.kratos.game.gangster.Modules;
import com.kratos.game.gangster.player.domain.Player;
import com.kratos.game.gangster.player.service.PlayerServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@MessageMeta(module = Modules.PLAYER, cmd = PlayerServiceImpl.CMD_RES_PROFILE)
public class ResPlayerProfile extends Message {
    private String name;
    private String roleId;
    private String avatarUrl;
    private int level;
    private long exp;
    private int diamond;
    private int money;
    private short stamina;

    public ResPlayerProfile() {}

    public ResPlayerProfile(Player player) {
        this.name = player.getName();
        this.roleId = player.getRoleId();
        this.avatarUrl = player.getAvatarUrl();
        this.level = player.getLevel();
        this.exp = player.getExp();
        this.diamond = player.getDiamond();
        this.money = player.getMoney();
        this.stamina = player.getStamina();
    }
}
