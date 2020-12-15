package br.com.jagucheski.bankapi.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jagucheski.bankapi.model.Cliente;
import br.com.jagucheski.bankapi.model.ClientePessoaJuridica;
import br.com.jagucheski.bankapi.model.TipoPessoa;

public class ClienteFormPJ {

	@NotBlank(message = "razaoSocial é obrigatório")
	@Size(min = 5, max = 250)
	private String razaoSocial;

	@NotBlank(message = "CNPJ é obrigatório")
	@Size(min = 14, max = 20)
	private String CNPJ;
	
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

	public ClientePessoaJuridica toPessoaJuridica() {
		Cliente cliente = new Cliente(TipoPessoa.PJ);
		ClientePessoaJuridica pj = new ClientePessoaJuridica(this.razaoSocial, this.CNPJ, cliente);
		return pj;
	}

}
