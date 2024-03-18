package com.example.javacrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDTO(
        String id,
        @NotBlank @NotNull(message = "titulo é obrigatório") String titulo,
        @NotBlank @NotNull String autor,
        @NotBlank @NotNull String editora,
        @NotNull Integer numeroPaginas
) {}