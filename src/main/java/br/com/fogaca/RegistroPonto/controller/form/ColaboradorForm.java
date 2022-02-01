package br.com.fogaca.RegistroPonto.controller.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CPF;

import br.com.fogaca.RegistroPonto.model.Colaborador;
import br.com.fogaca.RegistroPonto.service.CargoService;
import br.com.fogaca.RegistroPonto.service.UnidadeOrganizacionalService;

public class ColaboradorForm {

	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String nome;
	@NotNull
	private LocalDate dataNascimento;	
	@Type(type="true_false")
	private boolean ativo;
	private String telefone;
	@NotNull
	private Long cargoId;
	@NotNull
	private LocalDate dataAdmissao;
	@NotNull
	@CPF
	private String cpf;
	@NotNull
	private String pis;
	private LocalTime horaEntra;
	private LocalTime horaSai;
	private LocalTime intervaloTempo;
	@Type(type="true_false")
	private boolean trabalhaTodosSabados;
	@Type(type="true_false")
	private boolean trabalhaSabadosAlternados;
	@Type(type="true_false")
	private boolean homeOffice;	
	private LocalTime horaEntraSabado;
	private LocalTime horaSaiSabado;
	private Long unidadeOrganizacionalId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public Long getCargoId() {
		return cargoId;
	}
	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPis() {
		return pis;
	}
	public void setPis(String pis) {
		this.pis = pis;
	}
	public LocalTime getHoraEntra() {
		return horaEntra;
	}
	public void setHoraEntra(LocalTime horaEntra) {
		this.horaEntra = horaEntra;
	}
	public LocalTime getHoraSai() {
		return horaSai;
	}
	public void setHoraSai(LocalTime horaSai) {
		this.horaSai = horaSai;
	}
	public LocalTime getIntervaloTempo() {
		return intervaloTempo;
	}
	public void setIntervaloTempo(LocalTime intervaloTempo) {
		this.intervaloTempo = intervaloTempo;
	}
	
	public boolean isTrabalhaTodosSabados() {
		return trabalhaTodosSabados;
	}
	public void setTrabalhaTodosSabados(boolean trabalhaTodosSabados) {
		this.trabalhaTodosSabados = trabalhaTodosSabados;
	}
	public boolean isTrabalhaSabadosAlternados() {
		return trabalhaSabadosAlternados;
	}
	public void setTrabalhaSabadosAlternados(boolean trabalhaSabadosAlternados) {
		this.trabalhaSabadosAlternados = trabalhaSabadosAlternados;
	}
	public boolean isHomeOffice() {
		return homeOffice;
	}
	public void setHomeOffice(boolean homeOffice) {
		this.homeOffice = homeOffice;
	}
	public LocalTime getHoraEntraSabado() {
		return horaEntraSabado;
	}
	public void setHoraEntraSabado(LocalTime horaEntraSabado) {
		this.horaEntraSabado = horaEntraSabado;
	}
	public LocalTime getHoraSaiSabado() {
		return horaSaiSabado;
	}
	public void setHoraSaiSabado(LocalTime horaSaiSabado) {
		this.horaSaiSabado = horaSaiSabado;
	}
	public Long getUnidadeOrganizacionalId() {
		return unidadeOrganizacionalId;
	}
	public void setUnidadeOrganizacionalId(Long unidadeOrganizacionalId) {
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
	}
	
	public Colaborador converter(CargoService cargoService, UnidadeOrganizacionalService unidadeOrganizacionalService) {
		return new Colaborador(email, nome, dataNascimento, ativo, telefone, cargoService.findById(cargoId).get(), dataAdmissao, cpf, pis, horaEntra, horaSai, intervaloTempo, trabalhaTodosSabados, trabalhaSabadosAlternados, homeOffice, horaEntraSabado, horaSaiSabado, unidadeOrganizacionalService.findById(unidadeOrganizacionalId).get());
	}
	
}
