package dev.desafiocdc.handler;

import dev.desafiocdc.handler.dtos.ErroDTO;
import dev.desafiocdc.handler.exceptions.EmailDuplicadoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error(request.getRequestURI(), ex);
        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ErroDTO.builder()
                .timestamp(Instant.now())
                .status(400)
                .error("Bad Request")
                .messages(messages)
                .path(request.getRequestURI())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(DuplicateKeyException ex, HttpServletRequest request) {
        log.error(request.getRequestURI(), ex);
        return ErroDTO.builder()
                .timestamp(Instant.now())
                .status(400)
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(EmailDuplicadoException ex, HttpServletRequest request) {
        log.error(request.getRequestURI(), ex);
        return ErroDTO.builder()
                .timestamp(Instant.now())
                .status(400)
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
