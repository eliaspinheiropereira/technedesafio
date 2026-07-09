package io.github.eliaspinheiropereira.technedesafio.dto.response;

import jakarta.validation.constraints.*;

public record CursoResponse(

        Long id,
        String nome,
        Integer duracaoEmSemestre

) {}

