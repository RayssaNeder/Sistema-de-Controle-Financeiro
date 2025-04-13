package com.xpto.demo.service.error;

import lombok.Data;

@Data
public class Error {
	private Integer code;
	private String message;

	public Error code(Integer code) {
		this.code = code;
		return this;
	}

	public Error message(String message) {
		this.message = message;
		return this;
	}
}
