package br.com.jagucheski.bankapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jagucheski.bankapi.dto.AgenciaDto;
import br.com.jagucheski.bankapi.form.AgenciaForm;
import br.com.jagucheski.bankapi.model.Agencia;
import br.com.jagucheski.bankapi.repository.AgenciaRepository;
import br.com.jagucheski.bankapi.repository.ContaCorrenteRepository;

@RestController
@RequestMapping("agencia")
public class AgenciaController {

	@Autowired
	AgenciaRepository agenciaRepository;
	
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
		
	@GetMapping("find")
	@ResponseBody
	public List<Agencia> findAgencia(Model model){
		return agenciaRepository.findAll();		
	}
	
	/**
	 * 201: retorna no corpo da resposta a agencia criada
	 * 409: caso ja tenha agencia com numero cadastrado
	 * */
	@PostMapping("cadastra")
	@Transactional
	public ResponseEntity<AgenciaDto> cadastrarAgencia(@RequestBody @Valid AgenciaForm agenciaForm, UriComponentsBuilder uriBuilder) {
		Optional<Agencia> optional = agenciaRepository.findByNumero(agenciaForm.getNumero());
		if (optional.isPresent()) {
			return ResponseEntity.status(409).build();
		}
		
		Agencia agencia = agenciaForm.toAgencia();
		agenciaRepository.save(agencia);
		
		URI uri = uriBuilder.path("/agencia/{id}").buildAndExpand(agencia.getId()).toUri();
		return ResponseEntity.created(uri).body(new AgenciaDto(agencia));		
	}
	
	@PutMapping("atualiza/{id}")
	@Transactional
	public ResponseEntity<AgenciaDto> atualizarAgencia(@PathVariable Long id, @RequestBody @Valid AgenciaForm agenciaForm) {
		Optional<Agencia> optional = agenciaRepository.findById(id);
		if (optional.isPresent()) {
			Agencia agencia = agenciaForm.atualizaAgencia(id, agenciaRepository);
			return ResponseEntity.ok(new AgenciaDto(agencia));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("detalha/{numero}")
	@Transactional
	public ResponseEntity<Agencia> detalharAgencia(@PathVariable int numero) {
		Optional<Agencia> optional = agenciaRepository.findByNumero(numero);
		if (optional.isPresent()) {
			return ResponseEntity.ok(agenciaRepository.findByNumero(numero).get());
		}
		return ResponseEntity.notFound().build();
	}	
	
	/**
	 * 200: agencia deletada
	 * 409: caso agencia possui conta corrente vinculada
	 * 400: caso id agencia nao encontrado
	 * */
	@DeleteMapping("deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Agencia> agencia = agenciaRepository.findById(id);
		if (agencia.isPresent()) {
			if (!agencia.get().possuiContaCorrente(contaCorrenteRepository)) {
				agenciaRepository.delete(agencia.get());
				return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.status(409).build();
			}
		}
		return ResponseEntity.notFound().build();
	}
}
