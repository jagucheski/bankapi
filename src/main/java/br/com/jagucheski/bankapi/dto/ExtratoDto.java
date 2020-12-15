package br.com.jagucheski.bankapi.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.jagucheski.bankapi.model.ContaCorrente;
import br.com.jagucheski.bankapi.model.Transacao;

public class ExtratoDto {

	private String numeroConta;
	private BigDecimal saldo;
	private List<Transacao> transacoes = new ArrayList<Transacao>();

	public ExtratoDto(ContaCorrente contaCorrente, List<Transacao> transacoesContaCorrente) {
		this.numeroConta = contaCorrente.getNumeroConta();
		this.saldo = contaCorrente.getSaldoAtual();
		this.transacoes = transacoesContaCorrente;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
}
