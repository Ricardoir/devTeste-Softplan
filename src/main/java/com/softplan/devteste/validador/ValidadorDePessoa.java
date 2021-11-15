package com.softplan.devteste.validador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.softplan.devteste.entidade.Pessoa;
import com.softplan.devteste.exception.ErrorField;
import com.softplan.devteste.exception.ErrorMessageEnum;
import com.softplan.devteste.exception.NegocioException;

public class ValidadorDePessoa {

	private ValidadorDePessoa() {
		throw new IllegalStateException("Utility class");
	}

	public static void valida(Pessoa pessoa) {
		if (pessoa == null) {
			throw new NegocioException(ErrorMessageEnum.E003.getCode(), ErrorMessageEnum.E003.getMessage());
		}
		List<ErrorField> errors = new ArrayList<>();
		validateCategoryFields(pessoa, errors);

		if (!errors.isEmpty()) {
			throw new NegocioException(ErrorMessageEnum.E004.getCode(), ErrorMessageEnum.E004.getMessage(), errors);
		}
	}

	private static void validateCategoryFields(Pessoa pessoa, List<ErrorField> errors) {

		if (!ValidadorDeCPF.cpfValido(pessoa.getCpf())) {
			errors.add(new ErrorField(ErrorMessageEnum.E000.getCode(), ErrorMessageEnum.E000.getMessage(), null));
		}

		if (LocalDate.now().compareTo(pessoa.getDataNascimento()) <= 0) {
			errors.add(new ErrorField(ErrorMessageEnum.E002.getCode(), ErrorMessageEnum.E002.getMessage(), null));
		}
	}

}
