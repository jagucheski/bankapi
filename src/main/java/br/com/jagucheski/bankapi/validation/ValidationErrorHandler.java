package br.com.jagucheski.bankapi.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	/**
	 * Retorna codigo 400 - HttpStatus.BAD_REQUEST
	 * */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDto> handle(MethodArgumentNotValidException exception) {
		List<ErrorFormDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			ErrorFormDto erro = new ErrorFormDto(e.getField(), e.getDefaultMessage());
			dto.add(erro);
		});
		
		return dto;
	}

}
