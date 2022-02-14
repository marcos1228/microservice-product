package com.product.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.product.domain.dto.request.ProductDtoRequest;
import com.product.domain.dto.request.ProductUpdateDtoRequest;
import com.product.domain.dto.response.ProductDtoResponse;
import com.product.domain.model.Product;
import com.product.exception.BusinessException;
import com.product.exception.MessageBuilder;
import com.product.repository.ProductRepository;
import com.product.sender.ProductDeleteQueueSender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductDeleteQueueSender productInativeQueueSender;

	@Autowired
	private MessageBuilder messageBuilder;

	public ProductDtoResponse getProductById(Long id) {
		log.info("Method={} idProduct={}", "getProductById", id);
		Product product = repository.findById(id)
				.orElseThrow(() -> new BusinessException(messageBuilder.getMessage("message.exception")));
		return modelMapper.map(product, ProductDtoResponse.class);
	}

	@Transactional
	public ProductDtoResponse save(ProductDtoRequest request) {
		log.info("Method={}", "save");
		Product product = modelMapper.map(request, Product.class);
		return modelMapper.map(repository.save(product), ProductDtoResponse.class);
	}

	@Transactional
	public ProductDtoResponse update(Long id, ProductUpdateDtoRequest dto) {
		log.info("Method={} idProduct", "update", id);
		Product product = repository.findById(id)
				.orElseThrow(() -> new BusinessException(messageBuilder.getMessage("message.exception")));
		modelMapper.map(dto, product);
		return modelMapper.map(repository.save(product), ProductDtoResponse.class);
	}

	public Page<ProductDtoResponse> findByName(String name, Pageable pageable) {
		log.info("Method={}", "findByName");
		Page<Product> list = repository.findByName(name, pageable);
		return list.map(item -> modelMapper.map(item, ProductDtoResponse.class));
	}

	@Transactional
	public void delete(Long id) {
		log.info("Method={} idProduct={}", "delete", id);
		Product product = repository.findById(id)
				.orElseThrow((() -> new BusinessException(messageBuilder.getMessage("message.exception"))));
		repository.delete(product);
		this.productInativeQueueSender.sendMessage(product);
	}
}
