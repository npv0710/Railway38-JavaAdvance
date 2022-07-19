package com.vti.configuration;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vti.exception.NotFoundException;
import com.vti.service.AccountService;

//@RestControllerAdvice
public class RestExceptionHadler extends ResponseEntityExceptionHandler{
	
//	@ExceptionHandler({ AccessDeniedException.class })
//    public ResponseEntity<Object> handleAccessDeniedException(
//      Exception ex, WebRequest request) {
//        return new ResponseEntity<Object>(
//          "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
//    }
	
//	@ExceptionHandler(EntityNotFoundException.class)
//	public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
//		return ResponseEntity.status(404).body("Entity not found exception");
//	}
//	
//	@ExceptionHandler(UsernameNotFoundException.class)
//	public ResponseEntity<String> handleEntityNotFound(UsernameNotFoundException ex) {
//		return ResponseEntity.status(401).body("Account does not exists");
//	}
	
//	@ExceptionHandler(ValidationException.class)
//	public ResponseEntity<String> handleNotFoundException(Exception ex) {
//		return ResponseEntity.status(500).body(ex.getMessage());
//	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<String> handleMethodArgumentNotValidException(Exception ex) {
//		return ResponseEntity.status(500).body(ex.getMessage());
//	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleException(Exception ex) {
//		return ResponseEntity.status(500).body(ex.getMessage());
//	}
}
