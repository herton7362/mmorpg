package com.kratos.engine.framework.net.socket.message;


import com.kratos.engine.framework.net.socket.annotation.MessageMeta;

/**
 * 抽象消息定义
 */
public abstract class Message {
    /**
     * messageMeta, module of message
     * @return
     */
    public short getModule() {
        MessageMeta annotation = getClass().getAnnotation(MessageMeta.class);
        if (annotation != null) {
            return annotation.module();
        }
        return 0;
    }

    /**
     * messageMeta, subType of message
     * @return
     */
    public short getCmd() {
        MessageMeta annotation = getClass().getAnnotation(MessageMeta.class);
        if (annotation != null) {
            return annotation.cmd();
        }
        return 0;
    }
}
