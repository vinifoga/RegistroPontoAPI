package br.com.fogaca.RegistroPonto.model;

public class QRCode extends Registro {

	private Long codQrcode;
	private String descricao;
	
	public Long getCodQrcode() {
		return codQrcode;
	}
	public void setCodQrcode(Long codQrcode) {
		this.codQrcode = codQrcode;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
