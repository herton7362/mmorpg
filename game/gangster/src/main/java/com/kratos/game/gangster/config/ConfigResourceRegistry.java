package com.kratos.game.gangster.config;

import com.kratos.engine.framework.scheme.support.BaseConfigResourceRegistry;
import com.kratos.engine.framework.scheme.support.ConfigData;
import com.kratos.game.gangster.auto.server.FirstNameConfig;
import com.kratos.game.gangster.auto.server.GameParamsConfig;
import com.kratos.game.gangster.auto.server.ItemConfig;
import com.kratos.game.gangster.auto.server.LastNameConfig;
import org.springframework.stereotype.Component;

@Component
public class ConfigResourceRegistry extends BaseConfigResourceRegistry {
    @Override
    protected Config config() {
        return Config
                .builder()
                .addJson(new ConfigData<>(ItemConfig.class, "Item.json"))
                .addJson(new GameParamConfigData(GameParamsConfig.class, "GameParams.json"))
                .addJson(new ConfigData<>(FirstNameConfig.class, "FirstName.json"))
                .addJson(new ConfigData<>(LastNameConfig.class, "LastName.json"))
                .build();
    }
}
