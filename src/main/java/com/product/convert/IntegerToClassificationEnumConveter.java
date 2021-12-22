package com.product.convert;

import org.modelmapper.AbstractConverter;

import com.product.domain.enums.ClassificationEnum;

public class IntegerToClassificationEnumConveter extends AbstractConverter<Integer, ClassificationEnum> {

	@Override
	protected ClassificationEnum convert(Integer source) {
		return ClassificationEnum.getById(source);
	}

}
