package br.com.fogaca.RegistroPonto.controller.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.fogaca.RegistroPonto.model.Registro;
import br.com.fogaca.RegistroPonto.model.StatusCorrecaoRegistro;
import br.com.fogaca.RegistroPonto.service.ColaboradorService;

public class RegistroForm {
	
	@NotNull
	private LocalDate data;
	@NotNull
	private LocalTime hora;
	private StatusCorrecaoRegistro status;
	private String mensagem;
	private Long colaboradorId;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public StatusCorrecaoRegistro getStatus() {
		return status;
	}
	public void setStatus(StatusCorrecaoRegistro status) {
		this.status = status;
	}
	public Long getColaboradorId() {
		return colaboradorId;
	}
	public void setColaboradorId(Long colaboradorId) {
		this.colaboradorId = colaboradorId;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Registro converter(ColaboradorService colaboradorService) {
		return new Registro(data, hora, status, mensagem, colaboradorService.findById(colaboradorId).get());
	}
}
