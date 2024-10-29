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

@RestController
@RequestMapping("/api/v1/arquiteto")
public class ArquitetoController {
	
	@Autowired
	private ArquitetoService service;

	@GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public ArquitetoVo findById(@PathVariable(value = "id")Long id) {
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public List<ArquitetoVo> findAll(){
		return service.findAll();
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public ArquitetoVo create(@RequestBody ArquitetoVo arquiteto) {
		return service.create(arquiteto);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	public ArquitetoVo update(@RequestBody ArquitetoVo arquiteto) {
		return service.update(arquiteto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ArquitetoVo> delete(@PathVariable(value = "id")Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
