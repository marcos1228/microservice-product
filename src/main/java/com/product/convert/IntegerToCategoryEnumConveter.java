package com.product.convert;

import org.modelmapper.AbstractConverter;

import com.product.domain.enums.CategoryEnum;

public class IntegerToCategoryEnumConveter extends AbstractConverter<Integer, CategoryEnum> {

	@Override
	protected CategoryEnum convert(Integer source) {
		return CategoryEnum.getByid(source);
	}

}
