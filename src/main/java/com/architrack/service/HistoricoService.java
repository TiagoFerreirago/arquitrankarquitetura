package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Historico;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.HistoricoRepository;
import com.architrack.vo.v1.HistoricoVo;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository repository;
		
	
	public HistoricoVo findById(Long id) {
		Historico historico = repository.findById(id).orElseThrow( 
				() -> new ResponseNotFoundHandlerException("Id not found"));
		HistoricoVo vo = DozerMapper.parseObjectForEntity(historico, HistoricoVo.class);
		return vo;
	}
	
	public List<HistoricoVo> findAll(){
		List<Historico>list = repository.findAll();
		List<HistoricoVo>listVo = DozerMapper.parseListObjectForEntity(list, HistoricoVo.class);
		return listVo;
	}
	
	public HistoricoVo create(HistoricoVo historicoVo) {
		if(historicoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Historico hist = DozerMapper.parseObjectForEntity(historicoVo, Historico.class);
		HistoricoVo vo = DozerMapper.parseObjectForEntity(repository.save(hist),HistoricoVo.class);
		return vo;
	}
	
	public HistoricoVo update(HistoricoVo historicoVo) {
		if(historicoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Historico historico = repository.findById(historicoVo.getKey()).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		historico.setDataMudanca(historicoVo.getDataMudanca());
		historico.setDescricaoMudanca(historicoVo.getDescricaoMudanca());
		HistoricoVo vo = DozerMapper.parseObjectForEntity(repository.save(historico), HistoricoVo.class);
		return vo;
	}
	
	public void delete(Long id) {
		Historico hist = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		repository.delete(hist);
	}
}
