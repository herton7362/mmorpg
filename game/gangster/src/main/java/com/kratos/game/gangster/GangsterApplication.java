package com.kratos.game.gangster;

import com.kratos.engine.framework.net.ServerNode;
import com.kratos.engine.framework.net.socket.transport.SocketServer;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Config Server 配置中心
 *
 * @author herton
 */
@Log4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.kratos.engine.framework", "com.kratos.game.gangster"})
public class GangsterApplication implements CommandLineRunner {
    private ServerNode socketServer;

    public static void main(String[] args) {
        SpringApplication.run(GangsterApplication.class, args);
    }

    private void start() throws Exception {
        socketServer = new SocketServer();
        socketServer.init(9999);
        socketServer.start();
    }

    private void stop() {
        try {
            socketServer.shutDown();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Override
    public void run(String... strings) throws Exception {
        GangsterApplication app = new GangsterApplication();
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));
        app.start();

    }
}
