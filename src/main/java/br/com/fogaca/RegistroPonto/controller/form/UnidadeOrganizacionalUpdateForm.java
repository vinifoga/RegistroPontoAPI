package br.com.fogaca.RegistroPonto.controller.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.fogaca.RegistroPonto.model.UnidadeOrganizacional;
import br.com.fogaca.RegistroPonto.service.UnidadeOrganizacionalService;

public class UnidadeOrganizacionalUpdateForm {

	@NotNull @NotEmpty
	private String descricao;
	@CNPJ
	private String cnpj;
	@NotNull
	private LocalDate abertura;
	@Type(type="true_false")
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
	
	public UnidadeOrganizacional update(Long id, UnidadeOrganizacionalService unidadeService) {
		UnidadeOrganizacional unidade = unidadeService.findById(id).get();
		unidade.setDescricao(this.descricao);
		unidade.setCnpj(this.cnpj);
		unidade.setAbertura(this.abertura);
		unidade.setAtivo(this.ativo);
		unidade.setTelefone(this.telefone);
		unidade.setCep(this.cep);
		unidade.setRua(this.rua);
		unidade.setNumero(this.numero);
		unidade.setBairro(this.bairro);
		unidade.setCidade(this.cidade);
		unidade.setEstado(this.estado);
		unidade.setHoraFuncionaInicio(this.horaFuncionaInicio);
		unidade.setHoraFuncionaFim(this.horaFuncionaFim);
		return unidade;
	}
	
}
