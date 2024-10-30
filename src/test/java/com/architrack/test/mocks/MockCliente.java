package com.architrack.test.mocks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.architrack.entities.Cliente;
import com.architrack.entities.PessoaFisica;
import com.architrack.entities.PessoaJuridica;
import com.architrack.entities.Projeto;
import com.architrack.test.vo.ClienteVo;


public class MockCliente {

	
	public Cliente mockCliente(int number) throws ParseException{
		MockEndereco mockEndereco = new MockEndereco();
		Projeto projeto = new Projeto();
		projeto.setId((long) 3);
		MockDocumento mockDocumento = new MockDocumento();
		Cliente cliente = new Cliente();
		
		cliente.setDocumentos(mockDocumento.mockListDocumento());
		cliente.setEmail((number %2==0?"gustavo@gmail.com "+number:"imobile@gmail.com "+number));
		cliente.setEnderecos(mockEndereco.mockListEndereco());
		cliente.setId((long)number);
		cliente.setNome((number %2==0?"Gustavo Lima "+number:"Cristine Gomes "+number));
		cliente.setProjeto(projeto);
		cliente.setTelefone(number %2==0?"81-123456789 "+number:"18-987654321 "+number);
		
		if (number % 2 == 0) {
		    cliente.setPj(new PessoaJuridica("12.345.678/0001-90", "123.456.789.123", null));
		    cliente.setPf(null);
		} else {
		    cliente.setPf(new PessoaFisica("765321456-10", "897456321-59", null));
		    cliente.setPj(null);
		}
		return  cliente;
	}
	public ClienteVo mockClienteVo(int number) throws ParseException{
	
		ClienteVo vo = new ClienteVo();
		
		vo.setEmail(number %2==0?"imobile@gmail.com "+number:"gustavo@gmail.com "+number);
		vo.setKey((long)number);
		vo.setNome(number %2==0?"Cristine Gomes "+number:"Gustavo Lima "+number);
		vo.setTelefone(number %2==0?"18-987654321 "+number:"81-123456789 "+number);
		

		if (number % 2 == 0) {
		    vo.setPj(new PessoaJuridica("12.345.678/0001-90", "123.456.789.123", null));
		    vo.setPf(null);
		} else {
			vo.setPf(new PessoaFisica("765321456-10", "897456321-59", null));
			vo.setPj(null);
		}
		return  vo;
	}
	
	public Cliente mockCliente() throws ParseException{
		return mockCliente(1);
	}
	
	public ClienteVo mockClienteVo() throws ParseException{
		return mockClienteVo(1);
	}
	
	public List<Cliente> mockListCliente() throws ParseException{
		List<Cliente>list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			list.add(mockCliente(i));
		}
		return list;
	}
	
	public List<ClienteVo> mockListClienteVo() throws ParseException{
		List<ClienteVo>vo = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			vo.add(mockClienteVo(i));
		}
		return vo;
	}
}
