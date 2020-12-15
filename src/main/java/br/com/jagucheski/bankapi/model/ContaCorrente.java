package br.com.jagucheski.bankapi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "conta_corrente")
public class ContaCorrente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_conta")
	private String numeroConta;

	@Column(name = "saldo_atual")
	private BigDecimal saldoAtual;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Agencia agencia;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contaCorrente", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transacao> transacoes = new ArrayList<Transacao>();

	public ContaCorrente() {
	}

	public ContaCorrente(String numeroConta, BigDecimal saldoAtual, Agencia agencia, Cliente cliente) {
		this.numeroConta = numeroConta;
		this.saldoAtual = saldoAtual;
		this.agencia = agencia;
		this.cliente = cliente;
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

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public void depositar(BigDecimal deposito) {
		this.saldoAtual =  this.saldoAtual.add(deposito);
	}
	
	public void sacar(BigDecimal saque) {
		this.saldoAtual =  this.saldoAtual.subtract(saque);
	}

}
