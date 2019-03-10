package com.kratos.game.gangster.player.event;

import com.kratos.engine.framework.event.BaseEvent;
import com.kratos.game.gangster.player.domain.Player;

public class PlayerLevelUpEvent implements BaseEvent {
	
	private Player player;
	
	public PlayerLevelUpEvent(Player player) {
		this.player = player;
	}

    @Override
    public Player getOwner() {
        return player;
    }
}