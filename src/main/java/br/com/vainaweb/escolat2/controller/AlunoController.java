package br.com.vainaweb.escolat2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vainaweb.escolat2.dto.DadosAluno;
import br.com.vainaweb.escolat2.dto.DadosAtualizados;
import br.com.vainaweb.escolat2.dto.DadosColaborador;
import br.com.vainaweb.escolat2.model.AlunoModel;
import br.com.vainaweb.escolat2.service.AlunoService;
import jakarta.validation.Valid;
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
	
	// http://localhost:8080/aluno-teste/1
	@PostMapping
	public ResponseEntity<String> cadastrarAluno(@RequestBody @Valid DadosAluno dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dados));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizados dados) {
		var aluno = repository.getReferenceById(id);
		aluno.atualizarInfo(dados);
		repository.save(aluno);
		return ResponseEntity.status(HttpStatus.OK).body("Dados Atualizados");
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return "Deletado com sucesso";
	}
}
