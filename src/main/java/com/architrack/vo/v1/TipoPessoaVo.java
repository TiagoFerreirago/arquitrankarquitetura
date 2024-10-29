package com.architrack.vo.v1;

import java.util.Objects;

import com.architrack.entities.Arquiteto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","tipo","arquiteto"})
public class TipoPessoaVo {

	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String tipo;
	@JsonBackReference
	private Arquiteto arquiteto;
	
	public TipoPessoaVo() {
		
	}
	
	public TipoPessoaVo(String tipo, Arquiteto arquiteto) {
		
		this.tipo = tipo;
		this.arquiteto = arquiteto;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Arquiteto getArquiteto() {
		return arquiteto;
	}

	public void setArquiteto(Arquiteto arquiteto) {
		this.arquiteto = arquiteto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(arquiteto, key, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPessoaVo other = (TipoPessoaVo) obj;
		return Objects.equals(arquiteto, other.arquiteto) && Objects.equals(key, other.key)
				&& Objects.equals(tipo, other.tipo);
	}
	
	
}

