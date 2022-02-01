package br.com.fogaca.RegistroPonto.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fogaca.RegistroPonto.model.UnidadeOrganizacional;

public class UnidadeOrganizacionalDto {

	private Long codUnidade;
	private String descricao;
	private String cnpj;
	private LocalDate abertura;
	private boolean ativo;
	private String telefone;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private LocalTime horaFuncionaInicio;
	private LocalTime horaFuncionaFim;
	
	public UnidadeOrganizacionalDto(UnidadeOrganizacional unidade) {
		this.codUnidade = unidade.getCodUnidade();
		this.descricao = unidade.getDescricao();
		this.cnpj = unidade.getCnpj();
		this.abertura = unidade.getAbertura();
		this.ativo = unidade.isAtivo();
		this.telefone = unidade.getTelefone();
		this.cep = unidade.getCep();
		this.rua = unidade.getRua();
		this.numero = unidade.getNumero();
		this.bairro = unidade.getBairro();
		this.cidade = unidade.getCidade();
		this.estado = unidade.getEstado();
		this.horaFuncionaInicio = unidade.getHoraFuncionaInicio();
		this.horaFuncionaFim = unidade.getHoraFuncionaFim();
	}
	
	public Long getCodUnidade() {
		return codUnidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public LocalDate getAbertura() {
		return abertura;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public LocalTime getHoraFuncionaInicio() {
		return horaFuncionaInicio;
	}

	public LocalTime getHoraFuncionaFim() {
		return horaFuncionaFim;
	}
	
	public static List<UnidadeOrganizacionalDto> converterUnidade(List<UnidadeOrganizacional> unidades){
		return unidades.stream().map(UnidadeOrganizacionalDto::new).collect(Collectors.toList());
	}
	
}
