package dev.desafiocdc.handler.exceptions;

public class EmailDuplicadoException extends RuntimeException{
    public EmailDuplicadoException(String message) {
        super(message);
    }
}
