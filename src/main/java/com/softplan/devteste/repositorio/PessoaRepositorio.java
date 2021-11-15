package com.softplan.devteste.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softplan.devteste.entidade.Pessoa;

public interface PessoaRepositorio extends JpaRepository<Pessoa, String> {

}
