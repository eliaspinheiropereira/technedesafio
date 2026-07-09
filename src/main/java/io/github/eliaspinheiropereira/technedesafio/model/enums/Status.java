package io.github.eliaspinheiropereira.technedesafio.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    ABERTA,
    FECHADA,
    CANCELADA;

    @JsonCreator
    public static Status fromString(String valor) {
        return Status.valueOf(valor.toUpperCase().trim());
    }
}
