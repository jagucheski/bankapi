package br.com.jagucheski.bankapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jagucheski.bankapi.dto.ClienteDtoPF;
import br.com.jagucheski.bankapi.dto.ClienteDtoPJ;
import br.com.jagucheski.bankapi.form.ClienteFormPF;
import br.com.jagucheski.bankapi.form.ClienteFormPJ;
import br.com.jagucheski.bankapi.form.ClientePutFormPF;
import br.com.jagucheski.bankapi.form.ClientePutFormPJ;
import br.com.jagucheski.bankapi.model.Cliente;
import br.com.jagucheski.bankapi.model.ClientePessoaFisica;
import br.com.jagucheski.bankapi.model.ClientePessoaJuridica;
import br.com.jagucheski.bankapi.repository.ClientePessoaFisicaRepository;
import br.com.jagucheski.bankapi.repository.ClientePessoaJuridicaRepository;
import br.com.jagucheski.bankapi.repository.ClienteRepository;
import br.com.jagucheski.bankapi.repository.ContaCorrenteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClientePessoaFisicaRepository clientePFRepository;
	
	@Autowired
	private ClientePessoaJuridicaRepository clientePJRepository;
	
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@GetMapping("find")
	public List<Cliente> findCliente() {
		return clienteRepository.findAll(); 
	}

	@GetMapping("findPF")
	public List<ClientePessoaFisica> findClientePF() {
		return clientePFRepository.findAll(); 
	}

	@GetMapping("findPJ")
	public List<ClientePessoaJuridica> findClientePJ() {
		return clientePJRepository.findAll(); 
	}

	/**
	 * 201: retorna no corpo da resposta o clientePF criado
	 * 409: caso ja tenha cliente com cpf cadastrado
	 * */
	@PostMapping("cadastraPF")
	@Transactional
	public ResponseEntity<ClienteDtoPF> cadastrarClientePF(@RequestBody @Valid ClienteFormPF clienteFormPF, UriComponentsBuilder uriBuilder) {
		Optional<ClientePessoaFisica> optional = clientePFRepository.findByCPF(clienteFormPF.getCPF());
		if (optional.isPresent()) {
			return ResponseEntity.status(409).build();
		}
				
		ClientePessoaFisica clientePF = clienteFormPF.toPessoaFisica();
		clientePFRepository.save(clientePF);
		
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clientePF.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDtoPF(clientePF));
	}
	
	/**
	 * 201: retorna no corpo da resposta o clientePJ criado
	 * 409: caso ja tenha cliente com cnpj cadastrado
	 * */
	@PostMapping("cadastraPJ")
	@Transactional
	public ResponseEntity<ClienteDtoPJ> cadastrarClientePJ(@RequestBody @Valid ClienteFormPJ clienteFormPJ, UriComponentsBuilder uriBuilder) {
		Optional<ClientePessoaJuridica> optional = clientePJRepository.findByCNPJ(clienteFormPJ.getCNPJ());
		if (optional.isPresent()) {
			return ResponseEntity.status(409).build();
		}
		
		ClientePessoaJuridica clientePJ = clienteFormPJ.toPessoaJuridica();
		clientePJRepository.save(clientePJ);
		
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clientePJ.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDtoPJ(clientePJ));		
	}
	
	@PutMapping("atualizaPF/{id}")
	@Transactional
	public ResponseEntity<ClienteDtoPF> atualizarClientePF(@PathVariable Long id, @RequestBody @Valid ClientePutFormPF clientePutFormPF) {
		Optional<ClientePessoaFisica> optional = clientePFRepository.findById(id);
		if (optional.isPresent()) {
			ClientePessoaFisica clientePF = clientePutFormPF.atualizaClientePessoaFisica(id, clientePFRepository);
			return ResponseEntity.ok(new ClienteDtoPF(clientePF));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("atualizaPJ/{id}")
	@Transactional
	public ResponseEntity<ClienteDtoPJ> atualizarClientePJ(@PathVariable Long id, @RequestBody @Valid ClientePutFormPJ clientePutFormPJ) {
		Optional<ClientePessoaJuridica> optional = clientePJRepository.findById(id);
		if (optional.isPresent()) {
			ClientePessoaJuridica clientePJ = clientePutFormPJ.atualizaClientePessoaJuridica(id, clientePJRepository);
			return ResponseEntity.ok(new ClienteDtoPJ(clientePJ));
		}
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * 200: cliente pessoa fisica deletado
	 * 400: caso id cliente nao encontrado
	 * 409: caso cliente possua conta corrente vinculada
	 * */
	@DeleteMapping("deletarPF/{id}")
	@Transactional
	public ResponseEntity<?> deletarPF(@PathVariable Long id) {
		Optional<ClientePessoaFisica> clientePf = clientePFRepository.findById(id);
		if (clientePf.isPresent()) {
			if (!contaCorrenteRepository.findByClienteClientePessoaFisicaId(clientePf.get().getId()).isPresent()) {
				clientePFRepository.delete(clientePf.get());
				return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.status(409).build();
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * 200: cliente pessoa juridica deletado
	 * 400: caso id cliente nao encontrado
	 * 409: caso cliente possua conta corrente vinculada
	 * */
	@DeleteMapping("deletarPJ/{id}")
	@Transactional
	public ResponseEntity<?> deletarPJ(@PathVariable Long id) {
		Optional<ClientePessoaJuridica> clientePJ = clientePJRepository.findById(id);
		if (clientePJ.isPresent()) {
			if (!contaCorrenteRepository.findByClienteClientePessoaJuridicaId(clientePJ.get().getId()).isPresent()) {
				clientePJRepository.delete(clientePJ.get());
				return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.status(409).build();
			}
		}
		return ResponseEntity.notFound().build();
	}

}
