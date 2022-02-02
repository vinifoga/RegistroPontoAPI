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
	@GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
			@org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table") })
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	private Long codRegistro;
	@NotNull
	private LocalDate data;
	@NotNull
	private LocalTime hora;
	@Enumerated(EnumType.STRING)
	private StatusCorrecaoRegistro status;
	@ManyToOne
	private Colaborador colaborador;

	public Registro() {
		super();
	}

	public Registro(@NotNull LocalDate data, @NotNull LocalTime hora, StatusCorrecaoRegistro status,
			Colaborador colaborador) {
		super();
		this.data = data;
		this.hora = hora;
		this.status = status;
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

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
}
