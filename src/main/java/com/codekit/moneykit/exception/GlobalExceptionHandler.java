package com.codekit.moneykit.exception;

import com.codekit.moneykit.dto.ApiResponse;
import com.codekit.moneykit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        // Log the exception stack trace for debugging
        logger.error("Unexpected error occurred: ", ex);
        // Return a generic error response to the client
        ApiResponse<?> response = new ApiResponse<>(false, "An unexpected error occurred. Please try again later.", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidConfigurationPropertyValueException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(InvalidConfigurationPropertyValueException ex) {
        ApiResponse<?> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<?> response = new ApiResponse<>(false, "Request body is missing or invalid", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ApiResponse<?> response = new ApiResponse<>(false, "Request body is missing or invalid", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Duplicate entry detected. Please ensure the unique fields are not already in use.";
        ApiResponse<?> response = new ApiResponse<>(false, message, null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Add other exception handlers as needed
}
