package br.com.fogaca.RegistroPonto.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.GenerationType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class UnidadeOrganizacional {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codUnidade;
	@NotNull
	@NotEmpty
	private String descricao;
	@CNPJ
	private String cnpj;
	@NotNull
	private LocalDate abertura;
	@Type(type = "true_false")
	private boolean ativo;
	private String telefone;
	private String cep;
	@NotNull
	private String rua;
	@NotNull
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private LocalTime horaFuncionaInicio;
	private LocalTime horaFuncionaFim;

	public UnidadeOrganizacional() {
		super();
	}

	public UnidadeOrganizacional(@NotNull @NotEmpty String descricao, @CNPJ String cnpj, @NotNull LocalDate abertura,
			boolean ativo, String telefone, String cep, @NotNull String rua, @NotNull String numero, String bairro,
			String cidade, String estado, LocalTime horaFuncionaInicio, LocalTime horaFuncionaFim) {
		super();
		this.descricao = descricao;
		this.cnpj = cnpj;
		this.abertura = abertura;
		this.ativo = ativo;
		this.telefone = telefone;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.horaFuncionaInicio = horaFuncionaInicio;
		this.horaFuncionaFim = horaFuncionaFim;
	}

	public Long getCodUnidade() {
		return codUnidade;
	}

	public void setCodUnidade(Long codUnidade) {
		this.codUnidade = codUnidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public LocalDate getAbertura() {
		return abertura;
	}

	public void setAbertura(LocalDate abertura) {
		this.abertura = abertura;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalTime getHoraFuncionaInicio() {
		return horaFuncionaInicio;
	}

	public void setHoraFuncionaInicio(LocalTime horaFuncionaInicio) {
		this.horaFuncionaInicio = horaFuncionaInicio;
	}

	public LocalTime getHoraFuncionaFim() {
		return horaFuncionaFim;
	}

	public void setHoraFuncionaFim(LocalTime horaFuncionaFim) {
		this.horaFuncionaFim = horaFuncionaFim;
	}
}
