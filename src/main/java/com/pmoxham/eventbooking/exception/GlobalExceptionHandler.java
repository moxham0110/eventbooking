package com.pmoxham.eventbooking.exception;

import com.pmoxham.eventbooking.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException exception, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    // Handle Event Date in the Past
    @ExceptionHandler(EventDateInPastException.class)
    public ResponseEntity<ErrorResponseDto> handleEventDateInPastException(EventDateInPastException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                "Invalid event date",
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    // Handle Registration Not Allowed (e.g., event is full or closed)
    @ExceptionHandler(RegistrationAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleRegistrationNotAllowedException(RegistrationAlreadyExistsException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                "Registration not allowed",
                HttpStatus.FORBIDDEN,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    public ResponseEntity<ErrorResponseDto> handleRegistrationNotAllowedException(InvalidRegistrationException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                "Registration not allowed",
                HttpStatus.FORBIDDEN,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}