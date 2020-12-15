package br.com.jagucheski.bankapi.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jagucheski.bankapi.model.Agencia;
import br.com.jagucheski.bankapi.model.ClientePessoaFisica;
import br.com.jagucheski.bankapi.model.ContaCorrente;

public class ContaCorrentePFForm {

	@NotBlank(message = "numeroConta é obrigatório")
	private String numeroConta;
	
	@NotNull(message = "numeroAgencia é obrigatório")
	private int numeroAgencia;
	
	@NotBlank(message = "CPF é obrigatório")
	@Size(min = 11, max = 20)
	private String CPF;

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

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public ContaCorrente toContaCorrente(Agencia agencia, ClientePessoaFisica clientePessoaFisica) {		
		ContaCorrente contaCorrente = new ContaCorrente(this.numeroConta, BigDecimal.ZERO, agencia, clientePessoaFisica.getCliente());
		return contaCorrente;
	}

}
