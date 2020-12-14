package br.com.jagucheski.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
