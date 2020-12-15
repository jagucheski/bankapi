package br.com.jagucheski.bankapi.dto;

import br.com.jagucheski.bankapi.model.ClientePessoaFisica;

public class ClienteDtoPF {

	private Long id;
	private String nome;
	private String CPF;

	public ClienteDtoPF() {
	}

	public ClienteDtoPF(ClientePessoaFisica clientePessoaFisica) {
		this.id = clientePessoaFisica.getId();
		this.nome = clientePessoaFisica.getNome();
		this.CPF = clientePessoaFisica.getCPF();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

}
