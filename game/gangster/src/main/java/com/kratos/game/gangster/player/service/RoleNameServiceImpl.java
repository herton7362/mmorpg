package com.kratos.game.gangster.player.service;

import com.kratos.engine.framework.crud.BaseCrudService;
import com.kratos.game.gangster.auto.server.FirstNameConfig;
import com.kratos.game.gangster.auto.server.LastNameConfig;
import com.kratos.game.gangster.config.ConfigResourceRegistry;
import com.kratos.game.gangster.player.domain.RoleId;
import com.kratos.game.gangster.player.domain.RoleName;
import io.netty.util.internal.ConcurrentSet;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Log4j
@Component
public class RoleNameServiceImpl extends BaseCrudService<String, RoleName> implements RoleNameService {
    private volatile Set<String> roleNames = new ConcurrentSet<>();
    @Autowired
    private ConfigResourceRegistry configResourceRegistry;

    @PostConstruct
    public void init() {
        super.init();
        List<RoleName> roleNames = findAll();
        roleNames.parallelStream().forEach(roleName -> this.roleNames.add(roleName.getId()));
    }

    @Override
    public String getNextRoleName() {
        String roleName = "%s%s";
        List<LastNameConfig> lastNames = configResourceRegistry.getConfig(LastNameConfig.class).getList();
        List<FirstNameConfig> firstNames = configResourceRegistry.getConfig(FirstNameConfig.class).getList();
        String lastName = lastNames.get(RandomUtils.nextInt(0, lastNames.size())).getLastName();
        String firstName = firstNames.get(RandomUtils.nextInt(0, firstNames.size())).getFirstName();
        roleName = String.format(roleName, lastName, firstName);
        if (roleNames.contains(roleName)) {
            log.error("生成roleName重复：" + roleName);
            return getNextRoleName();
        }
        roleNames.add(roleName);
        cacheAndPersist(roleName, new RoleName(roleName));
        return roleName;
    }
}
