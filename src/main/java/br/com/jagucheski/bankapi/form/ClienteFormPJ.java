package br.com.jagucheski.bankapi.form;

import br.com.jagucheski.bankapi.model.Cliente;
import br.com.jagucheski.bankapi.model.PessoaJuridica;
import br.com.jagucheski.bankapi.model.TipoPessoa;

public class ClienteFormPJ {

	private String razaoSocial;
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

	public PessoaJuridica toPessoaJuridica() {
		Cliente cliente = new Cliente(TipoPessoa.PJ);
		PessoaJuridica pj = new PessoaJuridica(this.razaoSocial, this.CNPJ, cliente);
		return pj;
	}

}
