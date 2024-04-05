package br.com.vainaweb.escolat2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.vainaweb.escolat2.dto.DadosAluno;
import br.com.vainaweb.escolat2.model.AlunoModel;
import br.com.vainaweb.escolat2.repository.AlunoRepository;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repository;

	public List<AlunoModel> encontrarTodosAlunos() {
		return repository.findAll();
	}

	public String cadastrar(DadosAluno dados) {
		var existente = repository.findByCpf(dados.cpf());
		if (existente.isPresent()) {
			return "CPF ja cadastrado";
		} else {
			var aluno = new AlunoModel(dados.nome(), dados.email(), dados.cpf(), dados.curso(), dados.telefone(),
					dados.endereco());
			repository.save(aluno);
			return "Cadastro feito com sucesso";
		}

	}
}
