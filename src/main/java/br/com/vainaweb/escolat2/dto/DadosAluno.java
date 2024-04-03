package br.com.vainaweb.escolat2.dto;

import br.com.vainaweb.escolat2.enums.Curso;

public record DadosAluno(String nome, String email, String cpf, Curso curso, String telefone) {
	
}
