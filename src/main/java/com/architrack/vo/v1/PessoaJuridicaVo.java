package com.architrack.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.architrack.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","cnpj","inscricaoEstadual","cliente"})
public class PessoaJuridicaVo extends RepresentationModel<PessoaJuridicaVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String cnpj;
	private String inscricaoEstadual;
	private Cliente cliente;
	
	public PessoaJuridicaVo() {
		
	}

	public PessoaJuridicaVo(String cnpj, String ie, Cliente cliente) {
		
		this.cnpj = cnpj;
		this.inscricaoEstadual = ie;
		this.cliente = cliente;
	}
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, cnpj, inscricaoEstadual, key);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaJuridicaVo other = (PessoaJuridicaVo) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(cnpj, other.cnpj)
				&& Objects.equals(inscricaoEstadual, other.inscricaoEstadual) && Objects.equals(key, other.key);
	}
	
	
}
