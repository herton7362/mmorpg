package com.kratos.game.gangster.player.domain;

import com.kratos.engine.framework.db.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 玩家实体
 * @author herton
 */
@Data
@Entity
public class Player extends BaseEntity {
	private static final long serialVersionUID = 8913056963732639062L;

	@Column(length = 100)
	private String loginName;
	@Column(length = 500)
	private String password;
	@Column(length = 500)
	private String name;
	@Column(length = 3)
	private int level;
	@Column
	private long exp;

	@Override
	public String toString() {
		return "Player [id=" + getId() + ", name=" + name + "]";
	}

}
