package io.github.eliaspinheiropereira.technedesafio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(

        @NotBlank(message = "Logradouro é obrigatório")
        @Size(min = 3, max = 255, message = "Logradouro deve ter entre 3 e 255 caracteres")
        String logradouro,

        @NotBlank(message = "Número é obrigatório")
        @Size(min = 1, max = 10, message = "Número deve ter entre 1 e 10 caracteres")
        String numero,

        @NotBlank(message = "Bairro é obrigatório")
        @Size(min = 2, max = 100, message = "Bairro deve ter entre 2 e 100 caracteres")
        String bairro,

        @NotBlank(message = "Cidade é obrigatória")
        @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
        String cidade,

        @NotBlank(message = "Estado é obrigatório")
        @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres (ex: SP, RJ)")
        String estado,

        @NotBlank(message = "CEP é obrigatório")
        @Size(min = 8, max = 8, message = "CEP deve ter exatamente 8 dígitos")
        String cep
) {
}
