//Currently, if a task ID doesn't exist, your service throws RuntimeException. Spring may return a generic error response. Let's create a global exception handler.


package com.praveen.taskmanager.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.praveen.taskmanager.dto.ApiResponse;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
        MethodArgumentNotValidException ex) {

    String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.joining(", "));

    ApiResponse<Map<String, String>> response =
            new ApiResponse<>(false, message, null);

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleRuntimeException(
            RuntimeException ex) {

        ApiResponse<Map<String, String>> response =
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
