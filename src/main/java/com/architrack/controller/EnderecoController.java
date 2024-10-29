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

import com.architrack.service.EnderecoService;
import com.architrack.util.MediaType;
import com.architrack.vo.v1.EnderecoVo;


@RestController
@RequestMapping(value = "/api/v1/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public EnderecoVo findById(@PathVariable(value = "id")Long id) {
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public List<EnderecoVo> findAll(){
		return service.findAll();
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public EnderecoVo create(@RequestBody EnderecoVo endereco) {
		return service.create(endereco);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public EnderecoVo update(@RequestBody EnderecoVo endereco) {
		return service.update(endereco);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EnderecoVo>delele(@PathVariable(value = "id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
