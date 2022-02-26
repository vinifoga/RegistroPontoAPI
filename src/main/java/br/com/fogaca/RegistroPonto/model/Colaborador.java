package br.com.fogaca.RegistroPonto.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Colaborador {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricula;
	@NotNull
	@NotEmpty
	@Email
	private String email;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	private LocalDate dataNascimento;
	@Type(type = "true_false")
	private boolean ativo;
	private String telefone;
	@ManyToOne
	private Cargo cargo;
	@NotNull
	private LocalDate dataAdmissao;
	@CPF
	private String cpf;
	@NotNull
	private String pis;
	private LocalTime horaEntra;
	private LocalTime horaSai;
	private LocalTime intervaloTempo;
	@Type(type = "true_false")
	private boolean trabalhaTodosSabados;
	@Type(type = "true_false")
	private boolean trabalhaSabadosAlternados;
	@Type(type = "true_false")
	private boolean homeOffice;
//	@ElementCollection(targetClass = DiasHomeOffice.class, fetch = FetchType.EAGER)
//	@Enumerated(EnumType.STRING)
//	private List<DiasHomeOffice> diasHomeOffice;
	private LocalTime horaEntraSabado;
	private LocalTime horaSaiSabado;
	@ManyToOne
	private UnidadeOrganizacional unidadeOrganizacional;
	@OneToMany(mappedBy = "colaborador")
	@JsonIgnore
	private List<Registro> registro;

	public Colaborador() {
		super();
	}

	public Colaborador(@NotNull @NotEmpty String email, @NotNull @NotEmpty String nome,
			@NotNull LocalDate dataNascimento, boolean ativo, String telefone, @NotNull Cargo cargo,
			@NotNull LocalDate dataAdmissao, @NotNull String cpf, @NotNull String pis, LocalTime horaEntra,
			LocalTime horaSai, LocalTime intervaloTempo, boolean trabalhaTodosSabados,
			boolean trabalhaSabadorAlternados, boolean homeOffice, LocalTime horaEntraSabado, LocalTime horaSaiSabado,
			UnidadeOrganizacional unidadeOrganizacional) {
		super();
		this.email = email;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.ativo = ativo;
		this.telefone = telefone;
		this.cargo = cargo;
		this.dataAdmissao = dataAdmissao;
		this.cpf = cpf;
		this.pis = pis;
		this.horaEntra = horaEntra;
		this.horaSai = horaSai;
		this.intervaloTempo = intervaloTempo;
		this.trabalhaTodosSabados = trabalhaTodosSabados;
		this.trabalhaSabadosAlternados = trabalhaSabadorAlternados;
		this.homeOffice = homeOffice;
		this.horaEntraSabado = horaEntraSabado;
		this.horaSaiSabado = horaSaiSabado;
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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

//	public List<DiasHomeOffice> getDiasHomeOffice() {
//		return diasHomeOffice;
//	}
//	
//	public void setDiasHomeOffice(List<DiasHomeOffice> diasHomeOffice) {
//		this.diasHomeOffice = diasHomeOffice;
//	}

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

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public List<Registro> getRegistro() {
		return registro;
	}

	public void setRegistro(List<Registro> registro) {
		this.registro = registro;
	}

}
