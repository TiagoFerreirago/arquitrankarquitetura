package com.architrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Documento;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.exception.ResponseNotFoundHandlerException;
import com.architrack.repository.DocumentoRepository;
import com.architrack.vo.v1.DocumentoVo;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository repository;
	

	public DocumentoVo findById(Long id) {
		Documento documento = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		DocumentoVo vo = DozerMapper.parseObjectForEntity(documento, DocumentoVo.class);
		return vo;
		
	}
	
	public List<DocumentoVo> findAll() {
		List<Documento>documento = repository.findAll();
		List<DocumentoVo>listVo = DozerMapper.parseListObjectForEntity(documento, DocumentoVo.class);
		return listVo;
	}
	
	public DocumentoVo create(DocumentoVo documentoVo) {
		if(documentoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Documento arq = DozerMapper.parseObjectForEntity(documentoVo, Documento.class);
		DocumentoVo vo = DozerMapper.parseObjectForEntity(repository.save(arq), DocumentoVo.class);
		return vo;
	}
	
	public DocumentoVo update(DocumentoVo documentoVo) {
		if(documentoVo ==null) {
			throw new ResponseBadRequestHandlerException();
		}
		Documento documento = repository.findById(documentoVo.getKey()).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		documento.setDataEnvio(documentoVo.getDataEnvio());
		documento.setNomeDocumento(documentoVo.getNomeDocumento());
		documento.setTipoDocumento(documentoVo.getTipoDocumento());
		
		DocumentoVo vo = DozerMapper.parseObjectForEntity(repository.save(documento), DocumentoVo.class);
		return vo;
	}
	
	public void delete(Long id) {
		Documento documento = repository.findById(id).orElseThrow(
				() -> new ResponseNotFoundHandlerException("Id not found"));
		repository.delete(documento);
	}
}
