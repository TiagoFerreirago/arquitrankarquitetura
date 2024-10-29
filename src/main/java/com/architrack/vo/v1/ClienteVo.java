package com.architrack.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.architrack.entities.PessoaFisica;
import com.architrack.entities.PessoaJuridica;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","nome","telefone","email","pf","pj","enderecos","projeto","documentos"})
public class ClienteVo extends RepresentationModel<ClienteVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String nome;
	private String telefone;
	private String email;
	private PessoaFisica pf;
	private PessoaJuridica pj;
	//private List<Endereco> enderecos;
	//private Projeto projeto;
	//private List<Documento> documentos;
	
	public ClienteVo(){
		
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

	public PessoaFisica getPf() {
		return pf;
	}

	public void setPf(PessoaFisica pf) {
		this.pf = pf;
	}

	public PessoaJuridica getPj() {
		return pj;
	}

	public void setPj(PessoaJuridica pj) {
		this.pj = pj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, key, nome, pf, pj, telefone);
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
		ClienteVo other = (ClienteVo) obj;
		return Objects.equals(email, other.email) && Objects.equals(key, other.key) && Objects.equals(nome, other.nome)
				&& Objects.equals(pf, other.pf) && Objects.equals(pj, other.pj)
				&& Objects.equals(telefone, other.telefone);
	}

	
}
