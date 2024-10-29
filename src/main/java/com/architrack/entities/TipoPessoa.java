package com.architrack.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Table
@Entity
public class TipoPessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(nullable = false)
	private String tipo;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_arquiteto")
	
	private Arquiteto arquiteto;
	
	public TipoPessoa() {
		
	}

	public TipoPessoa(String tipo, Arquiteto arquiteto) {
		this.tipo = tipo;
		this.arquiteto = arquiteto;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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
		return Objects.hash(Id, arquiteto, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPessoa other = (TipoPessoa) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(arquiteto, other.arquiteto)
				&& Objects.equals(tipo, other.tipo);
	}
	
	
}
