package br.com.jagucheski.bankapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jagucheski.bankapi.model.Agencia;
import br.com.jagucheski.bankapi.model.ContaCorrente;
import br.com.jagucheski.bankapi.repository.AgenciaRepository;
import br.com.jagucheski.bankapi.repository.ContaCorrenteRepository;

@RestController
public class ContaCorrenteController {

	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	AgenciaRepository agenciaRepository;
	
	@GetMapping("contaCorrente/contas")	
	@ResponseBody
	public List<ContaCorrente> getContaCorrentes(Model model){
		return contaCorrenteRepository.findAll();		
	}
	
	@GetMapping("agencias")	
	@ResponseBody
	public List<Agencia> getAgencias(Model model){
		return agenciaRepository.findAll();		
	}
	
}
