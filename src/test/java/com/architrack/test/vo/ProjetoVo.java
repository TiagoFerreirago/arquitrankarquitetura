package com.architrack.test.vo;

import java.util.List;
import java.util.Objects;

import com.architrack.entities.Agenda;
import com.architrack.entities.Arquiteto;
import com.architrack.entities.Cliente;
import com.architrack.entities.Documento;
import com.architrack.entities.Historico;
import com.architrack.entities.Pagamentos;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;


public class ProjetoVo{

	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String areaEmpreendimento;
	private Cliente cliente;
	private List<Arquiteto> arquitetos;
	private List<Pagamentos> pagamentos;
	private List<Documento> documentos;
	private List<Agenda> agendamentos;
	private List<Historico> listaHistorico;
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Arquiteto> getArquitetos() {
		return arquitetos;
	}

	public void setArquitetos(List<Arquiteto> arquitetos) {
		this.arquitetos = arquitetos;
	}

	public List<Pagamentos> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamentos> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<Agenda> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agenda> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<Historico> getListaHistorico() {
		return listaHistorico;
	}

	public void setListaHistorico(List<Historico> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agendamentos, areaEmpreendimento, arquitetos, cliente, documentos, key, listaHistorico,
				pagamentos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoVo other = (ProjetoVo) obj;
		return Objects.equals(agendamentos, other.agendamentos)
				&& Objects.equals(areaEmpreendimento, other.areaEmpreendimento)
				&& Objects.equals(arquitetos, other.arquitetos) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(documentos, other.documentos) && Objects.equals(key, other.key)
				&& Objects.equals(listaHistorico, other.listaHistorico) && Objects.equals(pagamentos, other.pagamentos);
	}
	
	
}
