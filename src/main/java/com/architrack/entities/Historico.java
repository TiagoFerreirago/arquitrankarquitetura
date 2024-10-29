package com.architrack.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "historico")
public class Historico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "descricao_mudanca")
	private String descricaoMudanca;
	@Temporal(TemporalType.DATE)
	private Date dataMudanca;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	private Projeto projeto;
	
	public Historico() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoMudanca() {
		return descricaoMudanca;
	}

	public void setDescricaoMudanca(String descricaoMudanca) {
		this.descricaoMudanca = descricaoMudanca;
	}

	public Date getDataMudanca() {
		return dataMudanca;
	}

	public void setDataMudanca(Date dataMudanca) {
		this.dataMudanca = dataMudanca;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataMudanca, descricaoMudanca, id, projeto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		return Objects.equals(dataMudanca, other.dataMudanca)
				&& Objects.equals(descricaoMudanca, other.descricaoMudanca) && Objects.equals(id, other.id)
				&& Objects.equals(projeto, other.projeto);
	}
	
	
}
