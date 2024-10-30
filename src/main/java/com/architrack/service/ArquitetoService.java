package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.controller.ArquitetoController;
import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Arquiteto;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.ArquitetoRepository;
import com.architrack.vo.v1.ArquitetoVo;

import jakarta.transaction.Transactional;

@Service
public class ArquitetoService {

	@Autowired
	private ArquitetoRepository repository;
	
	
	@Transactional
	public ArquitetoVo findById(Long id) {
		Arquiteto arquiteto = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		ArquitetoVo vo = DozerMapper.parseObjectForEntity(arquiteto, ArquitetoVo.class);
		vo.add(linkTo(methodOn(ArquitetoController.class).findById(id)).withSelfRel());
		return vo;
		
	}
	@Transactional
	public List<ArquitetoVo> findAll() {
		List<Arquiteto>arquiteto = repository.findAll();
		List<ArquitetoVo>listVo = DozerMapper.parseListObjectForEntity(arquiteto, ArquitetoVo.class);
		listVo.stream().forEach(x -> x.add(linkTo(methodOn(ArquitetoController.class).findById(x.getKey())).withSelfRel()));
		return listVo;
	}
	@Transactional
	public ArquitetoVo create(ArquitetoVo arquiteto) {
		if(arquiteto == null) {
			throw new ResponseBadRequestHandlerException("id request failed");
		}
		Arquiteto arq = DozerMapper.parseObjectForEntity(arquiteto, Arquiteto.class);
		ArquitetoVo vo = DozerMapper.parseObjectForEntity(repository.save(arq), ArquitetoVo.class);
		vo.add(linkTo(methodOn(ArquitetoController.class).findById(arq.getId())).withSelfRel());
		return vo;
	}
	@Transactional
	public ArquitetoVo update(ArquitetoVo arquitetoVo) {
		if(arquitetoVo == null) {
			throw new ResponseBadRequestHandlerException("id request failed");
		}
		
		Arquiteto arquiteto = repository.findById(arquitetoVo.getKey()).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
	
		arquiteto.setEmail(arquitetoVo.getEmail());;
		arquiteto.setNome(arquitetoVo.getNome());
		arquiteto.setTelefone(arquitetoVo.getTelefone());
		arquiteto.setTipo(arquitetoVo.getTipo());
		arquiteto.setEnderecos(arquitetoVo.getEnderecos());
		
		var vo = DozerMapper.parseObjectForEntity(repository.save(arquiteto), ArquitetoVo.class);
		
		vo.add(linkTo(methodOn(ArquitetoController.class).findById(arquiteto.getId())).withSelfRel());
		return vo;
	}
	@Transactional
	public void delete(Long id) {
		Arquiteto arquiteto = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		repository.delete(arquiteto);
	}

}
