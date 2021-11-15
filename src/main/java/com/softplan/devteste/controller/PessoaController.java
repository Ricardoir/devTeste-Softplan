package com.softplan.devteste.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softplan.devteste.dto.AtualizarPessoaDTO;
import com.softplan.devteste.dto.CadastraPessoaDTO;
import com.softplan.devteste.dto.PessoaDTO;
import com.softplan.devteste.entidade.Pessoa;
import com.softplan.devteste.service.AtualizaPessoaService;
import com.softplan.devteste.service.CadastraPessoaService;
import com.softplan.devteste.service.ConsultaPessoaService;
import com.softplan.devteste.service.DeletaPessoaService;
import com.softplan.devteste.validador.FormatadorDeCPF;
import com.softplan.devteste.validador.ValidadorDeCPF;

@RestController
@RequestMapping("/pessoa/v1")
public class PessoaController {

	Logger logger = LoggerFactory.getLogger(PessoaController.class);

	@Autowired
	ConsultaPessoaService consultaPessoaService;

	@Autowired
	CadastraPessoaService cadastraPessoaService;

	@Autowired
	AtualizaPessoaService atualizaPessoaService;

	@Autowired
	DeletaPessoaService deletaPessoaService;

	@GetMapping(value = "/source")
	public ResponseEntity<String> source() {
		// todo: acessivel sem autenticação
		return ok("Retornar link projeto github");

	}

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> consultaTodasAsPessoas() {

		List<PessoaDTO> consultaTodasDTO = consultaPessoaService.consultaTodasAsPessoas();

		return ok(consultaTodasDTO);

	}

	@GetMapping(value = "/{cpf}")
	public ResponseEntity<PessoaDTO> consultaPessoaPorCpf(@PathVariable("cpf") String cpf) {
		logger.info("Consulta Pessoa por CPF: {}", cpf);

		String cpfFormatado = FormatadorDeCPF.adicionaZerosEsquerda(cpf);

		ValidadorDeCPF.validaCpf(cpfFormatado);

		PessoaDTO consultaPorCpfDTO = consultaPessoaService.consultaPessoaPorCpf(cpfFormatado);

		return ok(consultaPorCpfDTO);

	}

	@PostMapping
	public ResponseEntity<Pessoa> cadastraPessoa(@Valid @RequestBody CadastraPessoaDTO pessoaDTO,
			HttpServletRequest request) {

		logger.info("Requisição Recebida cadastraPessoa: {}", pessoaDTO);

		var pessoaCadastrada = cadastraPessoaService.cadastraPessoaPorCpf(pessoaDTO);

		var uri = ServletUriComponentsBuilder.fromRequestUri(request).path("/{cpf}")
				.buildAndExpand(pessoaCadastrada.getCpf()).toUri();

		return ResponseEntity.created(uri).body(pessoaCadastrada);

	}

	@PutMapping(value = "/{cpf}")
	public ResponseEntity<Pessoa> atualizaPessoa(@PathVariable("cpf") String cpf,
			@Valid @RequestBody AtualizarPessoaDTO pessoaDTO) {

		logger.info("Requisição Recebida atualizaPessoa: {}", pessoaDTO);

		var pessoaAtual = atualizaPessoaService.atualizaPessoaPorCpf(cpf, pessoaDTO);

		return ok(pessoaAtual);

	}

	@DeleteMapping(value = "/{cpf}")
	public ResponseEntity<String> deletaPessoaPorCpf(@PathVariable("cpf") String cpf) {
		logger.info("Requisição Recebida deletaPessoaPorCpf: {}", cpf);

		String cpfFormatado = FormatadorDeCPF.adicionaZerosEsquerda(cpf);

		ValidadorDeCPF.validaCpf(cpfFormatado);

		deletaPessoaService.deletaPessoaPorCpf(cpfFormatado);

		return noContent().build();

	}

}
