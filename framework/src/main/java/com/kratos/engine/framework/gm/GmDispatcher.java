package com.kratos.engine.framework.gm;

import com.kratos.engine.framework.net.socket.message.CmdExecutor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Log4j
@Component
public class GmDispatcher {
	private static final GmDispatcher instance = new GmDispatcher();

	public static GmDispatcher getInstance() {
	    return instance;
    }
	
	private ConversionService conversionService = new DefaultConversionService();
	
	/** [methodName, CmdExecutor] */
	private static final Map<String, CmdExecutor> GM_HANDLERS = new HashMap<>();
	
	public void registerHandler(String key, CmdExecutor executor) {
		GM_HANDLERS.put(key, executor);
	}
	
	public void dispatch(long playerId, String[] args) {
		String method = args[0];
		
		for (Map.Entry<String, CmdExecutor> entry : GM_HANDLERS.entrySet()) {
			String targetMethod = entry.getKey();
			if (method.equalsIgnoreCase(targetMethod)) {
				CmdExecutor executor = entry.getValue();
				try {
					// 动态参数要求只能有两个参数，第二个需要是 String[] 类型
                    Object[] methodParams = new Object[args.length];
                    methodParams[0] = playerId;
                    for (int i = 1; i < methodParams.length; i++) {
                        methodParams[i] = conversionService.convert(args[i], executor.getParams()[i]);
                    }
                    executor.getMethod().invoke(executor.getHandler(), methodParams);
				} catch (Exception e) {
					log.error("", e);
				}
				break;
			}
		}
	}
	

}
