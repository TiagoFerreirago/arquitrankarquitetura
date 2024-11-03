package com.architrack.test.mocks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.architrack.entities.Cliente;
import com.architrack.entities.Projeto;
import com.architrack.vo.v1.ProjetoVo;

public class MockProjeto {

	public Projeto mockProjeto(int number) throws ParseException {
		MockAgenda mockAgenda = new MockAgenda();
		MockArquiteto mockArquiteto = new MockArquiteto();
		MockDocumento mockDocumento = new MockDocumento();
		MockHistorico mockHistorico = new MockHistorico();
		MockPagamento mockPagamento = new MockPagamento();
		Cliente cliente = new Cliente();
		cliente.setId((long) 2);
		Projeto projeto = new Projeto();
		
		projeto.setAgendamentos(mockAgenda.mockListAgenda());
		projeto.setAreaEmpreendimento((number%2==0?"Construção civil "+number:"Arquitetura Comercial "+number));
		projeto.setArquitetos(mockArquiteto.mockListArquiteto());
		projeto.setCliente(cliente);
		projeto.setDocumentos(mockDocumento.mockListDocumento());
		projeto.setId((long)number);
		projeto.setListaHistorico(mockHistorico.mockListHistorico());
		projeto.setPagamentos(mockPagamento.mockListPagamentos());
		return projeto;
	}
	
	public ProjetoVo mockProjetoVo(int number) throws ParseException {
		
		ProjetoVo projetoVo = new ProjetoVo();
		
		projetoVo.setAreaEmpreendimento((number%2==0?"Arquitetura Comercial "+number:"Construção civil "+number));
		projetoVo.setKey((long)number);
		
		return projetoVo;
	}
	
	public Projeto mockProjeto() throws ParseException {
		return mockProjeto(1);
		
	}
	
	public ProjetoVo mockProjetoVo() throws ParseException {
		return mockProjetoVo(1);
		
	}
	
	public List<Projeto> mockListProjeto() throws ParseException{
		List<Projeto> projetos = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			projetos.add(mockProjeto(i));
		}
		return projetos;
	}
	
	public List<ProjetoVo> mockListProjetoVo() throws ParseException{
		List<ProjetoVo> projetosVo = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			projetosVo.add(mockProjetoVo(i));
		}
		return projetosVo;
	}
}
