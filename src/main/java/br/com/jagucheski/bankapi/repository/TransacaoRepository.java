package br.com.jagucheski.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
