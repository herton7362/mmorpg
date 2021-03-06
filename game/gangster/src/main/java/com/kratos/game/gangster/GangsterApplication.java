package com.kratos.game.gangster;

import com.kratos.engine.framework.base.SpringContext;
import com.kratos.engine.framework.net.ServerNode;
import com.kratos.engine.framework.net.socket.transport.SocketServer;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Config Server 配置中心
 *
 * @author herton
 */
@Log4j
@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = {"com.kratos.engine.framework", "com.kratos.game.gangster"})
@ComponentScan(basePackages = {"com.kratos.engine.framework", "com.kratos.game.gangster"})
public class GangsterApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(GangsterApplication.class, args);
    }

    private void stop() {
        try {
            SpringContext.getBean(SocketServer.class).shutDown();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Override
    public void run(String... strings) throws Exception {
        GangsterApplication app = new GangsterApplication();
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));
    }
}
