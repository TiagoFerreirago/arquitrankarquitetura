package com.architrack.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.architrack.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","rg","cpf","cliente"})
public class PessoaFisicaVo extends RepresentationModel<PessoaFisicaVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String rg;
	private String cpf;
	private Cliente cliente;
	
	public PessoaFisicaVo() {
		
	}

	public PessoaFisicaVo(String rg, String cpf, Cliente cliente) {
		this.rg = rg;
		this.cpf = cpf;
		this.cliente = cliente;
	}
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, cpf, key, rg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisicaVo other = (PessoaFisicaVo) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(key, other.key) && Objects.equals(rg, other.rg);
	}
	
	
}
