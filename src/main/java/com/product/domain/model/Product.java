package com.product.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Table(name = "TB_PRODUCT")
@Entity
@Data
public class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "NM_NAME")
	private String name;
	@Column(name = "DS_DESCRIPTION")
	private String description;
	@Column(name = "VL_PRICE")
	private BigDecimal price;
	@Column(name = "QT_THEAMOUNT")
	private BigDecimal theamount;
	@Column(name = "EN_CATEGORY")
	private Integer category;
	@Column(name = "EN_CLASSIFICATION")
	private Integer classification;
}
