package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Agenda;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.AgendaRepository;
import com.architrack.vo.v1.AgendaVo;

import jakarta.transaction.Transactional;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository repository;
	
	
	
	public AgendaVo findById(Long id) {
		
		Agenda entity = repository.findById(id).orElseThrow( () -> 
		 new ResponseNotFoundHandlerException("Id not found"));
		AgendaVo vo = DozerMapper.parseObjectForEntity(entity, AgendaVo.class);
		
		return vo;
	}
	@Transactional
	public List<AgendaVo> findAll() {
		List<Agenda>list = repository.findAll();
		List<AgendaVo>listVo = DozerMapper.parseListObjectForEntity(list, AgendaVo.class);
		return listVo;
	}

	public AgendaVo update(AgendaVo agenda) {
		if(agenda == null) {
			throw new ResponseBadRequestHandlerException("id request failed");
		}
		Agenda entity = repository.findById(agenda.getKey()).orElseThrow( () -> 
		new ResponseNotFoundHandlerException("Id not found"));
		
		entity.setDataFim(agenda.getDataFim());
		entity.setDataInicio(agenda.getDataInicio());
		AgendaVo vo = DozerMapper.parseObjectForEntity(repository.save(entity), AgendaVo.class);
		return vo;
	
}
	public AgendaVo create(AgendaVo agenda) {
		if(agenda == null) {
			throw new ResponseBadRequestHandlerException("id request failed");
		}
		Agenda entity = DozerMapper.parseObjectForEntity(agenda, Agenda.class);
		AgendaVo vo = DozerMapper.parseObjectForEntity(repository.save(entity), AgendaVo.class);
		return vo;
	}
	
	public void delete(Long id) {
		Agenda agenda = repository.findById(id).orElseThrow(
				() ->  new ResponseNotFoundHandlerException("Id not found"));
		
		repository.delete(agenda);
	}
}
