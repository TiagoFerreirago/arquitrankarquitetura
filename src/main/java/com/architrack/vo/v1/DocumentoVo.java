package com.architrack.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","nomeDocumento","tipoDocumento","dataEnvio"})
public class DocumentoVo extends RepresentationModel<DocumentoVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String nomeDocumento;
	private String tipoDocumento;
	private Date dataEnvio;
	//private Cliente cliente;
	//private Projeto projeto;
	
	public DocumentoVo() {
		
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataEnvio, key, nomeDocumento, tipoDocumento);
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
		DocumentoVo other = (DocumentoVo) obj;
		return Objects.equals(dataEnvio, other.dataEnvio) && Objects.equals(key, other.key)
				&& Objects.equals(nomeDocumento, other.nomeDocumento)
				&& Objects.equals(tipoDocumento, other.tipoDocumento);
	}

	
}
