package io.github.eliaspinheiropereira.technedesafio.dto.request;

import jakarta.validation.constraints.*;

public record DisciplinaRequest(

        @NotBlank(message = "Nome da disciplina é obrigatório")
        @Size(min = 3, max = 255, message = "Nome da disciplina deve ter entre 3 e 255 caracteres")
        String nome,

        @NotNull(message = "Carga horária é obrigatória")
        @Positive(message = "Carga horária deve ser um número positivo")
        @Max(value = 300, message = "Carga horária máxima é de 300 horas")
        Integer cargaHoraria,

        @NotNull(message = "ID do curso é obrigatório")
        @Positive(message = "ID do curso deve ser um número positivo")
        Long cursoId

) {}

