package br.com.jagucheski.bankapi.dto;

import java.math.BigDecimal;

import br.com.jagucheski.bankapi.model.Agencia;
import br.com.jagucheski.bankapi.model.ContaCorrente;

public class ContaCorrenteDto {

	private Long id;
	private String numeroConta;
	private BigDecimal saldoAtual;
	private Agencia agencia;
	
	public ContaCorrenteDto() {
	}

	public ContaCorrenteDto(ContaCorrente contaCorrente) {
		this.id = contaCorrente.getId();
		this.numeroConta = contaCorrente.getNumeroConta();
		this.saldoAtual = contaCorrente.getSaldoAtual();
		this.agencia = contaCorrente.getAgencia();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
}
