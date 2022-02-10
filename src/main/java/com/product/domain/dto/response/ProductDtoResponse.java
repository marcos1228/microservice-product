package com.product.domain.dto.response;

import java.math.BigDecimal;

import com.product.domain.enums.CategoryEnum;
import com.product.domain.enums.ClassificationEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductDtoResponse {
	
	@ApiModelProperty(value = "Id do produto", example = "123")
	private Long id;
	
	@ApiModelProperty(value = "Nome do produto", example = "DorFlex")
	private String name;
	
	@ApiModelProperty(value = "Descrição do produto", example = "Relachante muscular")
	private String description;
	
	@ApiModelProperty(value = "Preço do produto", example = "5")
	private BigDecimal price;
	
	@ApiModelProperty(value = "Quantidade do produto", example = "50")
	private BigDecimal theamount;
	
	@ApiModelProperty(value = "Categoria do produto", example = "Medicamneto")
	private CategoryEnum category;
	
	@ApiModelProperty(value = "Clasificação do produto", example = "Genérico")
	private ClassificationEnum classification;
}
