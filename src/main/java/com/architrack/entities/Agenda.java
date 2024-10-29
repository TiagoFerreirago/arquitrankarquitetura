package com.architrack.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim")
	private Date dataFim;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_projeto")
	private Projeto projeto;
	@OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
	private List<EspacoAgenda> listAgenda;
	
	public Agenda() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<EspacoAgenda> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<EspacoAgenda> listAgenda) {
		this.listAgenda = listAgenda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataFim, dataInicio, id, listAgenda, projeto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		return Objects.equals(dataFim, other.dataFim) && Objects.equals(dataInicio, other.dataInicio)
				&& Objects.equals(id, other.id) && Objects.equals(listAgenda, other.listAgenda)
				&& Objects.equals(projeto, other.projeto);
	}

	
}
