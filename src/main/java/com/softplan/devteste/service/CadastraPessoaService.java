package com.softplan.devteste.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.devteste.dto.CadastraPessoaDTO;
import com.softplan.devteste.entidade.Pessoa;
import com.softplan.devteste.exception.ErrorMessageEnum;
import com.softplan.devteste.exception.NegocioException;
import com.softplan.devteste.repositorio.PessoaRepositorio;
import com.softplan.devteste.validador.ValidadorDeCPF;
import com.softplan.devteste.validador.ValidadorDePessoa;

@Service
public class CadastraPessoaService {

	@Autowired
	PessoaRepositorio pessoaRepositorio;

	public Pessoa cadastraPessoaPorCpf(CadastraPessoaDTO pessoaDTO) {

		String cpfFormatado = pessoaDTO.getCpf();

		ValidadorDeCPF.validaCpf(cpfFormatado);

		pessoaRepositorio.findById(cpfFormatado).ifPresent(p -> {
			throw new NegocioException(ErrorMessageEnum.E006.getCode(), ErrorMessageEnum.E006.getMessage());
		});

		var pessoa = new Pessoa();

		pessoa.setCpf(cpfFormatado);
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setSexo(pessoaDTO.getSexo());
		pessoa.setEmail(pessoaDTO.getEmail());
		pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
		pessoa.setNaturalidade(pessoaDTO.getNaturalidade());
		pessoa.setNacionalidade(pessoaDTO.getNacionalidade());
		pessoa.setDataCadastro(LocalDateTime.now());

		ValidadorDePessoa.valida(pessoa);

		return pessoaRepositorio.save(pessoa);

	}

}
