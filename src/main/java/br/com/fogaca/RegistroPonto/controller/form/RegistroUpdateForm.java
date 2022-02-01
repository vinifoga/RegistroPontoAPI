package br.com.fogaca.RegistroPonto.controller.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.fogaca.RegistroPonto.model.Registro;
import br.com.fogaca.RegistroPonto.model.StatusCorrecaoRegistro;
import br.com.fogaca.RegistroPonto.service.ColaboradorService;
import br.com.fogaca.RegistroPonto.service.RegistroService;

public class RegistroUpdateForm {

	@NotNull
	private LocalDate data;
	@NotNull
	private LocalTime hora;
	private StatusCorrecaoRegistro status;
	
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
	
	public Registro update(Long id, RegistroService registroService, ColaboradorService colaboradorService) {
		Registro registro = registroService.findById(id).get();
		registro.setData(this.data);
		registro.setHora(this.hora);
		registro.setStatus(this.status);
		return registro;
	}
}
