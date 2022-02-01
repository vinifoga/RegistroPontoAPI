package br.com.fogaca.RegistroPonto.model;

public class Geolocalizacao extends Registro{

	private Long codGeolocalizacao;
	private String descricao;
	
	public Long getCodGeolocalizacao() {
		return codGeolocalizacao;
	}
	public void setCodGeolocalizacao(Long codGeolocalizacao) {
		this.codGeolocalizacao = codGeolocalizacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
