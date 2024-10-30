package com.architrack.test.vo;

import java.util.Date;
import java.util.Objects;

import com.architrack.entities.Projeto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class HistoricoVo{

	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String descricaoMudanca;
	private Date dataMudanca;
	private Projeto projeto;
	
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataMudanca, descricaoMudanca, key, projeto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoricoVo other = (HistoricoVo) obj;
		return Objects.equals(dataMudanca, other.dataMudanca)
				&& Objects.equals(descricaoMudanca, other.descricaoMudanca) && Objects.equals(key, other.key)
				&& Objects.equals(projeto, other.projeto);
	}


}
