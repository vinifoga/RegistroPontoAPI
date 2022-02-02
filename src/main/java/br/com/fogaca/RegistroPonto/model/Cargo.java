package br.com.fogaca.RegistroPonto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cargo {

	@Id
	@GenericGenerator(name = "table", strategy = "enhanced-table", parameters = {
			@org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table") })
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	private Long id;
	private String descricao;
	private String cbo;
	@ManyToOne
	private Departamento departamento;

	public Cargo() {
		super();
	}

	public Cargo(String descricao, String cbo, Departamento departamento) {
		super();
		this.descricao = descricao;
		this.cbo = cbo;
		this.departamento = departamento;
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

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
