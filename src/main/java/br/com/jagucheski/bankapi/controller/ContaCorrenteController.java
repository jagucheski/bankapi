package br.com.jagucheski.bankapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jagucheski.bankapi.dto.ContaCorrenteDto;
import br.com.jagucheski.bankapi.form.ContaCorrentePFForm;
import br.com.jagucheski.bankapi.form.ContaCorrentePJForm;
import br.com.jagucheski.bankapi.model.Agencia;
import br.com.jagucheski.bankapi.model.ClientePessoaFisica;
import br.com.jagucheski.bankapi.model.ClientePessoaJuridica;
import br.com.jagucheski.bankapi.model.ContaCorrente;
import br.com.jagucheski.bankapi.repository.AgenciaRepository;
import br.com.jagucheski.bankapi.repository.ClientePessoaFisicaRepository;
import br.com.jagucheski.bankapi.repository.ClientePessoaJuridicaRepository;
import br.com.jagucheski.bankapi.repository.ContaCorrenteRepository;

@RestController
@RequestMapping("contaCorrente")
public class ContaCorrenteController {

	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
	
	@Autowired
	ClientePessoaFisicaRepository clientePessoaFisicaRepository;
	
	@Autowired
	ClientePessoaJuridicaRepository clientePessoaJuridicaRepository;
	
	@Autowired
	AgenciaRepository agenciaRepository;
	
	@GetMapping("find")
	@ResponseBody
	public List<ContaCorrente> findContaCorrente(Model model){
		return contaCorrenteRepository.findAll();		
	}
		
	/**
	 * 201: retorna no corpo da resposta a conta corrente criada
	 * 404: caso numero de agencia ou cpf não estejam cadastrados
	 * 409: caso ja tenha conta corrente com agencia e cpf cadastrados 
	 * */
	@PostMapping("cadastraPF")
	@Transactional
	public ResponseEntity<ContaCorrenteDto> cadastrarContaCorrentePF(@RequestBody @Valid ContaCorrentePFForm contaCorrentePFForm, UriComponentsBuilder uriBuilder) {
		
		//Validacao do numero de agencia e CPF
		Optional<ClientePessoaFisica> clientePessoaFisica = clientePessoaFisicaRepository.findByCPF(contaCorrentePFForm.getCPF());
		Optional<Agencia> agencia = agenciaRepository.findByNumero(contaCorrentePFForm.getNumeroAgencia());
		if(!clientePessoaFisica.isPresent() || !agencia.isPresent()){
			return ResponseEntity.notFound().build();
		}
		
		//Valida se existe conta corrente cadastrada com numero de agencia e CPF
		Optional<ContaCorrente> contaCorrenteDB = contaCorrenteRepository.findByAgenciaNumeroAndClienteClientePessoaFisicaCPF(contaCorrentePFForm.getNumeroAgencia(), contaCorrentePFForm.getCPF());
		if (contaCorrenteDB.isPresent()) {
			return ResponseEntity.status(409).build();
		}
		
		ContaCorrente contaCorrente = contaCorrentePFForm.toContaCorrente(agencia.get(), clientePessoaFisica.get());
		contaCorrenteRepository.save(contaCorrente);
		
		URI uri = uriBuilder.path("/contaCorrente/{id}").buildAndExpand(contaCorrente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaCorrenteDto(contaCorrente));	
	}
	
	/**
	 * 201: retorna no corpo da resposta a conta corrente criada
	 * 404: caso numero de agencia ou cnpj não estejam cadastrados
	 * 409: caso ja tenha conta corrente com agencia e cnpj cadastrados 
	 * */
	@PostMapping("cadastraPJ")
	@Transactional
	public ResponseEntity<ContaCorrenteDto> cadastrarContaCorrentePJ(@RequestBody @Valid ContaCorrentePJForm contaCorrentePJForm, UriComponentsBuilder uriBuilder) {
		
		//Validacao do numero de agencia e CNPJ
		Optional<ClientePessoaJuridica> clientePessoaJuridica = clientePessoaJuridicaRepository.findByCNPJ(contaCorrentePJForm.getCNPJ());
		Optional<Agencia> agencia = agenciaRepository.findByNumero(contaCorrentePJForm.getNumeroAgencia());
		if(!clientePessoaJuridica.isPresent() || !agencia.isPresent()){
			return ResponseEntity.notFound().build();
		}
		
		//Valida se existe conta corrente cadastrada com numero de agencia e CNPJ
		Optional<ContaCorrente> contaCorrenteDB = contaCorrenteRepository.findByAgenciaNumeroAndClienteClientePessoaJuridicaCNPJ(contaCorrentePJForm.getNumeroAgencia(), contaCorrentePJForm.getCNPJ());
		if (contaCorrenteDB.isPresent()) {
			return ResponseEntity.status(409).build();
		}
		
		ContaCorrente contaCorrente = contaCorrentePJForm.toContaCorrente(agencia.get(), clientePessoaJuridica.get());
		contaCorrenteRepository.save(contaCorrente);
		
		URI uri = uriBuilder.path("/contaCorrente/{id}").buildAndExpand(contaCorrente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaCorrenteDto(contaCorrente));	
	}
	
}
