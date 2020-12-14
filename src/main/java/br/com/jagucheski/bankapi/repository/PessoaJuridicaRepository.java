package br.com.jagucheski.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

}
