package io.github.eliaspinheiropereira.technedesafio.dto.response;

import io.github.eliaspinheiropereira.technedesafio.model.enums.Status;
import io.github.eliaspinheiropereira.technedesafio.model.enums.Turno;

import java.util.List;

public record TurmaResponse(

        Long id,

        String periodo,

        Turno turno,

        Status status,

        Integer limiteVaga,

        Integer vagaDisponivel,

        List<DisciplinaResponse> disciplinas

) {}

