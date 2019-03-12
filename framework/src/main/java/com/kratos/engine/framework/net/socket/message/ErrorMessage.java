package com.kratos.engine.framework.net.socket.message;

import com.kratos.engine.framework.net.socket.annotation.MessageMeta;
import lombok.Data;
import lombok.NonNull;

@Data
@MessageMeta
public class ErrorMessage extends Message {
	@NonNull
	private String message;

	public ErrorMessage() {

	}

	public ErrorMessage(String message) {
		this.message = message;
	}
}
