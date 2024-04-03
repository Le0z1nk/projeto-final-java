package br.com.vainaweb.escolat2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vainaweb.escolat2.dto.DadosAluno;
import br.com.vainaweb.escolat2.model.AlunoModel;
import br.com.vainaweb.escolat2.service.AlunoService;
import br.com.vainaweb.escolat2.repository.AlunoRepository;

@RestController
@RequestMapping("/aluno-teste")
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	@Autowired
	private AlunoRepository repository;
	
	@GetMapping
	public List<AlunoModel> listarTodosAlunos() {
		return service.encontrarTodosAlunos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoModel> listarAlunosPorId(@PathVariable Long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public String cadastrarAluno(@RequestBody DadosAluno dados) {
		service.cadastrar(dados);
		return "ok";
	}
}
