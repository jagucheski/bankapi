package br.com.jagucheski.bankapi.dto;

import br.com.jagucheski.bankapi.model.ClientePessoaJuridica;

public class ClienteDtoPJ {

	private Long id;
	private String razaoSocial;
	private String CNPJ;

	public ClienteDtoPJ() {
	}

	public ClienteDtoPJ(ClientePessoaJuridica clientePessoaJuridica) {
		this.id = clientePessoaJuridica.getId();
		this.razaoSocial = clientePessoaJuridica.getRazaoSocial();
		this.CNPJ = clientePessoaJuridica.getCNPJ();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

}
