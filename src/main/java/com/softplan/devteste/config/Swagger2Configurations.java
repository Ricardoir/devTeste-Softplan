package com.softplan.devteste.config;

import static java.util.stream.Collectors.toSet;

import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configurations {

	private static final String BASE_PACKAGE = "com.softplan.devteste";
	private static final String TITLE = "API - Cadastro de pessoas";
	private static final String DESCRIPTION = "API de serviços do sistema de Cadastro de pessoas";
	private static final String VERSION = "1.0";
	private static final String TERMS = "";

	@Bean
	public Docket apiDoc() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData()).useDefaultResponseMessages(false)
				.consumes(Stream.of("application/json").collect(toSet())).enable(true).select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE)).paths(PathSelectors.ant("/**")).build()
				.protocols(Stream.of("http", "https").collect(toSet()));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION).version(VERSION).termsOfServiceUrl(TERMS)
				.build();
	}

}