package com.kratos.game.gangster.config;

import com.kratos.engine.framework.scheme.support.BaseConfigResourceRegistry;
import com.kratos.engine.framework.scheme.support.ConfigData;
import com.kratos.game.gangster.auto.server.ItemConfig;
import org.springframework.stereotype.Component;

@Component
public class ConfigResourceRegistry extends BaseConfigResourceRegistry {
    @Override
    protected Config config() {
        return Config
                .builder()
                .addJson(ConfigData.of(ItemConfig.class, "item.json"))
                .build();
    }
}
