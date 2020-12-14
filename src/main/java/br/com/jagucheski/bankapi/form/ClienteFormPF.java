package br.com.jagucheski.bankapi.form;

import br.com.jagucheski.bankapi.model.Cliente;
import br.com.jagucheski.bankapi.model.PessoaFisica;
import br.com.jagucheski.bankapi.model.TipoPessoa;

public class ClienteFormPF {

	private String nome;
	private String CPF;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		CPF = cpf;
	}

	public PessoaFisica toPessoaFisica() {
		Cliente cliente = new Cliente(TipoPessoa.PF);
		PessoaFisica pf = new PessoaFisica(this.nome, this.CPF, cliente);
		return pf;
	}

}
