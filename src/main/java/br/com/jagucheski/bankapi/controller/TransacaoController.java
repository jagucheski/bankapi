package br.com.jagucheski.bankapi.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jagucheski.bankapi.dto.ExtratoDto;
import br.com.jagucheski.bankapi.dto.TransacaoDto;
import br.com.jagucheski.bankapi.form.TransacaoForm;
import br.com.jagucheski.bankapi.model.ContaCorrente;
import br.com.jagucheski.bankapi.model.TipoTransacao;
import br.com.jagucheski.bankapi.model.Transacao;
import br.com.jagucheski.bankapi.repository.ContaCorrenteRepository;
import br.com.jagucheski.bankapi.repository.TransacaoRepository;

@RestController
@RequestMapping("transacao")
public class TransacaoController {
	
	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	/**
	 * 201: retorna no corpo da resposta a transacao salva
	 * 409: caso não encontre a conta corrente
	 * */
	@PostMapping("depositar")
	@Transactional
	public ResponseEntity<TransacaoDto> depositar(@RequestBody @Valid TransacaoForm transacaoForm, UriComponentsBuilder uriBuilder) {
		Optional<ContaCorrente> optional = contaCorrenteRepository.findById(transacaoForm.getIdContaCorrente());
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		ContaCorrente contaCorrenteDB = optional.get();
		contaCorrenteDB.depositar(transacaoForm.getValor());
		
		Transacao transacao = transacaoForm.toTransacaoDeposito(contaCorrenteDB);
		transacaoRepository.save(transacao);
		
		URI uri = uriBuilder.path("/transacao/{id}").buildAndExpand(transacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new TransacaoDto(transacao));	
	}
	
	/**
	 * 201: retorna no corpo da resposta a transacao salva
	 * 409: caso não encontre a conta corrente
	 * */
	@PostMapping("sacar")
	@Transactional
	public ResponseEntity<TransacaoDto> sacar(@RequestBody @Valid TransacaoForm transacaoForm, UriComponentsBuilder uriBuilder) {
		Optional<ContaCorrente> optional = contaCorrenteRepository.findById(transacaoForm.getIdContaCorrente());
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		ContaCorrente contaCorrenteDB = optional.get();
		contaCorrenteDB.sacar(transacaoForm.getValor());
		
		Transacao transacao = transacaoForm.toTransacaoSaque(contaCorrenteDB);
		transacaoRepository.save(transacao);
		
		URI uri = uriBuilder.path("/transacao/{id}").buildAndExpand(transacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new TransacaoDto(transacao));	
	}

	@PostMapping("saldoContaCorrente")
	@Transactional
	public ResponseEntity<String> saldoContaCorrente(@RequestBody Long idContaCorrente) {
		Optional<ContaCorrente> contaCorrenteDB = contaCorrenteRepository.findById(idContaCorrente);
		if (contaCorrenteDB.isPresent()) {
			ContaCorrente contaCorrente = contaCorrenteDB.get();
			Transacao transacao = new Transacao(LocalDateTime.now(), TipoTransacao.SALDO, contaCorrente, contaCorrente.getSaldoAtual());
			transacaoRepository.save(transacao);
			return ResponseEntity.ok(contaCorrente.getSaldoAtual().toString());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("extrato/{idContaCorrente}")
	@Transactional
	public ResponseEntity<ExtratoDto> extrato(@PathVariable Long idContaCorrente) {
		Optional<ContaCorrente> contaCorrenteOptional = contaCorrenteRepository.findById(idContaCorrente);
		if (contaCorrenteOptional.isPresent()) {
			ExtratoDto extrato = new ExtratoDto(contaCorrenteOptional.get(), transacaoRepository.findByContaCorrenteId(idContaCorrente));
			return ResponseEntity.ok(extrato);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Transacao> optional = transacaoRepository.findById(id);
		if (optional.isPresent()) {
			transacaoRepository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
