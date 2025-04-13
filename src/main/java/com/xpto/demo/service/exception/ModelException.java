package com.xpto.demo.service.exception;

import com.xpto.demo.service.error.Error;

import lombok.Getter;

public class ModelException extends RuntimeException {

	/** */
	private static final long serialVersionUID = -2015234875153160420L;

	@Getter
	private final transient Error model;

	public ModelException(Error model, Throwable cause) {
		super(model.getMessage(), cause);
		this.model = model;
	}

	public ModelException(Error model) {
		this(model, null);
	}
}
