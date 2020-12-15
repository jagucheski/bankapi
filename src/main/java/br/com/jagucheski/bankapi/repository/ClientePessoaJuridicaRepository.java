package br.com.jagucheski.bankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.ClientePessoaJuridica;

public interface ClientePessoaJuridicaRepository extends JpaRepository<ClientePessoaJuridica, Long> {

	Optional<ClientePessoaJuridica> findByCNPJ(String cnpj);

}
