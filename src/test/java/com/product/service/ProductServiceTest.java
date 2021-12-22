package com.product.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import com.product.domain.dto.response.ProductDtoResponse;
import com.product.domain.model.Product;
import com.product.exception.BusinessException;
import com.product.exception.MessageBuilder;
import com.product.feature.ScenarioFactory;
import com.product.repository.ProductRepository;
import com.product.sender.ProductDeleteQueueSender;

import lombok.var;
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	@InjectMocks
	ProductService productService;

	@Mock
	ModelMapper modelMapper;

	@Mock
	ProductRepository productRepository;

	@Mock
	MessageBuilder messageBuilder;

	@Mock
	ProductDeleteQueueSender productDeleteQueueSender;

	@Test
	public void getProductById_WhenSendIdProductValid_ExpectedSucess() {
		var product = ScenarioFactory.newProduct();
		var optionalProduct = ScenarioFactory.newOptionalProduct();
		var productResponse = ScenarioFactory.productDtoResponse();
		when(productRepository.findById(product.getId())).thenReturn(optionalProduct);
		when(modelMapper.map(product, ProductDtoResponse.class)).thenReturn(productResponse);
		productService.getProductById(1L);
		verify(productRepository, times(1)).findById(1L);
		verify(modelMapper, times(1)).map(product, ProductDtoResponse.class);

	}

	@Test
	public void getProductById_WhenSendIdProductInvalid_ExpectedException() {
		var optionalProductNulo = ScenarioFactory.optionalProductNulo();

		when(productRepository.findById(3L)).thenReturn(optionalProductNulo);

		assertThatThrownBy(() -> productService.getProductById(3L)).isInstanceOf(BusinessException.class)
				.hasMessage(messageBuilder.getMessage("message.exception"));
		verify(productRepository, times(1)).findById(3L);

	}

	@Test
	public void save_WhenReceivingProductRequestDtoWithAllValidFields_ExpectedSucess() {
		var product = ScenarioFactory.newProduct();
		var request = ScenarioFactory.productDtoRequest();
		var productDtoResponse = ScenarioFactory.productDtoResponse();

		when(modelMapper.map(request, Product.class)).thenReturn(product);
		when(productRepository.save(product)).thenReturn(product);
		when(modelMapper.map(product, ProductDtoResponse.class)).thenReturn(productDtoResponse);

		productService.save(request);
		verify(productRepository, times(1)).save(product);
		verify(modelMapper, times(1)).map(product, ProductDtoResponse.class);
	}

	@Test
	public void findByName_WhenListProduct_ExpectedSucess() {
		var pageRequest = ScenarioFactory.newPageable();
		var newPage = ScenarioFactory.newPage();
		var name = "test";
		when(productRepository.findByName(name, pageRequest)).thenReturn(newPage);
		productService.findByName(name, pageRequest);
		verify(productRepository, times(1)).findByName(name, pageRequest);

	}

	@Test
	public void update_WhenReceivingValidBaseIdAndProductUpdateDtoRequestWittAllFieldsValidated_ExpectedSucess() {
		var productRequestDtoUpdate = ScenarioFactory.newProductRequestDtoUpdate();
		var optionalProduct = ScenarioFactory.newOptionalProduct();
		var productDtoResponse = ScenarioFactory.productDtoResponse();

		when(productRepository.findById(optionalProduct.get().getId())).thenReturn(optionalProduct);
		when(productRepository.save(optionalProduct.get())).thenReturn(optionalProduct.get());
		when(modelMapper.map(optionalProduct.get(), ProductDtoResponse.class)).thenReturn(productDtoResponse);

		productService.update(1L, productRequestDtoUpdate);
		verify(productRepository, times(1)).findById(optionalProduct.get().getId());
		verify(productRepository, times(1)).save(optionalProduct.get());
		verify(modelMapper, times(1)).map(optionalProduct.get(), ProductDtoResponse.class);

	}

	@Test
	public void update_WhenReceivingInvalidBaseIdOrProductUpdateDtoRequestWithSomeInvalidFields_ExpectedException() {
		var productRequestDtoUpdate = ScenarioFactory.newProductRequestDtoUpdate();
		var optionalProductNulo = ScenarioFactory.optionalProductNulo();
		when(productRepository.findById(2L)).thenReturn(optionalProductNulo);
		assertThatThrownBy(() -> productService.update(2L, productRequestDtoUpdate))
				.isInstanceOf(BusinessException.class).hasMessage(messageBuilder.getMessage("message.exception"));
		verify(productRepository, times(1)).findById(2L);

	}

	@Test
	public void delete_WhenReceivingValidIdOnBase_ExpectedSucess() {
		Product product = ScenarioFactory.newProduct();
		var newOptionalProduct = ScenarioFactory.newOptionalProduct();
		when(productRepository.findById((product.getId()))).thenReturn(newOptionalProduct);
		productService.delete(1L);
		productDeleteQueueSender.sendMessage(product);
		verify(productRepository, times(1)).delete(product);
	}

	@Test
	public void delete_WhenReceivingInvalidBaseId_ExpectedException() {
		var optionalProductNulo = ScenarioFactory.optionalProductNulo();
		when(productRepository.findById(2L)).thenReturn(optionalProductNulo);
		assertThatThrownBy(() -> productService.delete(2L)).isInstanceOf(BusinessException.class)
				.hasMessage(messageBuilder.getMessage("message.exception"));
		verify(productRepository, times(1)).findById(2L);
		verify(productDeleteQueueSender, never()).sendMessage(any());
	}
}
