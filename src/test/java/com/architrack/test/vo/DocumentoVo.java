package com.architrack.test.vo;

import java.util.Date;
import java.util.Objects;

import com.architrack.entities.Cliente;
import com.architrack.entities.Projeto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class DocumentoVo  {
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String nomeDocumento;
	private String tipoDocumento;
	private Date dataEnvio;
	private Cliente cliente;
	private Projeto projeto;
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataEnvio, key, nomeDocumento, projeto, tipoDocumento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoVo other = (DocumentoVo) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(dataEnvio, other.dataEnvio)
				&& Objects.equals(key, other.key) && Objects.equals(nomeDocumento, other.nomeDocumento)
				&& Objects.equals(projeto, other.projeto) && Objects.equals(tipoDocumento, other.tipoDocumento);
	}
	
	
}
