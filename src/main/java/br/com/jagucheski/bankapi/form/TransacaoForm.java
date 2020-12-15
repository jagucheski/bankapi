package br.com.jagucheski.bankapi.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.jagucheski.bankapi.model.ContaCorrente;
import br.com.jagucheski.bankapi.model.TipoTransacao;
import br.com.jagucheski.bankapi.model.Transacao;

public class TransacaoForm {

	@NotNull(message = "valor é obrigatório")
	private BigDecimal valor;

	@NotNull(message = "idContaCorente é obrigatório")
	private Long idContaCorrente;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getIdContaCorrente() {
		return idContaCorrente;
	}

	public void setIdContaCorrente(Long idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}

	public Transacao toTransacaoDeposito(ContaCorrente contaCorrente) {
		return new Transacao(LocalDateTime.now(), TipoTransacao.DEPOSITO, contaCorrente, this.valor);
		}

	public Transacao toTransacaoSaque(ContaCorrente contaCorrente) {
		return new Transacao(LocalDateTime.now(), TipoTransacao.SAQUE, contaCorrente, this.valor);
	}

}
