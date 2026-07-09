package io.github.eliaspinheiropereira.technedesafio.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Turno {
    MANHA,
    TARDE,
    NOITE;

    @JsonCreator
    public static Turno fromString(String valor) {
        return Turno.valueOf(valor.toUpperCase().trim());
    }
}
