package br.com.jagucheski.bankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.ClientePessoaFisica;

public interface ClientePessoaFisicaRepository extends JpaRepository<ClientePessoaFisica, Long> {

	Optional<ClientePessoaFisica> findByCPF(String cpf);

}
