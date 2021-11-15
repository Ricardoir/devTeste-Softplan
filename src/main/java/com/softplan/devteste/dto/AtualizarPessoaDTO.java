package com.softplan.devteste.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.softplan.devteste.enun.Sexo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AtualizarPessoaDTO {

	@NotNull(message = "O campo nome é obrigatório")
	private String nome;
	private Sexo sexo;

	@Email(message = "E-mail inválido")
	private String email;

	@NotNull(message = "O campo data de Nascimento é obrigatório")
	private LocalDate dataNascimento;
	private String naturalidade;
	private String nacionalidade;

}
