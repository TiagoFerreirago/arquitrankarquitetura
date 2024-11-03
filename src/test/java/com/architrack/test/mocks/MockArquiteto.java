package com.architrack.test.mocks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.architrack.entities.Arquiteto;
import com.architrack.entities.Endereco;
import com.architrack.entities.Projeto;
import com.architrack.entities.TipoPessoa;
import com.architrack.vo.v1.ArquitetoVo;


public class MockArquiteto {

	
	public Arquiteto mockArquiteto(int number) throws ParseException {
		TipoPessoa pessoa = new TipoPessoa();
		pessoa.setId((long)5);
		pessoa.setTipo((number%2==0?"Pessoa Juridica "+number:"Pessoa Fisica "+number));
		
		List<Endereco> listEndereco = new ArrayList<>();
		listEndereco.add(new Endereco());
		List<Projeto> listProjeto = new ArrayList<>();
		listProjeto.add(new Projeto());
		
		Arquiteto arq = new Arquiteto();
		arq.setEmail((number%2==0?"123@gmail.com "+number:"987@gmail.com "+number));
		arq.setEnderecos(listEndereco);
		arq.setId((long)number);
		arq.setNome(number%2==0?"Anna Green "+number:"Harry Kane "+number);
		arq.setProjetos(listProjeto);
		arq.setTelefone(number%2==0?"123456789 "+number:"987654321 "+number);
		arq.setTipo(pessoa);
		return arq;
	}
	public ArquitetoVo mockArquitetoVo(int number) throws ParseException {
		List<Endereco> listEndereco = new ArrayList<>();
		listEndereco.add(new Endereco());
		List<Projeto> listProjeto = new ArrayList<>();
		listProjeto.add(new Projeto());
		
		ArquitetoVo vo = new ArquitetoVo();
		TipoPessoa pessoa = new TipoPessoa();
		pessoa.setId((long)5);
		pessoa.setTipo(number%2==0?"Pessoa Fisica "+number:"Pessoa Juridica "+number);
		vo.setEmail(number%2==0?"987@gmail.com "+number:"123@gmail.com "+number);
		vo.setEnderecos(listEndereco);
		vo.setKey((long)number);
		vo.setNome((number%2==0?"Harry Kane "+number:"Anna Green "+number));
		vo.setProjetos(listProjeto);
		vo.setTelefone((number%2==0?"987654321 "+number:"123456789 "+number));
		vo.setTipo(pessoa);
		return vo;
	}
	
	public Arquiteto mockArquiteto() throws ParseException {
		return mockArquiteto(1);
	}
	
	public ArquitetoVo mockArquitetoVo() throws ParseException {
		return mockArquitetoVo(1);
	}
	
	public List<Arquiteto>mockListArquiteto() throws ParseException{
		List<Arquiteto> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			list.add(mockArquiteto(i));
		}
		return list;
	}
	public List<ArquitetoVo>mockListArquitetoVo() throws ParseException{
		List<ArquitetoVo> vo = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			vo.add(mockArquitetoVo(i));
		}
		return vo;
	}
}
