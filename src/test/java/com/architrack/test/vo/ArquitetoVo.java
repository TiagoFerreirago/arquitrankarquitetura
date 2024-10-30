package com.architrack.test.vo;

import java.util.List;
import java.util.Objects;

import com.architrack.entities.Endereco;
import com.architrack.entities.Projeto;
import com.architrack.entities.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class ArquitetoVo{
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String nome;
	private String telefone;
	private String email;
	private List<Endereco> enderecos;
	private List<Projeto> projetos;
	private TipoPessoa tipo;
	
	public ArquitetoVo() {
		
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, enderecos, key, nome, projetos, telefone, tipo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquitetoVo other = (ArquitetoVo) obj;
		return Objects.equals(email, other.email) && Objects.equals(enderecos, other.enderecos)
				&& Objects.equals(key, other.key) && Objects.equals(nome, other.nome)
				&& Objects.equals(projetos, other.projetos) && Objects.equals(telefone, other.telefone)
				&& Objects.equals(tipo, other.tipo);
	}
	
	
}
