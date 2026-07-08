package io.github.eliaspinheiropereira.technedesafio.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record AlunoRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 caracteres")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 dígitos")
        @CPF(message = "CPF deve ser válido")
        String cpf,

        @Email(message = "Email deve ser válido")
        String email,

        @Past(message = "Data de nascimento deve ser uma data passada")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @Valid
        @NotNull(message = "Endereço é obrigatório")
        EnderecoRequest endereco
) {
}

