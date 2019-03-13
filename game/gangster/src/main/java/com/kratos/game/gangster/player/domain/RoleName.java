package com.kratos.game.gangster.player.domain;

import com.kratos.engine.framework.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class RoleName extends BaseEntity<String> {
    public RoleName() {}

    public RoleName(String name) {
        this.setId(name);
    }
}
