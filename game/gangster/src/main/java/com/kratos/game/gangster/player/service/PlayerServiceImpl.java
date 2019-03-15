package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.cache.CacheOptions;
import com.kratos.engine.framework.common.utils.IdGenerator;
import com.kratos.engine.framework.common.utils.StringHelper;
import com.kratos.engine.framework.crud.BaseCrudService;
import com.kratos.engine.framework.event.OnFire;
import com.kratos.engine.framework.net.socket.IoSession;
import com.kratos.engine.framework.net.socket.SessionCloseReason;
import com.kratos.engine.framework.net.socket.SessionManager;
import com.kratos.engine.framework.wechat.WechatManager;
import com.kratos.engine.framework.wechat.bean.ResAccessToken;
import com.kratos.game.gangster.config.GlobalData;
import com.kratos.game.gangster.player.PlayerContext;
import com.kratos.game.gangster.player.cache.TokenCacheContainer;
import com.kratos.game.gangster.player.domain.Player;
import com.kratos.game.gangster.player.event.PlayerLoginEvent;
import com.kratos.game.gangster.player.message.ReqPlayerEdit;
import com.kratos.game.gangster.player.message.ResPlayerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Component
public class PlayerServiceImpl extends BaseCrudService<Long, Player> implements PlayerService {
	public static final short CMD_REQ_LOGIN = 1; // 请求微信登录

    private TokenCacheContainer tokenCacheContainer;
	@Autowired
	private GlobalData globalData;
	@Autowired
	private RoleIdService roleIdService;
	@Autowired
    private WechatManager wechatManager;

	@PostConstruct
	public void cacheInit() {
        tokenCacheContainer = new TokenCacheContainer(this, CacheOptions.builder().build());
    }

    @Override
    public void logonWebSocket(IoSession session, String token) {
        Player player = findByToken(token);
        if(player != null) {
            SessionManager.getInstance().registerSession(player.getId(), session);
        } else {
            session.close(SessionCloseReason.INVALID_TOKEN);
        }
    }

    @Override
	public boolean login(String code) {
        String openId;
	    if(!"test".equalsIgnoreCase(code)) {
            ResAccessToken accessToken = wechatManager.getWechatService().getAccessToken(code);
            openId = accessToken.getOpenid();
        } else {
            openId = "test";
        }
        Player player = findByOpenId(openId);
        boolean isCreate = false;
        if(player == null) {
            player = register(openId);
            isCreate = true;
        }

        OnFire.fire(new PlayerLoginEvent(player));
        return isCreate;
	}

	@Transactional(readOnly = true)
	private Player findByOpenId(String openId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("wechatOpenid"), openId));
        TypedQuery<Player> query = em.createQuery(criteriaQuery);
        List<Player> resultList = query.getResultList();
        if(resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public Player findByToken(String token) {
        return tokenCacheContainer.get(token);
    }

    @Transactional(readOnly = true)
    public Player findByTokenFromDB(String token) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("token"), token));
        TypedQuery<Player> query = em.createQuery(criteriaQuery);
        List<Player> resultList = query.getResultList();
        if(resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
	public void checkDailyReset(Player player) {
		long resetTimestamp = globalData.dailyResetTimestamp;
		if (player.getLastDailyReset() < resetTimestamp) {
			player.setLastDailyReset(globalData.dailyResetTimestamp);
			cache(player.getId(), player);
			onDailyReset(player);
		}
	}

	@Override
	public void edit(ReqPlayerEdit reqPlayerEdit) {
        Player player = PlayerContext.getPlayer();
        if(StringHelper.isNoneBlank(reqPlayerEdit.getName())) {
            player.setName(reqPlayerEdit.getName());
        }
        if(StringHelper.isNoneBlank(reqPlayerEdit.getAvatarUrl())) {
            player.setAvatarUrl(reqPlayerEdit.getAvatarUrl());
        }
        cacheAndPersist(player.getId(), player);
	}

    @Override
    public ResPlayerProfile getProfile() {
        Player player = PlayerContext.getPlayer();
        return new ResPlayerProfile(player);
    }

    private Player register(String openid) {
        Player player = new Player();
        player.setId(IdGenerator.getNextId());
        player.setWechatOpenid(openid);
        player.setToken(UUID.randomUUID().toString());
        player.setRoleId(roleIdService.getNextRoleId());
        this.cacheAndPersist(player.getId(), player);
        return player;
    }

	/**
	 * 各个模块的业务日重置
	 *
	 * @param player 玩家
	 */
	private void onDailyReset(Player player) {
        System.err.println("各个模块的业务日重置");
	}
}
