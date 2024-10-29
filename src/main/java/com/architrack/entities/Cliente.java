package com.architrack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome",nullable = false)
	private String nome;
	@Column(name = "telefone",nullable = false)
	private String telefone;
	@Column(name = "email", nullable = false)
	private String email;
	
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private PessoaFisica pf;
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval =true)
	private PessoaJuridica pj;
	@OneToMany(mappedBy = "cliente",  cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
	@OneToOne(mappedBy = "cliente",cascade = CascadeType.ALL)
	private Projeto projeto;
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	public Cliente() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documentos, email, enderecos, id, nome, pf, pj, projeto, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(documentos, other.documentos) && Objects.equals(email, other.email)
				&& Objects.equals(enderecos, other.enderecos) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(pf, other.pf) && Objects.equals(pj, other.pj)
				&& Objects.equals(projeto, other.projeto) && Objects.equals(telefone, other.telefone);
	}

	
}
