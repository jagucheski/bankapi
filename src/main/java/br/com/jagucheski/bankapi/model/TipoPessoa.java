package br.com.jagucheski.bankapi.model;

public enum TipoPessoa {

	PF("PESSOA_FISICA"), PJ("PESSOA_JURIDICA");

	private final String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
