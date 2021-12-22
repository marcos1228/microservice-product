package com.product.feature;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.product.domain.dto.request.ProductDtoRequest;
import com.product.domain.dto.request.ProductUpdateDtoRequest;
import com.product.domain.dto.response.ProductDtoResponse;
import com.product.domain.enums.CategoryEnum;
import com.product.domain.enums.ClassificationEnum;
import com.product.domain.model.Product;

import lombok.var;

public class ScenarioFactory {

	public static Product newProduct() {
		var product = new Product();
		product.setId(1L);
		product.setName("Dorflex");
		product.setDescription("Relachante muscular para dores abnorminais");
		product.setCategory(1);
		product.setPrice(BigDecimal.valueOf(5.0));
		product.setTheamount(BigDecimal.valueOf(5.0));
		product.setClassification(1);
		return product;
	}

	

	public static ProductDtoRequest productDtoRequest() {
		var product = new ProductDtoRequest();
		product.setName("Dorflex");
		product.setDescription("Relachante muscular para dores abnorminais");
		product.setCategory(CategoryEnum.BEAUTY);
		product.setPrice(BigDecimal.valueOf(5.0));
		product.setTheamount(BigDecimal.valueOf(5.0));
		product.setClassification(ClassificationEnum.REFERENCE);
		return product;

	}


	public static ProductDtoResponse productDtoResponse() {
		var product = new ProductDtoResponse();
		product.setId(1L);
		return product;

	}
	

	public static Pageable newPageable() {
		Pageable pageable = Pageable.ofSize(10);
		return pageable;
	}

	public static Product product() {
		var product = new Product();
		return product;
	}

	public static ProductDtoRequest productDTO() {
		var product = new ProductDtoRequest();
		return product;
	}

	public static Page<Product> newPage() {
		List<Product> product = new ArrayList<>();
		product.add(product());
		Page<Product> page = new PageImpl<>(product);
		return page;

	}

	public static ProductUpdateDtoRequest newProductRequestDtoUpdate() {
		var product = new ProductUpdateDtoRequest();
		product.setName("Creme de mão");
		product.setDescription("Creme para as mãos");
		product.setPrice(BigDecimal.valueOf(5));
		return product;

	}

	public static Optional<Product> newOptionalProduct() {
		return Optional.of(newProduct());
	}
	
	public static Optional<Product> optionalProductNulo() {
		return Optional.empty();
	}


	public static ProductDtoRequest newProductDtoRequest() {
	 var  productDtoRequest = new ProductDtoRequest();
	   productDtoRequest.setName("Dorflex");
	   productDtoRequest.setDescription("Relachante muscular para dores abnorminais");
	   productDtoRequest.setTheamount(BigDecimal.valueOf(5.0));
	    return productDtoRequest;
	}



}
