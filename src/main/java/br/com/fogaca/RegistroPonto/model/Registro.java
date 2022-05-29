package br.com.fogaca.RegistroPonto.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Registro {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codRegistro;
	@NotNull
	private LocalDate data;
	@NotNull
	private LocalTime hora;
	@Enumerated(EnumType.STRING)
	private StatusCorrecaoRegistro status;
	private String mensagem;
	@ManyToOne
	private Colaborador colaborador;

	public Registro() {
		super();
	}

	public Registro(@NotNull LocalDate data, @NotNull LocalTime hora, StatusCorrecaoRegistro status, String mensagem,
			Colaborador colaborador) {
		super();
		this.data = data;
		this.hora = hora;
		this.status = status;
		this.mensagem = mensagem;
		this.colaborador = colaborador;
	}

	public Long getCodRegistro() {
		return codRegistro;
	}

	public void setCodRegistro(Long codRegistro) {
		this.codRegistro = codRegistro;
	}

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

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
}
