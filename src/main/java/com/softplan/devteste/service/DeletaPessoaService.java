package com.softplan.devteste.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.devteste.entidade.Pessoa;
import com.softplan.devteste.exception.ErrorMessageEnum;
import com.softplan.devteste.exception.NegocioException;
import com.softplan.devteste.repositorio.PessoaRepositorio;

@Service
public class DeletaPessoaService {

	@Autowired
	PessoaRepositorio pessoaRepositorio;

	public void deletaPessoaPorCpf(String cpf) {

		Optional<Pessoa> consultaPorCpf = pessoaRepositorio.findById(cpf);

		if (consultaPorCpf.isPresent()) {

			pessoaRepositorio.deleteById(cpf);

		} else {

			throw new NegocioException(ErrorMessageEnum.E009.getCode(), ErrorMessageEnum.E009.getMessage());
		}

	}

}
