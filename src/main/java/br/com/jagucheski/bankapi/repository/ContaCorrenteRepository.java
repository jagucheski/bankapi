package br.com.jagucheski.bankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jagucheski.bankapi.dto.ContaCorrenteDto;
import br.com.jagucheski.bankapi.model.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

	Optional<ContaCorrente> findByAgenciaNumeroAndClienteClientePessoaFisicaCPF(int numeroConta, String CPF);

	Optional<ContaCorrente> findByAgenciaNumeroAndClienteClientePessoaJuridicaCNPJ(int numeroAgencia, String cnpj);

	Optional<ContaCorrenteDto> findByNumeroConta(String numero);

}
