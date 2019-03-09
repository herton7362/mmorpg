package com.kratos.engine.framework.gm.message;

import com.kratos.engine.framework.net.socket.message.Message;

public class ReqGmCommand extends Message {
	
	private String params;

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "ReqGmCommand [params=" + params + "]";
	}
	

}
