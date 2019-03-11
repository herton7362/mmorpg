package com.kratos.engine.framework.net.socket;

import com.alibaba.fastjson.JSON;
import com.kratos.engine.framework.net.socket.message.Message;
import com.kratos.engine.framework.net.socket.message.WebSocketFrame;
import com.kratos.engine.framework.net.socket.task.IDispatch;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 链接的会话
 * @author herton
 */
@Data
public class IoSession {
	
	private static final Logger logger = LoggerFactory.getLogger(IoSession.class);

	/** 网络连接channel */
	private Channel channel;

	/** ip地址 */
	private String ipAddr;

	private boolean reconnected;

	private long playerId;

	private IDispatch dispatcher;

	private Map<String, Object> attributes;

    public IoSession(Channel channel) {
        this.channel = channel;
        this.ipAddr = ChannelUtils.getIp(channel);
        this.dispatcher = anonymousDispatcher;
    }

    /**
     * 向客户端发送消息
     * @param packet 消息
     */
    public void sendPacket(Message packet) {
        if (packet == null) {
            return;
        }
        WebSocketFrame frame = WebSocketFrame.valueOf(packet);
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(frame)));
    }

    /**
     * 关闭session
     * @param reason {@link SessionCloseReason}
     */
    public void close(SessionCloseReason reason) {
        try{
            if (this.channel == null) {
                return;
            }
            if (channel.isOpen()) {
                channel.close();
                logger.info("close session[{}], reason is {}", playerId, reason);
            }else{
                logger.info("session[{}] already close, reason is {}", playerId, reason);
            }
        }catch(Exception e){
        }
    }

    /**
     * 匿名分发器，用于角色未登录
     */
    static IDispatch anonymousDispatcher = () -> 0;
}
