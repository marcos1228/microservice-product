package com.product.convert;

import org.modelmapper.AbstractConverter;

import com.product.domain.enums.CategoryEnum;

public class CategoryEnumToIntegerConvet extends AbstractConverter<CategoryEnum, Integer>{

	@Override
	protected Integer convert(CategoryEnum source) {
		return source.getId();
	}

}
