package com.architrack.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","descricaoMudanca","dataMudanca","projeto"})
public class HistoricoVo extends RepresentationModel<HistoricoVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String descricaoMudanca;
	private Date dataMudanca;
	//private Projeto projeto;
	
	public HistoricoVo() {
		
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataMudanca, descricaoMudanca, key);
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
		HistoricoVo other = (HistoricoVo) obj;
		return Objects.equals(dataMudanca, other.dataMudanca)
				&& Objects.equals(descricaoMudanca, other.descricaoMudanca) && Objects.equals(key, other.key);
	}
	
	
}
