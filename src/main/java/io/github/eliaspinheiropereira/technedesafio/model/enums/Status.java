package io.github.eliaspinheiropereira.technedesafio.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    ABERTA,
    FECHADA,
    CANCELADA;

    @JsonCreator
    public static Status fromString(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Status não pode ser vazio");
        }

        try {
            return Status.valueOf(valor.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Status '%s' inválido. Valores aceitos: aberta, fechada, cancelada", valor)
            );
        }
    }
}
