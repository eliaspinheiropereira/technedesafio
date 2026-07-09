package io.github.eliaspinheiropereira.technedesafio.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Turno {
    MANHA,
    TARDE,
    NOITE;

    @JsonCreator
    public static Turno fromString(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Turno não pode ser vazio");
        }

        try {
            return Turno.valueOf(valor.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Turno '%s' inválido. Valores aceitos: manha, tarde, noite", valor)
            );
        }
    }
}
