package com.softplan.devteste.exception;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {

	E000("000", "CPF Inválido"), //
	E001("001", "E-mail Inválido"), //
	E002("002", "Data Nascimento Inválida"), //
	E003("003", "Requisição nula"), //
	E004("004", "A requisição contém erro(s)"), //
	E005("005", "Falha ao atualizar, Pessoa Não Localizada"), //
	E006("006", "Falha ao Cadastrar, Pessoa já existe"), //
	E007("007", "Falha ao consultar, Não existem Pessoas cadastradas"), //
	E008("008", "Falha ao consultar, Pessoa Não Localizada"), //
	E009("009", "Falha ao deletar, Pessoa Não Localizada");

	private final String code;
	private final String message;

	ErrorMessageEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
