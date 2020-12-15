package br.com.jagucheski.bankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

	Optional<Agencia> findByNumero(int numero);

}
