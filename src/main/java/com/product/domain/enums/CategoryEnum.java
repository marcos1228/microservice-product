package com.product.domain.enums;

import lombok.Getter;

@Getter
public enum CategoryEnum {
	BEAUTY(1, "PRODUTO DE BELEZA"), 
	COSMETICS(2,"PRODUTO COMETICOS"), 
	FITNESS(3,"PRODUTO FITNESS"),
	HYGIENE(4,"PRODUTO HIGIENE"), 
	MOTHERANDBABY(5,"PRODUTO MAMAE E BEBE"),
	MEDICINE(6,"PRODUTO MEDICAMENTO"), 
	CHEERS(7,"PRODUTO DE SAUDE"), 
	IDOSO(8, "PRODUTO DE IDOSO"),
	NONE(0,"");

	private Integer id;
	private String name;

	CategoryEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static CategoryEnum getByid(Integer id) {
		for (CategoryEnum e : values()) {
			if(e.id.equals(id)) return e;
		}
		return NONE;

	}

}
