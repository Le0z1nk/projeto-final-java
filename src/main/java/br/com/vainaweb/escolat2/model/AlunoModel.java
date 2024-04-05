package br.com.vainaweb.escolat2.model;

import org.hibernate.validator.constraints.br.CPF;

import br.com.vainaweb.escolat2.dto.DadosAtualizados;
import br.com.vainaweb.escolat2.dto.EnderecoDTO;
import br.com.vainaweb.escolat2.enums.Curso;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_alunos")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Email
	@Column(unique = true)
	private String email;

	@CPF
	@Column(unique = true)
	private String cpf;

	private Curso curso;
	private String telefone;

	@Embedded
	private Endereco endereco;

	public AlunoModel(String nome, String email, String cpf, Curso curso, String telefone,
			@Valid EnderecoDTO endereco) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.curso = curso;
		this.telefone = telefone;
		this.endereco = new Endereco(endereco.cep(), endereco.logradouro(), endereco.bairro(), endereco.cidade(),
				endereco.complemento(), endereco.uf(), endereco.numero());

	}

	public void atualizarInfo(@Valid DadosAtualizados dados) {
		this.nome = dados.nome() != null ? dados.nome() : this.nome;
		this.email = dados.email() != null ? dados.nome() : this.email;
	}
}
