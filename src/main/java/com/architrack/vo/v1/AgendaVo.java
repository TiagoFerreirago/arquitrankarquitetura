package com.architrack.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","dataInicio","dataFim","projeto"})
public class AgendaVo extends RepresentationModel<AgendaVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private Date dataInicio;
	private Date dataFim;
	
	//private Projeto projeto;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataFim, dataInicio, key);
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
				&& Objects.equals(key, other.key);
	}

}
