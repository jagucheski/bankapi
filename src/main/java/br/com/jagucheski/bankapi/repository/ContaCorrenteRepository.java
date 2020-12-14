package br.com.jagucheski.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

}
