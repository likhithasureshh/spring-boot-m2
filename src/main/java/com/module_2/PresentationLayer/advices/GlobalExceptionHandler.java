package com.module_2.PresentationLayer.advices;

import com.module_2.PresentationLayer.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        ApiError apiError = ApiError.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                                .message(exception.getMessage()).build();
        return buildErrorResponse(apiError);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(Exception exception)
    {
        ApiError apiError = ApiError.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                .message(exception.getMessage()).build();
        return  buildErrorResponse(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                                .message("Input is invaid")
                                        .subErrors(errors).build();
        return buildErrorResponse(apiError);
    }
    private ResponseEntity<ApiResponse<?>> buildErrorResponse(ApiError apiError)
    {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getHttpStatus());
    }


}
