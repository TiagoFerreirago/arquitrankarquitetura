package com.architrack.test.mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.architrack.entities.Cliente;
import com.architrack.entities.Documento;
import com.architrack.entities.Projeto;
import com.architrack.test.vo.DocumentoVo;

public class MockDocumento {

	public Documento mockDocumento(int number) throws ParseException {
		Cliente cliente = new Cliente();
		cliente.setId((long)2);
		Projeto projeto = new Projeto();
		projeto.setId((long)3);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Documento documento = new Documento();
		documento.setCliente(cliente);
		documento.setDataEnvio(format.parse("25/12/2024"));
		documento.setId((long)number);
		documento.setNomeDocumento((number%2==0?"Projeto Estrutural "+number:"Licença Para Construção "+number));
		documento.setProjeto(projeto);
		documento.setTipoDocumento((number%2==0?"Técnico "+number:"Licenças "+number));
		return documento;
	}
	
		
		public DocumentoVo mockDocumentoVo(int number) throws ParseException {
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			DocumentoVo vo = new DocumentoVo();
			vo.setDataEnvio(format.parse("11/12/2024"));
			vo.setKey((long)number);
			vo.setNomeDocumento((number%2==0?"Licença Para Construção "+number:"Projeto Estrutural "+number));
			vo.setTipoDocumento((number%2==0?"Licenças "+number:"Técnico "+number));
			return vo;
		}
		
		public Documento mockDocumento() throws ParseException {
			return mockDocumento(1);
			
		}
		
		public DocumentoVo mockDocumentoVo() throws ParseException {
			return mockDocumentoVo(1);
			
		}
		
		public List<Documento>mockListDocumento() throws ParseException{
			List<Documento> list = new ArrayList<>();
			for(int i = 0; i < 10; i++) {
				list.add(mockDocumento(i));
			}
			return list;
		}
		
		public List<DocumentoVo>mockListDocumentoVo() throws ParseException{
			List<DocumentoVo> vo = new ArrayList<>();
			for(int i = 0; i < 10; i++) {
				vo.add(mockDocumentoVo(i));
			}
			return vo;
		}
}
