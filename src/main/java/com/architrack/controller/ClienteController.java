package com.architrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architrack.entities.Cliente;
import com.architrack.service.ClienteService;
import com.architrack.util.MediaType;
import com.architrack.vo.v1.ClienteVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/cliente")
@Tag(name = "Clientes", description = "Endpoint para o gerenciamento de clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar informações do cliente através do id", description = "Buscar informações do cliente através do id", tags = {"Clientes"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ClienteVo findById(@PathVariable(value = "id")Long id) {
		return service.findById(id);
	}
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar todas informações dos clientes", description = "Buscar todas informações dos clientes", tags = {"Clientes"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			mediaType ="application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public List<ClienteVo> findAll() {
		return service.findAll();
	}
	@PostMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Cadastrando o cliente", description = "Cadastrando o cliente com xml,json ou yml", tags = {"Clientes"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ClienteVo create(@RequestBody ClienteVo cliente) {
		
		return service.create(cliente);
	}
	@PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Atualizar informações do cliente", description = "Atualizar informações do cliente com xml,json ou yaml", tags = {"Clientes"},
	responses = {@ApiResponse(description = "No Content", responseCode = "204", content = @Content(
			schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ClienteVo update(@RequestBody ClienteVo cliente) {
		return service.update(cliente);
	}
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletar informações do cliente através do id", description = "Deletar informações do cliente através do id", tags = {"Clientes"},
	responses = {@ApiResponse(description = "No Content", responseCode = "204", content = @Content(
			schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ResponseEntity<ClienteVo> delete(@PathVariable("id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
