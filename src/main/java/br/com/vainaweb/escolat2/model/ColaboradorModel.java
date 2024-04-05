package br.com.vainaweb.escolat2.model;

import org.hibernate.validator.constraints.br.CPF;

import br.com.vainaweb.escolat2.dto.DadosAtualizados;
import br.com.vainaweb.escolat2.dto.EnderecoDTO;
import br.com.vainaweb.escolat2.enums.Cargo;
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

@Entity // trata a classe como uma entidade
@Table(name = "tb_colaboradores") // define o nome da tabela

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorModel {

	@Id // determina a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Email
	@Column(unique = true) // adicionando constraint UNIQUE
	private String email;

	@CPF
	@Column(unique = true)
	private String cpf;

	private Cargo cargo;

	@Embedded // diz que o atributo é incorporavel
	private Endereco endereco;

	public ColaboradorModel(String nome, String cpf, String email, Cargo cargo, @Valid EnderecoDTO endereco) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.cargo = cargo;
		this.endereco = new Endereco(endereco.cep(), endereco.logradouro(), endereco.bairro(), endereco.cidade(),
				endereco.complemento(), endereco.uf(), endereco.numero());
	}

	public void atualizarInfo(@Valid DadosAtualizados dados) {
		this.nome = dados.nome() != null ? dados.nome() : this.nome;
		this.email = dados.email() != null ? dados.email() : this.email;
	}

}
