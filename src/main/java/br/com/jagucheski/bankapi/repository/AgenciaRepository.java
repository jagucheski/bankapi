package br.com.jagucheski.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

}
