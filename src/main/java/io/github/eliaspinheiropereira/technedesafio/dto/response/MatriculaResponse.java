package io.github.eliaspinheiropereira.technedesafio.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.eliaspinheiropereira.technedesafio.model.enums.StatusMatricula;

import java.time.LocalDate;
import java.util.UUID;

public record MatriculaResponse(

        UUID codigoMatricula,

        Long alunoId,

        String alunoNome,

        Long turmaId,

        String turmaPeriodo,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataMatricula,

        StatusMatricula statusMatricula

) {}

