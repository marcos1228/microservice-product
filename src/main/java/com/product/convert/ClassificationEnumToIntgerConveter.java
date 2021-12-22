package com.product.convert;

import org.modelmapper.AbstractConverter;

import com.product.domain.enums.ClassificationEnum;

public class ClassificationEnumToIntgerConveter extends AbstractConverter<ClassificationEnum, Integer> {

	@Override
	protected Integer convert(ClassificationEnum source) {
		return source.getId();
	}

}
