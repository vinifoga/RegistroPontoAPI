package br.com.fogaca.RegistroPonto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Departamento {

	@Id
	@GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
			@org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table") })
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	private Long id;
	@NotNull
	@NotEmpty
	private String descricao;

	public Departamento() {
		super();
	}

	public Departamento(String descricao) {
		super();
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
