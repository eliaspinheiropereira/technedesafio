package io.github.eliaspinheiropereira.technedesafio.dto.request;

import jakarta.validation.constraints.*;

public record CursoRequest(

        @NotBlank(message = "Nome do curso é obrigatório")
        @Size(min = 3, max = 255, message = "Nome do curso deve ter entre 3 e 255 caracteres")
        String nome,

        @NotNull(message = "Duração em semestre é obrigatória")
        @Positive(message = "Duração em semestre deve ser um número positivo")
        @Max(value = 12, message = "Duração máxima é de 12 semestres")
        Integer duracaoEmSemestre

) {}

