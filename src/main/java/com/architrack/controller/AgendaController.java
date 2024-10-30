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

import com.architrack.entities.Agenda;
import com.architrack.service.AgendaService;
import com.architrack.util.MediaType;
import com.architrack.vo.v1.AgendaVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/agenda")
@Tag(name = "Agendamento", description = "Endpoint para o gerenciamento de agenda")
public class AgendaController {

	@Autowired
	private AgendaService service;
	
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar Agendamento", description = "Buscar o agendamento pelo Id", tags = {"Agendamento"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200",content =@Content(
			schema = @Schema(implementation = Agenda.class))),
	@ApiResponse(description = "400", responseCode = "Bad Request",content = @Content),
	@ApiResponse(description = "401", responseCode = "Unauthorized", content = @Content),
	@ApiResponse(description = "404", responseCode = "Not Found", content = @Content),
	@ApiResponse(description = "500", responseCode = "Internal Server Error", content = @Content)})
	public AgendaVo findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Buscar todos os Agendamentos",description = "Buscar todos os agendamentos", tags = {"Agendamento"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
			mediaType= "application/json",array = @ArraySchema(schema = @Schema(implementation = Agenda.class)))),
			@ApiResponse(description = "400", responseCode= "Bad Request", content = @Content),
			@ApiResponse(description = "401", responseCode = "Unauthorized", content = @Content),
			@ApiResponse(description = "404", responseCode = "Not Found", content = @Content),
			@ApiResponse(description = "500", responseCode = "Internal Server Error", content = @Content)})
	public List<AgendaVo> findAll() {
		return service.findAll();
	}
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Criação do Agendamento", description = "Criação do Agendamento com xml,json ou yaml", tags = {"Agendamento"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200",content =@Content(
			schema = @Schema(implementation = Agenda.class))),
	@ApiResponse(description = "400", responseCode = "Bad Request",content = @Content),
	@ApiResponse(description = "401", responseCode = "Unauthorized", content = @Content),
	@ApiResponse(description = "500", responseCode = "Internal Server Error", content = @Content)})
	public AgendaVo create(@RequestBody AgendaVo agenda) {
		return service.create(agenda);
	}
	@PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Atualização do Agendamento", description = "Atualização do Agendamento com xml,json ou yaml", tags = {"Agendamento"},
	responses = {@ApiResponse(description = "Sucess", responseCode = "200",content =@Content(
			schema = @Schema(implementation = Agenda.class))),
	@ApiResponse(description = "400", responseCode = "Bad Request",content = @Content),
	@ApiResponse(description = "401", responseCode = "Unauthorized", content = @Content),
	@ApiResponse(description = "404", responseCode = "Not Found", content = @Content),
	@ApiResponse(description = "500", responseCode = "Internal Server Error", content = @Content)})
	public AgendaVo update(@RequestBody AgendaVo agenda) {
		return service.update(agenda);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletar o Agendamento pelo id", description = "Deletar o agendamento pelo id com xml,json ou yaml", tags = {"Agendamento"},
	responses = {@ApiResponse(description = "No Content", responseCode = "204",content =@Content),
	@ApiResponse(description = "400", responseCode = "Bad Request",content = @Content),
	@ApiResponse(description = "401", responseCode = "Unauthorized", content = @Content),
	@ApiResponse(description = "404", responseCode = "Not Found", content = @Content),
	@ApiResponse(description = "500", responseCode = "Internal Server Error", content = @Content)})
	public ResponseEntity<AgendaVo> delete(@PathVariable(value = "id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
