package com.architrack.test.mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.architrack.entities.Historico;
import com.architrack.entities.Projeto;
import com.architrack.vo.v1.HistoricoVo;

public class MockHistorico {

	
	public Historico mockHistorico(int number) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Projeto projeto = new Projeto();
		projeto.setId((long)4);
		Historico historico = new Historico();
		historico.setDataMudanca(format.parse("15/12/2024"));
		historico.setDescricaoMudanca((number%2==0?"Atraso de Matérial "+number:"Licença "+number));
		historico.setId((long)number);
		historico.setProjeto(projeto);
		return historico;
	}
	
	public HistoricoVo mockHistoricoVo(int number) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		HistoricoVo vo = new HistoricoVo();
		
		vo.setDataMudanca(format.parse("25/12/2024"));
		vo.setDescricaoMudanca((number%2==0?"Licença "+number:"Atraso de Matérial "+number));
		vo.setKey((long)number);
		return vo;
	}
	
	public Historico mockHistorico() throws ParseException {
		return mockHistorico(1);
		
	}
	
	public HistoricoVo mockHistoricoVo() throws ParseException {
		return mockHistoricoVo(1);
		
	}
	
	public List<Historico> mockListHistorico() throws ParseException{
		List<Historico>list = new ArrayList<>();
		
		for(int i = 0 ; i < 10; i++) {
			list.add(mockHistorico(i));
		}
		return list;
	}
	
	public List<HistoricoVo> mockListHistoricoVo() throws ParseException{
		List<HistoricoVo>vo = new ArrayList<>();
		
		for(int i = 0 ; i < 10; i++) {
			vo.add(mockHistoricoVo(i));
		}
		return vo;
	}
}
