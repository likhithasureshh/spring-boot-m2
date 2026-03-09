package com.module_2.PresentationLayer.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        ApiError apiError = ApiError.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                                .message(exception.getMessage()).build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleRuntimeException(Exception exception)
    {
        ApiError apiError = ApiError.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                .message(exception.getMessage()).build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                                .message("Input is invaid")
                                        .subErrors(errors).build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

}
