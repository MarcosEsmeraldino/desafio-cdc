package dev.desafiocdc.handler;

import dev.desafiocdc.handler.dtos.ErroDTO;
import dev.desafiocdc.handler.exceptions.EmailDuplicadoException;
import dev.desafiocdc.handler.exceptions.NomeDuplicadoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class DesafioCDCExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error(request.getRequestURI(), ex);

        List<String> messages = null;
        String message = null;

        if (ex.getBindingResult().getFieldErrorCount() > 1) {
            messages = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
        } else {
            message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        }

        return ErroDTO.builder()
                .timestamp(Instant.now())
                .status(400)
                .error("Bad Request")
                .messages(messages)
                .message(message)
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
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

    @ExceptionHandler(EmailDuplicadoException.class)
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

    @ExceptionHandler(NomeDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(NomeDuplicadoException ex, HttpServletRequest request) {
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
