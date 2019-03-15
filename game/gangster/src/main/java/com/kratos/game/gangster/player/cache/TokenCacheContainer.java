package com.kratos.game.gangster.player.cache;

import com.kratos.engine.framework.cache.AbstractCacheContainer;
import com.kratos.engine.framework.cache.CacheOptions;
import com.kratos.game.gangster.player.domain.Player;
import com.kratos.game.gangster.player.service.PlayerService;

public class TokenCacheContainer extends AbstractCacheContainer<String, Player> {

	private PlayerService playerService;

	public TokenCacheContainer(PlayerService playerService, CacheOptions p) {
		super(p);
		this.playerService = playerService;
	}

	@Override
	public Player loadFromDb(String token) {
		Player player = playerService.findByTokenFromDB(token);
		if (player != null) {
			player.markPersistent();
		}
		return player;
	}

}
