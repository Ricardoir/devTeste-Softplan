package com.softplan.devteste.exception;

import java.util.ArrayList;
import java.util.List;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 9014822907400016637L;

	private final ErrorMessage errorMessage;

	public NegocioException(String code, String message, List<ErrorField> fields) {
		super();
		errorMessage = new ErrorMessage(code, message, fields);
	}

	public NegocioException(String code, String message) {
		super();
		errorMessage = new ErrorMessage(code, message, List.of());
	}

	public NegocioException(String code, String message, ErrorField uniqueField) {
		super();
		List<ErrorField> uniqueFieldList = new ArrayList<>();
		uniqueFieldList.add(uniqueField);
		errorMessage = new ErrorMessage(code, message, uniqueFieldList);
	}

	public ErrorMessage getError() {
		return errorMessage;
	}
}
