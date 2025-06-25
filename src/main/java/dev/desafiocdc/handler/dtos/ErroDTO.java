package dev.desafiocdc.handler.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class ErroDTO {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}