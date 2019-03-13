package com.kratos.game.gangster.player.domain;

import com.kratos.engine.framework.db.LongKeyEntity;
import com.kratos.engine.framework.net.socket.task.IDispatch;
import com.kratos.game.gangster.common.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 玩家实体
 *
 * @author herton
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Player extends LongKeyEntity {
    private static final long serialVersionUID = 8913056963732639062L;

    @Column(length = 100)
    private String loginName;
    @Column(length = 500)
    private String password;
    @Column(length = 500)
    private String name; // 姓名
    @Column(length = 36)
    private String roleId; // 角色ID最长9位，由数字和大写英文字母构成。
    @Column(nullable = false, columnDefinition = CommonConstant.DEFAULT_0)
    private int level; // 等级
    @Column(nullable = false, columnDefinition = CommonConstant.DEFAULT_0)
    private long exp; // 经验
    @Column(length = 36)
    private String token; // 权限
    @Column(nullable = false, columnDefinition = CommonConstant.DEFAULT_0)
    private long lastDailyReset; // 上一次每日重置的时间戳
    @Column(nullable = false, columnDefinition = CommonConstant.DEFAULT_0)
    private int diamond; // 钻石
    @Column(nullable = false, columnDefinition = CommonConstant.DEFAULT_0)
    private int money; // 钞票
    @Column(nullable = false, columnDefinition = CommonConstant.DEFAULT_0)
    private short stamina; // 体力
    @Column(length = 30)
    private String wechatOpenid; // 微信openid

    @Override
    public String toString() {
        return "Player [id=" + getId() + ", name=" + name + "]";
    }
}
