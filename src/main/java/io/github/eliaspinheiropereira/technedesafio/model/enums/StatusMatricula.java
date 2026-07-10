package io.github.eliaspinheiropereira.technedesafio.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusMatricula {
    PENDENTE,
    CONFIRMADA,
    CANCELADA;

    @JsonCreator
    public static StatusMatricula fromString(String valor){
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Status não pode ser vazio");
        }

        try {
            return StatusMatricula.valueOf(valor.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("StatusMatricula '%s' inválido. Valores aceitos: pedente, confirmada, cancelada", valor)
            );
        }
    }
}
