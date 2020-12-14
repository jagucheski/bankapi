package br.com.jagucheski.bankapi.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jagucheski.bankapi.dto.ClienteDtoPF;
import br.com.jagucheski.bankapi.dto.ClienteDtoPJ;
import br.com.jagucheski.bankapi.form.ClienteFormPF;
import br.com.jagucheski.bankapi.form.ClienteFormPJ;
import br.com.jagucheski.bankapi.model.Cliente;
import br.com.jagucheski.bankapi.model.PessoaFisica;
import br.com.jagucheski.bankapi.model.PessoaJuridica;
import br.com.jagucheski.bankapi.repository.PessoaFisicaRepository;
import br.com.jagucheski.bankapi.repository.PessoaJuridicaRepository;
import br.com.jagucheski.bankapi.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@GetMapping
	public List<Cliente> getCliente() {
		List<Cliente> ps = clienteRepository.findAll(); 
		return ps;
	}

	@GetMapping("pf")
	public List<PessoaFisica> getClientePF() {
		List<PessoaFisica> pfs = pessoaFisicaRepository.findAll(); 
		return pfs;
	}

	@GetMapping("pj")
	public List<PessoaJuridica> getClientePJ() {
		List<PessoaJuridica> pjs = pessoaJuridicaRepository.findAll(); 
		return pjs;
	}
		
	@PostMapping("cadPF")
	@Transactional
	public ResponseEntity<ClienteDtoPF> cadastrarClientePF(@RequestBody ClienteFormPF clienteFormPF, UriComponentsBuilder uriBuilder) {
		PessoaFisica clientePF = clienteFormPF.toPessoaFisica();
		pessoaFisicaRepository.save(clientePF);
		
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clientePF.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDtoPF(clientePF));
	}
	
	@PostMapping("cadPJ")
	@Transactional
	public ResponseEntity<ClienteDtoPJ> cadastrarClientePJ(@RequestBody ClienteFormPJ clienteFormPJ, UriComponentsBuilder uriBuilder) {
		PessoaJuridica clientePJ = clienteFormPJ.toPessoaJuridica();
		pessoaJuridicaRepository.save(clientePJ);
		
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clientePJ.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDtoPJ(clientePJ));		
	}

	
	//	@PostMapping
//	@Transactional
//	@CacheEvict(value = "listaDeTopicos", allEntries = true)
//	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
//		Topico topico = form.converter(cursoRepository);
//		topicoRepository.save(topico);
//		
//		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
//		return ResponseEntity.created(uri).body(new TopicoDto(topico));
//	}

}
