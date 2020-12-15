package br.com.jagucheski.bankapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.jagucheski.bankapi.model.ContaCorrente;
import br.com.jagucheski.bankapi.model.TipoTransacao;
import br.com.jagucheski.bankapi.model.Transacao;

public class TransacaoDto {

	private LocalDateTime data;
	private BigDecimal valor;
	private TipoTransacao tipoTransacao;
	private ContaCorrente contaCorrente;

	public TransacaoDto() {
	}

	public TransacaoDto(Transacao transacao) {
		this.data = transacao.getData();
		this.valor = transacao.getValor();
		this.tipoTransacao = transacao.getTipoTransacao();
		this.contaCorrente = transacao.getContaCorrente();
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

}
