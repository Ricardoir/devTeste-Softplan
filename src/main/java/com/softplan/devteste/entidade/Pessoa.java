package com.softplan.devteste.entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.softplan.devteste.dto.PessoaDTO;
import com.softplan.devteste.enun.Sexo;

import lombok.Data;

@Data
@Entity
public class Pessoa {

	private String nome;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Email
	private String email;

	@NotNull(message = "O campo data de Nascimento é obrigatório")
	private LocalDate dataNascimento;
	private String naturalidade;
	private String nacionalidade;

	@NotNull(message = "O campo CPF é obrigatório")
	@Id
	private String cpf;

	private LocalDateTime dataCadastro = LocalDateTime.now();

	private LocalDateTime dataAtualizacao;

	public Pessoa(PessoaDTO pessoaDTO) {
		this.cpf = pessoaDTO.getCpf();
		this.dataNascimento = pessoaDTO.getDataNascimento();
		this.email = pessoaDTO.getEmail();
		this.nacionalidade = pessoaDTO.getNacionalidade();
		this.naturalidade = pessoaDTO.getNaturalidade();
		this.nome = pessoaDTO.getNome();
		this.sexo = pessoaDTO.getSexo();

	}

	public Pessoa(PessoaDTO pessoaDTO, LocalDateTime dataAtual, LocalDateTime dataCadastroBD) {
		this.cpf = pessoaDTO.getCpf();
		this.dataNascimento = pessoaDTO.getDataNascimento();
		this.email = pessoaDTO.getEmail();
		this.nacionalidade = pessoaDTO.getNacionalidade();
		this.naturalidade = pessoaDTO.getNaturalidade();
		this.nome = pessoaDTO.getNome();
		this.sexo = pessoaDTO.getSexo();
		this.dataAtualizacao = dataAtual;
		this.dataCadastro = dataCadastroBD;

	}

	public Pessoa() {
	}

}
