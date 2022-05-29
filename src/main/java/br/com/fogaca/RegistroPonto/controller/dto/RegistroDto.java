package br.com.fogaca.RegistroPonto.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fogaca.RegistroPonto.model.Registro;
import br.com.fogaca.RegistroPonto.model.StatusCorrecaoRegistro;

public class RegistroDto {

	private Long codRegistro;
	private LocalDate data;
	private LocalTime hora;
	private StatusCorrecaoRegistro status;
	private String mensagem;
	private String colaboradorNome;
	
	public RegistroDto(Registro registro) {
		this.codRegistro = registro.getCodRegistro();
		this.data = registro.getData();
		this.hora = registro.getHora();
		this.status = registro.getStatus();
		this.mensagem = registro.getMensagem();
		this.colaboradorNome = registro.getColaborador().getNome();
	}

	public Long getCodRegistro() {
		return codRegistro;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public StatusCorrecaoRegistro getStatus() {
		return status;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public String getColaboradorNome() {
		return colaboradorNome;
	}
	
	public static Page<RegistroDto> converterRegistro(Page<Registro> registros){
		return registros.map(RegistroDto::new);
	}
	
	public static List<RegistroDto> converterRegistroList(List<Registro> registros){
		return registros.stream().map(RegistroDto::new).collect(Collectors.toList());
	}
}
