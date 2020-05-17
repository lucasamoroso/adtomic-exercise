package com.adtomic.routes.handlers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ApiExceptionHandler {
  @ResponseBody
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String mismatchedArgumentTypeHandler(MethodArgumentTypeMismatchException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(InvalidFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String invalidFormatExceptionHandler(InvalidFormatException ex) {
    return ex.getMessage();
  }

}
