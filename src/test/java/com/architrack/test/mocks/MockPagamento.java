package com.architrack.test.mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.architrack.entities.Pagamentos;
import com.architrack.entities.Projeto;
import com.architrack.test.vo.PagamentosVo;

public class MockPagamento {

	
	public Pagamentos mockPagamentos(int number) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Projeto projeto = new Projeto();
		projeto.setId((long)3);
		Pagamentos pagamentos = new Pagamentos();
		
		pagamentos.setDataPagamento(format.parse("08/11/2024"));
		pagamentos.setId((long)number);
		pagamentos.setProjeto(projeto);
		pagamentos.setStatus((number%2==0?"Concluido "+number:"Aguardando "+number));
		pagamentos.setValor((number%2==0?54000.00+number:78000.00+number));
		return pagamentos;
	}
	
	public PagamentosVo mockPagamentosVo(int number) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Projeto projeto = new Projeto();
		projeto.setId((long)5);
		PagamentosVo vo = new PagamentosVo();
		
		vo.setDataPagamento(format.parse("18/10/2024"));
		vo.setKey((long)number);
		vo.setStatus((number%2==0?"Aguardando "+number:"Concluido "+number));
		vo.setValor((number%2==0?78000D +number:54000D +number));
		return vo;
	}
	
	public Pagamentos mockPagamentos() throws ParseException {
		
		return mockPagamentos(1);
	}
	
	public PagamentosVo mockPagamentosVo() throws ParseException {
		
		return mockPagamentosVo(1);
	}
	
	public List<Pagamentos> mockListPagamentos() throws ParseException{
		List<Pagamentos> pagamentos = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			pagamentos.add(mockPagamentos(i));
		}
		return pagamentos;
	}
	
	public List<PagamentosVo> mockListPagamentosVo() throws ParseException{
		List<PagamentosVo> pagamentosVo = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			pagamentosVo.add(mockPagamentosVo(i));
		}
		return pagamentosVo;
	}
}
