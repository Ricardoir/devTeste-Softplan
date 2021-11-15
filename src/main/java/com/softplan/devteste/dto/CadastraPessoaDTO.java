package com.softplan.devteste.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.softplan.devteste.enun.Sexo;
import com.softplan.devteste.validador.FormatadorDeCPF;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
public class CadastraPessoaDTO {

	@NotNull(message = "O campo nome é obrigatório")
	private String nome;
	private Sexo sexo;

	@Email(message = "E-mail inválido")
	private String email;

	@NotNull(message = "O campo data de Nascimento é obrigatório")
	private LocalDate dataNascimento;
	private String naturalidade;
	private String nacionalidade;

	@NotNull(message = "O campo CPF é obrigatório")
	@Getter(value = AccessLevel.NONE)
	private String cpf;

	public String getCpf() {
		return FormatadorDeCPF.adicionaZerosEsquerda(cpf);
	}

}
