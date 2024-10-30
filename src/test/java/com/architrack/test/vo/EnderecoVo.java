package com.architrack.test.vo;

import java.util.Objects;

import com.architrack.entities.Arquiteto;
import com.architrack.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class EnderecoVo  {

	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private Cliente cliente;
	private Arquiteto arquiteto;
	
	public EnderecoVo() {
		
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Arquiteto getArquiteto() {
		return arquiteto;
	}

	public void setArquiteto(Arquiteto arquiteto) {
		this.arquiteto = arquiteto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(arquiteto, bairro, cep, cidade, cliente, estado, key, rua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoVo other = (EnderecoVo) obj;
		return Objects.equals(arquiteto, other.arquiteto) && Objects.equals(bairro, other.bairro)
				&& Objects.equals(cep, other.cep) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(cliente, other.cliente) && Objects.equals(estado, other.estado)
				&& Objects.equals(key, other.key) && Objects.equals(rua, other.rua);
	}

	
}
