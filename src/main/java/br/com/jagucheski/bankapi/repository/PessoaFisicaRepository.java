package br.com.jagucheski.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
