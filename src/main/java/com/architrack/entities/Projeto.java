package com.architrack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "area_empreendimento", nullable = false)
	private String areaEmpreendimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "projeto_arquiteto",joinColumns = {@JoinColumn(name = "id_projeto")},
	inverseJoinColumns = {@JoinColumn(name = "id_arquiteto")})
	private List<Arquiteto> arquitetos;
	
	@OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
	private List<Pagamentos> pagamentos;
	@OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
	private List<Documento> documentos;
	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
	private List<Agenda> agendamentos;
	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
	private List<Historico> listaHistorico;
	@OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
	private List<EspacoAgenda>listAgenda;
	public Projeto() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<EspacoAgenda> getListAgenda() {
		return listAgenda;
	}
	public void setListAgenda(List<EspacoAgenda> listAgenda) {
		this.listAgenda = listAgenda;
	}
	@Override
	public int hashCode() {
		return Objects.hash(agendamentos, areaEmpreendimento, arquitetos, cliente, documentos, id, listAgenda,
				listaHistorico, pagamentos);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		return Objects.equals(agendamentos, other.agendamentos)
				&& Objects.equals(areaEmpreendimento, other.areaEmpreendimento)
				&& Objects.equals(arquitetos, other.arquitetos) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(documentos, other.documentos) && Objects.equals(id, other.id)
				&& Objects.equals(listAgenda, other.listAgenda) && Objects.equals(listaHistorico, other.listaHistorico)
				&& Objects.equals(pagamentos, other.pagamentos);
	}

	
}
