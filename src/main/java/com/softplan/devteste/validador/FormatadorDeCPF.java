package com.softplan.devteste.validador;

import java.math.BigInteger;

import com.softplan.devteste.exception.ErrorMessageEnum;
import com.softplan.devteste.exception.NegocioException;

public class FormatadorDeCPF {

	private FormatadorDeCPF() {
		throw new IllegalStateException("Utility class");
	}

	public static String adicionaZerosEsquerda(String numeroCpf) {
		try {

			return String.format("%011d", new BigInteger(numeroCpf));

		} catch (Exception e) {

			throw new NegocioException(ErrorMessageEnum.E000.getCode(), ErrorMessageEnum.E000.getMessage());

		}
	}

}