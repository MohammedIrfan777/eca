package com.eca.catalog.advisors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.eca.catalog.dto.ErrorResponse;
import com.eca.catalog.exception.EcaCustomeServiceException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(value = { EcaCustomeServiceException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse exception(EcaCustomeServiceException ex) {
		log.info("Error in catalog service", ex);
		return new ErrorResponse(500, "Error in catalog service::please contact admin");
	}
}
