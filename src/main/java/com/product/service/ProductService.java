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
		log.info("id [{}] will be for search for!", id);
		Product product = repository.findById(id).orElseThrow(() -> new BusinessException(messageBuilder.getMessage("message.exception")));
		log.warn("The return [{}]" , product);
		return modelMapper.map(product, ProductDtoResponse.class);
	}

	@Transactional
	public ProductDtoResponse save(ProductDtoRequest request) {
		Product product = modelMapper.map(request, Product.class);
		log.info("Successfully saved!");
		return modelMapper.map(repository.save(product), ProductDtoResponse.class);
	}

	@Transactional
	public ProductDtoResponse update(Long id, ProductUpdateDtoRequest dto) {
		Product product = repository.findById(id).orElseThrow(() -> new BusinessException(messageBuilder.getMessage("message.exception")));
		log.info("id [{}] will be for updated by", id);
		modelMapper.map(dto, product);
		log.warn("The updated [{}]" , product);
		return modelMapper.map(repository.save(product), ProductDtoResponse.class);
	}

	public Page<ProductDtoResponse> findByName(String name, Pageable pageable) {
		Page<Product> list = repository.findByName(name, pageable);
		log.info("Offers will be listed in pageable form.");
		return list.map(item -> modelMapper.map(item, ProductDtoResponse.class));
	}

	@Transactional
	public void delete(Long id) {
		Product product = repository.findById(id).orElseThrow((() -> new BusinessException(messageBuilder.getMessage("message.exception"))));
		log.info("The id delete [{}]", id);
		repository.delete(product);
		log.warn("The product delete [{}]", product);
		this.productInativeQueueSender.sendMessage(product);
	}
}
