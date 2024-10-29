package com.architrack.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","areaEmpreendimento","cliente","arquitetos","pagamentos"
		,"documentos","agendamentos","listaHistorico"})
public class ProjetoVo extends RepresentationModel<ProjetoVo> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String areaEmpreendimento;
	//private Cliente cliente;
	//private List<Arquiteto> arquitetos;
	//private List<Pagamentos> pagamentos;
	//private List<Documento> documentos;
	//private List<Agenda> agendamentos;
	//private List<Historico> listaHistorico;
	
	public ProjetoVo() {
		
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAreaEmpreendimento() {
		return areaEmpreendimento;
	}

	public void setAreaEmpreendimento(String areaEmpreendimento) {
		this.areaEmpreendimento = areaEmpreendimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(areaEmpreendimento, key);
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
		ProjetoVo other = (ProjetoVo) obj;
		return Objects.equals(areaEmpreendimento, other.areaEmpreendimento) && Objects.equals(key, other.key);
	}

	
}
