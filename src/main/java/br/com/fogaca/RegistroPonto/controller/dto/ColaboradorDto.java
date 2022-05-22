package br.com.fogaca.RegistroPonto.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fogaca.RegistroPonto.model.Colaborador;

public class ColaboradorDto {

	private Long matricula;
	private String email;
	private String nome;
	private LocalDate dataNascimento;	
	private boolean ativo;
	private String telefone;
	private String cargo;
	private LocalDate dataAdmissao;
	private String cpf;
	private String pis;
	private LocalTime horaEntra;
	private LocalTime horaSai;
	private LocalTime intervaloTempo;
	private boolean trabalhaTodosSabados;
	private boolean trabalhaSabadosAlternados;
	private boolean homeOffice;	
	private LocalTime horaEntraSabado;
	private LocalTime horaSaiSabado;
	private String unidadeOrganizacional;
	private LocalTime saldoAcumulado;
	
	public ColaboradorDto(Colaborador colaborador) {
		this.matricula = colaborador.getMatricula();
		this.email = colaborador.getEmail();
		this.nome = colaborador.getNome();
		this.dataNascimento = colaborador.getDataNascimento();
		this.ativo = colaborador.isAtivo();
		this.telefone = colaborador.getTelefone();
		this.cargo = colaborador.getCargo().getDescricao();
		this.dataAdmissao = colaborador.getDataAdmissao();
		this.cpf = colaborador.getCpf();
		this.pis = colaborador.getPis();
		this.horaEntra = colaborador.getHoraEntra();
		this.horaSai = colaborador.getHoraSai();
		this.intervaloTempo = colaborador.getIntervaloTempo();
		this.trabalhaTodosSabados = colaborador.isTrabalhaTodosSabados();
		this.trabalhaSabadosAlternados = colaborador.isTrabalhaSabadosAlternados();
		this.homeOffice = colaborador.isHomeOffice();
		this.horaEntraSabado = colaborador.getHoraEntraSabado();
		this.horaSaiSabado = colaborador.getHoraSaiSabado();
		this.unidadeOrganizacional = colaborador.getUnidadeOrganizacional().getDescricao();
		this.saldoAcumulado = colaborador.getSaldoAcumulado();
	}

	public Long getMatricula() {
		return matricula;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCargo() {
		return cargo;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPis() {
		return pis;
	}

	public LocalTime getHoraEntra() {
		return horaEntra;
	}

	public LocalTime getHoraSai() {
		return horaSai;
	}

	public LocalTime getIntervaloTempo() {
		return intervaloTempo;
	}

	public boolean isTrabalhaTodosSabados() {
		return trabalhaTodosSabados;
	}

	public boolean isTrabalhaSabadosAlternados() {
		return trabalhaSabadosAlternados;
	}

	public boolean isHomeOffice() {
		return homeOffice;
	}

	public LocalTime getHoraEntraSabado() {
		return horaEntraSabado;
	}

	public LocalTime getHoraSaiSabado() {
		return horaSaiSabado;
	}
	
	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}
	
	public LocalTime getSaldoAcumulado() {
		return saldoAcumulado;
	}
	
	public static List<ColaboradorDto> converterColaborador(List<Colaborador> colaboradores){
		return colaboradores.stream().map(ColaboradorDto::new).collect(Collectors.toList());
	}	
	
}
