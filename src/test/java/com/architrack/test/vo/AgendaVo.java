package com.architrack.test.vo;

import java.util.Date;
import java.util.Objects;

import com.architrack.entities.Projeto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class AgendaVo {

	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private Date dataInicio;
	private Date dataFim;
	private Projeto projeto;
	
	public AgendaVo() {
	
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataFim, dataInicio, key, projeto);
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
		AgendaVo other = (AgendaVo) obj;
		return Objects.equals(dataFim, other.dataFim) && Objects.equals(dataInicio, other.dataInicio)
				&& Objects.equals(key, other.key) && Objects.equals(projeto, other.projeto);
	}
	
	
}
