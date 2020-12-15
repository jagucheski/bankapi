package br.com.jagucheski.bankapi.dto;

import br.com.jagucheski.bankapi.model.Agencia;

public class AgenciaDto {

	private int numero;

	public AgenciaDto() {
	}

	public AgenciaDto(Agencia agencia) {
		this.numero = agencia.getNumero();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
