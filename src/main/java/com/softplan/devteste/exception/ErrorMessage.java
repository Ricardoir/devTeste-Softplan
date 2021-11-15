package com.softplan.devteste.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

	private String code;
	private String message;
	private List<ErrorField> fields;

	public ErrorMessage(ErrorMessageEnum error, List<ErrorField> fields) {
		this.code = error.getCode();
		this.message = error.getMessage();
		this.fields = fields;
		
	}

}
