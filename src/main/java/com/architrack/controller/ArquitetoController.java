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

import com.architrack.entities.Arquiteto;
import com.architrack.service.ArquitetoService;
import com.architrack.util.MediaType;
import com.architrack.vo.v1.ArquitetoVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/arquiteto")
@Tag(name= "Arquiteto", description = "Endpoint para o gerenciamento de arquitetos")
public class ArquitetoController {
	
	@Autowired
	private ArquitetoService service;

	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar informações do arquiteto através do id", description = "Buscar informações do arquiteto através do id", tags = {"Arquiteto"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Arquiteto.class))),
	@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ArquitetoVo findById(@PathVariable(value = "id")Long id) {
		return service.findById(id);
	}
	@GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar todas informações dos arquitetos", description = "Buscar todas informações dos arquitetos", tags = {"Arquiteto"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Arquiteto.class)))),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),	
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),	
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),	
		@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public List<ArquitetoVo> findAll(){
		return service.findAll();
	}
	@PostMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Cadastrando o arquiteto", description = "Cadastrando o arquiteto com xml,json ou yml", tags = {"Arquiteto"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Arquiteto.class))),
	@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ArquitetoVo create(@RequestBody ArquitetoVo arquiteto) {
		return service.create(arquiteto);
	}
	@PutMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Atualizar informações do arquiteto", description = "Atualizar informações do arquiteto com xml,json ou yaml", tags = {"Arquiteto"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Arquiteto.class))),
	@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ArquitetoVo update(@RequestBody ArquitetoVo arquiteto) {
		return service.update(arquiteto);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletar informações do arquiteto através do id", description = "Deletar informações do arquiteto através do id", tags = {"Arquiteto"},
	responses = {@ApiResponse(description = "No Content", responseCode = "204", content = @Content(
			schema = @Schema(implementation = Arquiteto.class))),
	@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ResponseEntity<ArquitetoVo> delete(@PathVariable(value = "id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
