package io.github.eliaspinheiropereira.technedesafio.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.eliaspinheiropereira.technedesafio.model.enums.StatusMatricula;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record MatriculaUpdateRequest(
        @NotNull(message = "Status Matricula é obrigatório, escolha entre: aberta, fechada, cancelada")
        StatusMatricula statusMatricula

) {}

