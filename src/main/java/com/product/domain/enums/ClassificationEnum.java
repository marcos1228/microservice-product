package com.product.domain.enums;

import lombok.Getter;

@Getter
public enum ClassificationEnum {
	REFERENCE(1, "TIPO REFERENCIA"), 
	SIMILIAR(2, "TIPO SIMILIAR"), 
	GENERIC(3, "TIPO GENERICO"), 
	NONE(0, "");

	private Integer id;
	private String name;

	private ClassificationEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static ClassificationEnum getById(Integer id) {
		for (ClassificationEnum e : values()) {
			if (e.id.equals(id)) {
				return e;
			}
		}
		return NONE;

	}

}
