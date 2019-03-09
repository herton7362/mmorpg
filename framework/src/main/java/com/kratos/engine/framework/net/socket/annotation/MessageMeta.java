package com.kratos.engine.framework.net.socket.annotation;

import com.kratos.engine.framework.net.socket.message.Message;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * The annotation type is used in {@link Message}
 * to specify module and cmd of the given message.
 * @author herton
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface MessageMeta {

}
