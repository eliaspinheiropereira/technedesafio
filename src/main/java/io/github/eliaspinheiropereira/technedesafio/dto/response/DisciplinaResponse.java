package io.github.eliaspinheiropereira.technedesafio.dto.response;

import jakarta.validation.constraints.*;

public record DisciplinaResponse(

        Long id,
        String nome,
        Integer cargaHoraria,
        CursoResponse curso

) {}

