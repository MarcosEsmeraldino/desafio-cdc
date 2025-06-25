package dev.desafiocdc.handler;

import dev.desafiocdc.handler.dtos.ErroDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class DesafioCDCExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error(request.getRequestURI(), ex);
        return buildBadRequestError(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage(), request.getRequestURI());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(DuplicateKeyException ex, HttpServletRequest request) {
        log.error(request.getRequestURI(), ex);
        return buildBadRequestError(ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDTO handleException(IllegalStateException ex, HttpServletRequest request) {
        log.error(request.getRequestURI(), ex);
        return buildBadRequestError(ex.getMessage(), request.getRequestURI());
    }

    private ErroDTO buildBadRequestError(String message, String path) {
        return buildError(400, "Bad Request", message, path);
    }

    private ErroDTO buildError(Integer status, String error, String message, String path) {
        return ErroDTO.builder()
                .timestamp(Instant.now())
                .status(status)
                .error(error)
                .message(message)
                .path(path)
                .build();
    }
}
