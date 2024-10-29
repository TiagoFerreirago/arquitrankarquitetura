package com.architrack.exception;

import java.util.Date;

public class ResponseException {

	private String descricao;
	private String mensagem;
	private Date data;
	
	public ResponseException() {
		
	}

	public ResponseException(String descricao, String mensagem, Date data) {
	
		this.descricao = descricao;
		this.mensagem = mensagem;
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
