package com.softplan.devteste.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.devteste.dto.PessoaDTO;
import com.softplan.devteste.entidade.Pessoa;
import com.softplan.devteste.exception.ErrorMessageEnum;
import com.softplan.devteste.exception.NegocioException;
import com.softplan.devteste.repositorio.PessoaRepositorio;

@Service
public class ConsultaPessoaService {

	@Autowired
	PessoaRepositorio pessoaRepositorio;

	public List<PessoaDTO> consultaTodasAsPessoas() {

		List<Pessoa> findAll = pessoaRepositorio.findAll();

		if (findAll.isEmpty()) {
			throw new NegocioException(ErrorMessageEnum.E007.getCode(), ErrorMessageEnum.E007.getMessage());
		} else {

			return findAll.stream().map(PessoaDTO::new).collect(Collectors.toList());
		}

	}

	public PessoaDTO consultaPessoaPorCpf(String cpf) {
		Optional<Pessoa> consultaPorCpf = pessoaRepositorio.findById(cpf);

		if (consultaPorCpf.isPresent()) {

			return new PessoaDTO(consultaPorCpf.get());

		} else {
			throw new NegocioException(ErrorMessageEnum.E008.getCode(), ErrorMessageEnum.E008.getMessage());
		}

	}

}
