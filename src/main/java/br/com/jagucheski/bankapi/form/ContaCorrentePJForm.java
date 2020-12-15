package br.com.jagucheski.bankapi.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jagucheski.bankapi.model.Agencia;
import br.com.jagucheski.bankapi.model.ClientePessoaJuridica;
import br.com.jagucheski.bankapi.model.ContaCorrente;

public class ContaCorrentePJForm {

	@NotBlank(message = "numeroConta é obrigatório")
	private String numeroConta;
	
	@NotNull(message = "numeroAgencia é obrigatório")
	private int numeroAgencia;
	
	@NotBlank(message = "CNPJ é obrigatório")
	@Size(min = 14, max = 20)
	private String CNPJ;

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public ContaCorrente toContaCorrente(Agencia agencia, ClientePessoaJuridica clientePessoaJuridica) {
		ContaCorrente contaCorrente = new ContaCorrente(this.numeroConta, BigDecimal.ZERO, agencia,
				clientePessoaJuridica.getCliente());
		return contaCorrente;
	}

}
