package io.github.eliaspinheiropereira.technedesafio.dto.request;

import io.github.eliaspinheiropereira.technedesafio.model.enums.Status;
import io.github.eliaspinheiropereira.technedesafio.model.enums.Turno;
import jakarta.validation.constraints.*;

import java.util.List;

public record TurmaRequest(

        @NotBlank(message = "Período é obrigatório")
        @Size(min = 3, max = 100, message = "Período deve ter entre 3 e 100 caracteres")
        String periodo,

        @NotNull(message = "Turno é obrigatório, escolha entre: manha, tarde, noite")
        Turno turno,

        @NotNull(message = "Status é obrigatório, escolha entre: aberta, fechada, cancelada")
        Status status,

        @NotNull(message = "Limite de vagas é obrigatório")
        @Positive(message = "Limite de vagas deve ser um número positivo")
        @Max(value = 50, message = "Limite máximo de vagas é 50")
        Integer limiteVaga,

        @NotEmpty(message = "Lista de disciplinas é obrigatória")
        List<@Positive(message = "ID da disciplina deve ser um número positivo") Long> disciplinaIds

) {}

