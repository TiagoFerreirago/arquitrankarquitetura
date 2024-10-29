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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "arquiteto")
public class Arquiteto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable =false)
	private String nome;
	@Column(nullable =false, length = 15)
	private String telefone;
	@Column(nullable =false)
	private String email;
	
	@OneToMany(mappedBy = "arquiteto", cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
	
	@ManyToMany(mappedBy = "arquitetos")
	private List<Projeto> projetos;
	@OneToOne(mappedBy = "arquiteto", cascade = CascadeType.ALL, orphanRemoval = true)
	private TipoPessoa tipo;
	
	public Arquiteto() {
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
		return Objects.hash(email, enderecos, id, nome, projetos, telefone, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquiteto other = (Arquiteto) obj;
		return Objects.equals(email, other.email) && Objects.equals(enderecos, other.enderecos)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(projetos, other.projetos) && Objects.equals(telefone, other.telefone)
				&& Objects.equals(tipo, other.tipo);
	}

	
}

