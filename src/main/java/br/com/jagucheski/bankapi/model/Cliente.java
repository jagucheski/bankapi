package br.com.jagucheski.bankapi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;

	@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
	@JsonManagedReference
	private ClientePessoaFisica clientePessoaFisica;

	@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
	@JsonManagedReference
	private ClientePessoaJuridica clientePessoaJuridica;

	public Cliente() {
	}

	public Cliente(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public ClientePessoaFisica getClientePessoaFisica() {
		return clientePessoaFisica;
	}

	public void setClientePessoaFisica(ClientePessoaFisica clientePessoaFisica) {
		this.clientePessoaFisica = clientePessoaFisica;
	}

	public ClientePessoaJuridica getClientePessoaJuridica() {
		return clientePessoaJuridica;
	}

	public void setClientePessoaJuridica(ClientePessoaJuridica clientePessoaJuridica) {
		this.clientePessoaJuridica = clientePessoaJuridica;
	}

}
