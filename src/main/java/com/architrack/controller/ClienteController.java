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

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	
	public ClienteVo findById(@PathVariable(value = "id")Long id) {
		return service.findById(id);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public List<ClienteVo> findAll() {
		return service.findAll();
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public ClienteVo create(@RequestBody ClienteVo cliente) {
		
		return service.create(cliente);
	}
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public ClienteVo update(@RequestBody ClienteVo cliente) {
		return service.update(cliente);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteVo> delete(@PathVariable("id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
