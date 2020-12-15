package br.com.jagucheski.bankapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findByContaCorrenteId(Long idContaCorrente);

}
