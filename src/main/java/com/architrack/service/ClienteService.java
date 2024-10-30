package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.controller.ClienteController;
import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Cliente;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.ClienteRepository;
import com.architrack.vo.v1.ClienteVo;

@Service
public class ClienteService {

	
	@Autowired
	private  ClienteRepository repository;
	

	public ClienteVo findById(Long id) {
		Cliente cliente = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		ClienteVo vo = DozerMapper.parseObjectForEntity(cliente, ClienteVo.class);
		vo.add(linkTo(methodOn(ClienteController.class).findById(id)).withSelfRel());
		return vo;
		
	}
	
	public List<ClienteVo> findAll() {
		List<Cliente>cliente = repository.findAll();
		List<ClienteVo>listVo = DozerMapper.parseListObjectForEntity(cliente, ClienteVo.class);
		listVo.stream().forEach(x -> x.add(linkTo(methodOn(ClienteController.class).findById(x.getKey())).withSelfRel()));
		return listVo;
	}
	
	public ClienteVo create(ClienteVo cliente) {
		if(cliente ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Cliente cli = DozerMapper.parseObjectForEntity(cliente, Cliente.class);
		ClienteVo vo = DozerMapper.parseObjectForEntity(repository.save(cli), ClienteVo.class);
		vo.add(linkTo(methodOn(ClienteController.class).findById(cli.getId())).withSelfRel());
		return vo;
	}
	
	public ClienteVo update(ClienteVo clienteVo) {
		if(clienteVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Cliente cliente = repository.findById(clienteVo.getKey()).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		
		if(cliente ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		cliente.setEmail(clienteVo.getEmail());;
		cliente.setNome(clienteVo.getNome());
		cliente.setNome(clienteVo.getNome());
		cliente.setPf(clienteVo.getPf());
		cliente.setPj(clienteVo.getPj());
		cliente.setTelefone(clienteVo.getTelefone());
		
		ClienteVo vo = DozerMapper.parseObjectForEntity(repository.save(cliente), ClienteVo.class);
		vo.add(linkTo(methodOn(ClienteController.class).findById(cliente.getId())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		Cliente cliente = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		repository.delete(cliente);
	}
}
