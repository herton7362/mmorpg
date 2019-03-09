package com.kratos.engine.framework.config;

import com.kratos.engine.framework.net.socket.task.DefaultMessageDispatcher;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MessageHandlerClassLoader
	implements BeanPostProcessor, Ordered {

	private DefaultMessageDispatcher messageDispatcher = DefaultMessageDispatcher.getInstance();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//扫描所有controller的消息处理器

		try {
            messageDispatcher.registerMethodInvoke(bean);
		}catch(Exception e) {
		}

		return bean;
	}


	private String buildKey(short module, short cmd) {
		return module + "_" + cmd;
	}


	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
