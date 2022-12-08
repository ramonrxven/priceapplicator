package com.redegal.priceApplicator.infrastructure.inputAdapter.customException;

import com.redegal.priceApplicator.domain.exceptions.PriceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomExceptionAdapter extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                Arrays.asList(request.getDescription(false)));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public final ResponseEntity<Object> handlePriceNotFoundException(PriceNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                Arrays.asList(request.getDescription(false)));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

      @Override
      protected ResponseEntity<Object> handleMethodArgumentNotValid(
              MethodArgumentNotValidException ex, HttpHeaders headers,
              HttpStatus status, WebRequest request)
      {
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
