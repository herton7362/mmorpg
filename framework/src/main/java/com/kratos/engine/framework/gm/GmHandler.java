package com.kratos.engine.framework.gm;

import java.lang.annotation.*;

/**
 * gm命令处理器
 * 
 * @author kingston
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GmHandler {
	
	String cmd();

}
