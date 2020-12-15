package br.com.jagucheski.bankapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.jagucheski.bankapi.repository.ContaCorrenteRepository;

@Entity
public class Agencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int numero;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "agencia", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ContaCorrente> contasCorrente = new ArrayList<ContaCorrente>();

	public Agencia() {
	}

	public Agencia(int numero) {
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<ContaCorrente> getContasCorrente() {
		return contasCorrente;
	}

	public void setContasCorrente(List<ContaCorrente> contasCorrente) {
		this.contasCorrente = contasCorrente;
	}
	
	public boolean possuiContaCorrente(ContaCorrenteRepository contaCorrenteRepository) {
		return contaCorrenteRepository.countByAgenciaNumero(this.getNumero()) > 0 ? true : false;
	}

}
