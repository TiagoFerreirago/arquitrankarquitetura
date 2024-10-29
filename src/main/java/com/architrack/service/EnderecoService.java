package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Endereco;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.EnderecoRepository;
import com.architrack.vo.v1.EnderecoVo;

import jakarta.transaction.Transactional;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	@Transactional
	public EnderecoVo findById(Long id) {
		Endereco endereco = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		EnderecoVo vo = DozerMapper.parseObjectForEntity(endereco, EnderecoVo.class);
		return vo;
		
	}
	@Transactional
	public List<EnderecoVo> findAll() {
		List<Endereco>endereco = repository.findAll();
		List<EnderecoVo>listVo = DozerMapper.parseListObjectForEntity(endereco, EnderecoVo.class);
		return listVo;
	}
	@Transactional
	public EnderecoVo create(EnderecoVo enderecoVo) {
		if(enderecoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Endereco arq = DozerMapper.parseObjectForEntity(enderecoVo, Endereco.class);
		EnderecoVo vo = DozerMapper.parseObjectForEntity(repository.save(arq), EnderecoVo.class);
		return vo;
	}
	@Transactional
	public EnderecoVo update(EnderecoVo enderecoVo) {
		if(enderecoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Endereco endereco = repository.findById(enderecoVo.getKey()).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		
		endereco.setBairro(enderecoVo.getBairro());
		endereco.setCep(enderecoVo.getCep());
		endereco.setCidade(enderecoVo.getCidade());
		endereco.setEstado(enderecoVo.getEstado());
		endereco.setRua(enderecoVo.getRua());
		
		EnderecoVo vo = DozerMapper.parseObjectForEntity(repository.save(endereco), EnderecoVo.class);
		return vo;
	}
	@Transactional
	public void delete(Long id) {
		Endereco endereco = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		repository.delete(endereco);
	}
}
