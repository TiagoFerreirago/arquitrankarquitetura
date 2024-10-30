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

import com.architrack.entities.Historico;
import com.architrack.service.HistoricoService;
import com.architrack.util.MediaType;
import com.architrack.vo.v1.HistoricoVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/historico")
@Tag(name = "Historico", description = "Endpoint para o gerenciamento de historico")
public class HistoricoController {
	
	@Autowired
	private HistoricoService service;

	@GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar o histórico através do id", description = "Buscar o histórico através do id", tags = {"Historico"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Historico.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public HistoricoVo findById(@PathVariable(value = "id")Long id) {
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar todos os históricos", description = "Buscar todos os históricos", tags = {"Historico"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Historico.class)))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public List<HistoricoVo> findAll(){
		return service.findAll();
	}
	@PostMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML}
	,produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Cadastrando histórico", description = "Cadastrando histórico com xml,json ou yml", tags = {"Historico"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Historico.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public HistoricoVo create(@RequestBody HistoricoVo historico) {
		return service.create(historico);
	}
	@PutMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML}
	,produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Atualização do histórico", description = "Atualização do histórico com xml,json ou yml", tags = {"Historico"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			schema = @Schema(implementation = Historico.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public HistoricoVo update(@RequestBody HistoricoVo historico) {
		return service.update(historico);
	}
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletar o histórico através do id", description = "Deletar o histórico através do id", tags = {"Historico"},
	responses = {@ApiResponse(description = "No Content", responseCode = "204", content = @Content(
			schema = @Schema(implementation = Historico.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	public ResponseEntity<HistoricoVo>delete(@PathVariable(value = "id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
