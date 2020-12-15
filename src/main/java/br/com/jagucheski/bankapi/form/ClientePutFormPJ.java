package br.com.jagucheski.bankapi.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jagucheski.bankapi.model.ClientePessoaJuridica;
import br.com.jagucheski.bankapi.repository.ClientePessoaJuridicaRepository;

public class ClientePutFormPJ {

	@NotBlank(message = "razaoSocial é obrigatório")
	@Size(min = 5, max = 250)
	private String razaoSocial;

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public ClientePessoaJuridica atualizaClientePessoaJuridica(Long id, ClientePessoaJuridicaRepository clientePJRepository) {
		ClientePessoaJuridica clientePJ = clientePJRepository.getOne(id);
		clientePJ.setRazaoSocial(this.razaoSocial);
		return clientePJ;
	}

}
