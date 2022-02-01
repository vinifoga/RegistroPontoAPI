package br.com.fogaca.RegistroPonto.model;

import java.time.LocalDateTime;

public class Solicitacao {

	private Long codArquivo;
	private String descricao;
	private LocalDateTime dataHora;
	private String diretorio;
	
	public Long getCodArquivo() {
		return codArquivo;
	}
	public void setCodArquivo(Long codArquivo) {
		this.codArquivo = codArquivo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	
	
}
