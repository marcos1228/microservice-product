package com.product.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.product.domain.dto.request.ProductDtoRequest;
import com.product.domain.dto.request.ProductUpdateDtoRequest;
import com.product.domain.dto.response.ProductDtoResponse;
import com.product.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("products")
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@ApiOperation(value = "Buscar um produto pelo id", notes = "Este endpoint busca um produto pelo id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto encontrado com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado"),
			@ApiResponse(code = 401, message = "O cliente deve está autenticado no sistema"),
			@ApiResponse(code = 500, message = "Erro interno do servidor") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long id) {
		log.info("Method={}  message={}", "getProductById", "buscando por id");
		return ResponseEntity.ok().body(service.getProductById(id));
	}

	@ApiOperation(value = "Salvar produto", notes = "Este endpoint salvar um produto ")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "O cliente deve está autenticado no sistema"),
			@ApiResponse(code = 403, message = "Cliente não autorizado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor") })
	@PostMapping()
	public ResponseEntity<ProductDtoResponse> save(@RequestBody @Valid ProductDtoRequest resquest) {
		ProductDtoResponse save = service.save(resquest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
		log.info("Method={}  message={}", "save", "savando um produto");
		return ResponseEntity.created(uri).body(save);
	}

	@ApiOperation(value = "Atualizar um produto", notes = "Este endpoint atualizar um product na base")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto atualizado com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado"),
			@ApiResponse(code = 401, message = "O cliente deve está autenticado no sistema"),
			@ApiResponse(code = 403, message = "Cliente não autorizado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor") })
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDtoResponse> update(@PathVariable Long id,
			@RequestBody @Valid ProductUpdateDtoRequest dto) {
		log.info("Method={}  message={} id=[{}]", "update", "atualizando um produto");
		return ResponseEntity.ok().body(service.update(id, dto));
	}

	@ApiOperation(value = "Retorna uma lista de produto", notes = "Este endpoint retorna uma lista de produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Requesição feita com sucesso"),
			@ApiResponse(code = 403, message = "Cliente não autorizado"),
			@ApiResponse(code = 401, message = "O cliente deve está autenticado no sistema"),
			@ApiResponse(code = 500, message = "Erro interno do servidor") })
	@GetMapping()
	public ResponseEntity<Page<ProductDtoResponse>> findByName(
			@PageableDefault(sort = "name", direction = Direction.ASC, page = 0, size = 5) Pageable pageable,
			@RequestParam(required = false, defaultValue = "%") String name) {
		Page<ProductDtoResponse> listProduct = service.findByName(name, pageable);
		log.info("Method={} message={}", "findByName", "lista produto");
		return ResponseEntity.ok().body(listProduct);
	}

	@ApiOperation(value = "Excluir um produto", notes = "Este endpoint excluir um produto")
	@ApiResponses({ @ApiResponse(code = 204, message = "Produto excluido com sucesso"),
			@ApiResponse(code = 500, message = "Erro interno do servidor") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		log.info("Method={} message={} idProduct={}", "delete", "deleta um produto", id);
		return ResponseEntity.noContent().build();
	}
}
