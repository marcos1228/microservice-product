package com.product.domain.dto.request;

import lombok.Data;

@Data
public class UserUpdateDtoRequest {
	
	private String name;
	
	private String password;
}