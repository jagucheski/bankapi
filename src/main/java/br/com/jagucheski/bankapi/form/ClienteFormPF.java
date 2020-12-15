package br.com.jagucheski.bankapi.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jagucheski.bankapi.model.Cliente;
import br.com.jagucheski.bankapi.model.ClientePessoaFisica;
import br.com.jagucheski.bankapi.model.TipoPessoa;

public class ClienteFormPF {

	@NotBlank(message = "nome é obrigatório")
	@Size(min = 5, max = 250)
	private String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	@Size(min = 11, max = 20)
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

	public ClientePessoaFisica toPessoaFisica() {
		Cliente cliente = new Cliente(TipoPessoa.PF);
		ClientePessoaFisica pf = new ClientePessoaFisica(this.nome, this.CPF, cliente);
		return pf;
	}

}
