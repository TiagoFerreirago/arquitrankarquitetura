package com.architrack.test.mocks;

import java.util.ArrayList;
import java.util.List;

import com.architrack.entities.Arquiteto;
import com.architrack.entities.Cliente;
import com.architrack.entities.Endereco;
import com.architrack.vo.v1.EnderecoVo;

public class MockEndereco {

	public Endereco mockEndereco(int number) {
		
		Arquiteto arquiteto = new Arquiteto();
		arquiteto.setId((long)2);
		Cliente cliente = new Cliente();
		cliente.setId((long)9);
		Endereco endereco = new Endereco();
		endereco.setArquiteto(arquiteto);
		endereco.setBairro((number%2==0?"Marilia "+number:"Pituba "+number));
		endereco.setCep((number%2==0?"41235-415 "+number:"41830-480 "+number));
		endereco.setCidade((number%2==0?"São Bernado "+number:"Salvador "+number));
		endereco.setCliente(cliente);
		endereco.setEstado((number%2==0?"SP":"BA"));
		endereco.setId((long)number);
		endereco.setRua(number%2==0?"Rua das Neves "+number:"Rua Ceará "+number);
		return endereco;
	}
	
	public EnderecoVo mockEnderecoVo(int number) {
		
		Arquiteto arquiteto = new Arquiteto();
		arquiteto.setId((long)2);
		Cliente cliente = new Cliente();
		cliente.setId((long)9);
		EnderecoVo vo = new EnderecoVo();
		vo.setBairro(number%2==0?"Pituba "+number:"Marilia "+number);
		vo.setCep((number%2==0?"41830-480 "+number:"41235-415 "+number));
		vo.setCidade((number%2==0?"Salvador "+number:"São Bernado "+number));
		vo.setEstado((number%2==0?"BA":"SP"));
		vo.setKey((long)number);
		vo.setRua(number%2==0?"Rua Ceará "+number:"Rua das Neves "+number);
		return vo;
	}
	
	public Endereco mockEndereco() {
		return mockEndereco(1);
		
	}
	
	public EnderecoVo mockEnderecoVo() {
		return mockEnderecoVo(1);
		
	}
	
	public List<Endereco> mockListEndereco(){
		List<Endereco>list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			list.add(mockEndereco(i));
		}
		return list;
	}
	
	public List<EnderecoVo> mockListEnderecoVo(){
		List<EnderecoVo>vo = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			vo.add(mockEnderecoVo(i));
		}
		return vo;
	}
	
}
