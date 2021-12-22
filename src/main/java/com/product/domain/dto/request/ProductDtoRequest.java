package com.product.domain.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.product.domain.enums.CategoryEnum;
import com.product.domain.enums.ClassificationEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductDtoRequest {

	@ApiModelProperty(value = "Nome do Produto", example = "torsilax", required = true)
	@NotBlank(message = "{attribute.name.notBlank}")
	@NotNull(message = "{attribute.name.notNull}")
	@NotEmpty(message = "{attribute.name.notEmpty}")
	@Size(max = 50, min = 5, message = "{attribute.name.size}")
	private String name;

	@ApiModelProperty(value = "Descrição do Produto", example = "apresenta uma composição relaxante muscular, anti-inflamatória e analgésica", required = true)
	@NotBlank(message = "{attribute.description.notBlanck}")
	@NotNull(message = "{attribute.description.notNull}")
	@NotEmpty(message = "{attribute.description.notEmpty}")
	@Size(max = 50, min = 5, message = "{attribute.description.size}")
	private String description;

	@ApiModelProperty(value = "Preço do produto", example = "5.00", required = true)
	@NotNull(message = "{attribute.price.notNull}")
	@Positive(message = "{attribute.price.positive}")
	private BigDecimal price;

	@ApiModelProperty(value = "Quantidade do produto", example = "10", required = true)
	@NotNull(message = "{attribute.theamount.notNull}")
	@Positive(message = "{ttribute.theamount.positive}")
	private BigDecimal theamount;
	@ApiModelProperty(value = "Categoria do produto", example = "MEDICAMENTO", required = true)
	@NotNull(message = "{attribute.category.notNull}")
	private CategoryEnum category;
	@ApiModelProperty(value = "Clasificação do produto", example = "GENERICO", required = true)
	@NotNull(message = "{attribute.classification.notNull}")
	private ClassificationEnum classification;

}
