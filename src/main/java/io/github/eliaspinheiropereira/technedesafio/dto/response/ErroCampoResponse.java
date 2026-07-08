package io.github.eliaspinheiropereira.technedesafio.dto.response;

public record ErroCampoResponse(
        String campo,
        String mensagem
) {
}
