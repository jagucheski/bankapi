package br.com.jagucheski.bankapi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jagucheski.bankapi.model.ContaCorrente;
import br.com.jagucheski.bankapi.repository.ContaCorrenteRepository;

@SpringBootTest
class BankapiApplicationTests {

	
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@Test
	void carregaContaCorrenteByNumeroAgenciaAndCPF() {
		
		String cpf = "01143951011";
		int numeroAgencia = 3693;

		String cpf2 = "02239151022";
		int numeroAgencia2 = 3693;

		Optional<ContaCorrente> conta = contaCorrenteRepository.findByAgenciaNumeroAndClienteClientePessoaFisicaCPF(numeroAgencia,cpf);
		Optional<ContaCorrente> conta2 = contaCorrenteRepository.findByAgenciaNumeroAndClienteClientePessoaFisicaCPF(numeroAgencia2,cpf2);
		
		assertTrue(conta.isPresent());
		assertTrue(conta2.isPresent());
	}
	

}
