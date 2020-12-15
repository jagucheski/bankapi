package br.com.jagucheski.bankapi.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jagucheski.bankapi.model.ClientePessoaFisica;
import br.com.jagucheski.bankapi.repository.ClientePessoaFisicaRepository;

public class ClientePutFormPF {

	@NotBlank(message = "nome é obrigatório")
	@Size(min = 5, max = 250)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ClientePessoaFisica atualizaClientePessoaFisica(Long id, ClientePessoaFisicaRepository clientePFRepository) {
		ClientePessoaFisica clientePF = clientePFRepository.getOne(id);
		clientePF.setNome(this.nome);
		return clientePF;
	}

}
