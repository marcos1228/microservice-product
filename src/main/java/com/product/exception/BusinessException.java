package com.product.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BusinessException(String msg) {
		super(msg);
	}
}
