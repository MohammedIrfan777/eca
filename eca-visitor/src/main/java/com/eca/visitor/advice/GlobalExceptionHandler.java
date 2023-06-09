package com.eca.visitor.advice;

import com.eca.visitor.constants.VisitorConstants;
import com.eca.visitor.dto.response.ErrorResponse;
import com.eca.visitor.exception.VisitorManagementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<ErrorResponse> constraintViolationException(Exception ex) {
		log.error("GlobalExceptionHandler handleConstraintViolationException {}",ex.getMessage());
		return new ResponseEntity<>(createErrorResponse(ex), HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler({VisitorManagementException.class})
	public ResponseEntity<ErrorResponse> notFound(Exception ex) {
		log.error(VisitorConstants.GLOBAL_EXCEPTION_HANDLER_KEY,ex.getMessage());
		return new ResponseEntity<>(createErrorResponse(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({SQLException.class})
	public ResponseEntity<ErrorResponse> handleSqlException(Exception ex) {
		log.error("GlobalExceptionHandler handleSqlException {}",ex.getMessage());
		return new ResponseEntity<>(createErrorResponse(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({HttpClientErrorException.class})
	public ResponseEntity<ErrorResponse> httpClientError(Exception ex) {
		log.error("GlobalExceptionHandler handleHttpClientErrorException {}",ex.getMessage());
		return new ResponseEntity<>(createErrorResponse(ex),HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorResponse> internalError(Exception ex) {
		log.error("GlobalExceptionHandler handleException {}",ex.getMessage());
		return new ResponseEntity<>(createErrorResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	private ErrorResponse createErrorResponse(Exception exception) {
		var errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setError( exception.getMessage());
		return errorResponse;
	}

}
