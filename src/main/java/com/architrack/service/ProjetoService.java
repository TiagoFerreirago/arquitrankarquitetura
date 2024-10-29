package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Projeto;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.ProjetoRepository;
import com.architrack.vo.v1.ProjetoVo;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository repository;
	
	
	public ProjetoVo findById(Long id) {
		
		Projeto projeto = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		ProjetoVo vo = DozerMapper.parseObjectForEntity(projeto, ProjetoVo.class);
		return vo;
	}
	
	public List<ProjetoVo> findAll(){
		List<Projeto> projetos = repository.findAll();
		List<ProjetoVo> listVo = DozerMapper.parseListObjectForEntity(projetos, ProjetoVo.class);
		return listVo;
	}
	
	public ProjetoVo create(ProjetoVo projetoVo) {
		if(projetoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Projeto proj = DozerMapper.parseObjectForEntity(projetoVo, Projeto.class);
		ProjetoVo vo = DozerMapper.parseObjectForEntity(repository.save(proj), ProjetoVo.class);
		return vo;
	}
	
	public ProjetoVo update(ProjetoVo projetoVo) {
		if(projetoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Projeto projeto = repository.findById(projetoVo.getKey()).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		projeto.setAreaEmpreendimento(projetoVo.getAreaEmpreendimento());
		ProjetoVo vo = DozerMapper.parseObjectForEntity(repository.save(projeto), ProjetoVo.class);
		return vo;
				
	}
	
	public void delete(Long id) {
		
		Projeto proj = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		repository.delete(proj);
	}
}
