package io.github.eliaspinheiropereira.technedesafio.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.eliaspinheiropereira.technedesafio.model.enums.StatusMatricula;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record MatriculaRequest(

        @NotNull(message = "ID do aluno é obrigatório")
        @Positive(message = "ID do aluno deve ser um número positivo")
        Long alunoId,

        @NotNull(message = "ID da turma é obrigatório")
        @Positive(message = "ID da turma deve ser um número positivo")
        Long turmaId,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataMatricula
) {}

