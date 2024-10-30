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

import com.architrack.entities.Pagamentos;
import com.architrack.entities.Projeto;
import com.architrack.service.ProjetoService;
import com.architrack.util.MediaType;
import com.architrack.vo.v1.ProjetoVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/projeto")
@Tag(name = "Projetos", description ="Endpoint para o gerenciamento de projetos")
public class ProjetoController {

	@Autowired
	private ProjetoService service;
	
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar o projeto através do id", description = "Buscar o projeto através do id", tags = {"Projetos"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Projeto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ProjetoVo findById(@PathVariable(value = "id")Long id) {
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar todos os projetos", description =  "Buscar todos os projetos", tags = {"Projetos"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pagamentos.class)))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public List<ProjetoVo> findAll(){
		return service.findAll();
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Cadastrar o projeto", description = "Cadastrar o projeto com xml,json ou yml", tags = {"Projetos"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Projeto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ProjetoVo create(@RequestBody ProjetoVo projeto) {
		return service.create(projeto);
	}
	@PutMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Atualizar o projeto", description = "Atualizar o projeto com xml,json ou yml", tags = {"Projetos"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Projeto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ProjetoVo update(@RequestBody ProjetoVo projeto) {
		return service.update(projeto);
	}
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletar o projeto através do id", description = "Deletar o projeto através do id", tags = {"Projetos"},
	responses = {@ApiResponse(description = "No Content", responseCode = "204", content = @Content(
			schema = @Schema(implementation = Projeto.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ResponseEntity<ProjetoVo>delete(@PathVariable(value = "id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
