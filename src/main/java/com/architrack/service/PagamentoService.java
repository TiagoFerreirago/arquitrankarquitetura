package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.controller.PagamentoController;
import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Pagamentos;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.PagamentoRepository;
import com.architrack.vo.v1.PagamentosVo;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;
		
	
	public PagamentosVo findById(Long id) {
		Pagamentos pagamentos = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		PagamentosVo vo = DozerMapper.parseObjectForEntity(pagamentos, PagamentosVo.class);
		vo.add(linkTo(methodOn(PagamentoController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public List<PagamentosVo> findAll(){
		
		List<Pagamentos> pagamentos = repository.findAll();
		List<PagamentosVo> listVo = DozerMapper.parseListObjectForEntity(pagamentos, PagamentosVo.class);
		listVo.stream().forEach(x -> x.add(linkTo(methodOn(PagamentoController.class).findById(x.getKey())).withSelfRel()));
		return listVo;
	}
	
	public PagamentosVo create(PagamentosVo pagamentosVo) {
		if(pagamentosVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Pagamentos pag = DozerMapper.parseObjectForEntity(pagamentosVo, Pagamentos.class);
		PagamentosVo vo = DozerMapper.parseObjectForEntity(repository.save(pag), PagamentosVo.class);
		vo.add(linkTo(methodOn(PagamentoController.class).findById(pag.getId())).withSelfRel());
		return vo;
	}
	
	public PagamentosVo update(PagamentosVo pagamentoVo) {
		if(pagamentoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Pagamentos pagamento= repository.findById(pagamentoVo.getKey()).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		pagamento.setDataPagamento(pagamentoVo.getDataPagamento());
		pagamento.setStatus(pagamentoVo.getStatus());
		pagamento.setValor(pagamentoVo.getValor());
		PagamentosVo vo = DozerMapper.parseObjectForEntity(repository.save(pagamento), PagamentosVo.class);
		vo.add(linkTo(methodOn(PagamentoController.class).findById(pagamento.getId())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		Pagamentos pag= repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		repository.delete(pag);
	}
}
