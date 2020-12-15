package br.com.jagucheski.bankapi.form;

import javax.validation.constraints.NotNull;

import br.com.jagucheski.bankapi.model.Agencia;
import br.com.jagucheski.bankapi.repository.AgenciaRepository;

public class AgenciaForm {

	@NotNull(message = "número é obrigatório")
	private int numero;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Agencia toAgencia() {
		return new Agencia(this.numero);
	}

	public Agencia atualizaAgencia(Long id, AgenciaRepository agenciaRepository) {
		Agencia agencia = agenciaRepository.getOne(id);
		agencia.setNumero(this.numero);
		return agencia;
	}
}
