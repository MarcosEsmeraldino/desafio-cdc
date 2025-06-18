package dev.desafiocdc.handler.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class ErroDTO {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private List<String> messages;
    private String path;
}