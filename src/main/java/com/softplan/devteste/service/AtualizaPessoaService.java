package com.softplan.devteste.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.devteste.dto.AtualizarPessoaDTO;
import com.softplan.devteste.entidade.Pessoa;
import com.softplan.devteste.exception.ErrorMessageEnum;
import com.softplan.devteste.exception.NegocioException;
import com.softplan.devteste.repositorio.PessoaRepositorio;
import com.softplan.devteste.validador.FormatadorDeCPF;
import com.softplan.devteste.validador.ValidadorDeCPF;
import com.softplan.devteste.validador.ValidadorDePessoa;

@Service
public class AtualizaPessoaService {

	@Autowired
	PessoaRepositorio pessoaRepositorio;

	public Pessoa atualizaPessoaPorCpf(String cpf, AtualizarPessoaDTO pessoaDTO) {

		String cpfFormatado = FormatadorDeCPF.adicionaZerosEsquerda(cpf);

		ValidadorDeCPF.validaCpf(cpfFormatado);

		var pessoaCadastrada = pessoaRepositorio.findById(cpfFormatado).orElseThrow(this::pessoaNaoEncontrada);

		pessoaCadastrada.setNome(pessoaDTO.getNome());
		pessoaCadastrada.setSexo(pessoaDTO.getSexo());
		pessoaCadastrada.setEmail(pessoaDTO.getEmail());
		pessoaCadastrada.setDataNascimento(pessoaDTO.getDataNascimento());
		pessoaCadastrada.setNaturalidade(pessoaDTO.getNaturalidade());
		pessoaCadastrada.setNacionalidade(pessoaDTO.getNacionalidade());
		pessoaCadastrada.setDataAtualizacao(LocalDateTime.now());

		ValidadorDePessoa.valida(pessoaCadastrada);

		return pessoaRepositorio.save(pessoaCadastrada);

	}

	private NegocioException pessoaNaoEncontrada() {
		return new NegocioException(ErrorMessageEnum.E005.getCode(), ErrorMessageEnum.E005.getMessage());

	}
}
