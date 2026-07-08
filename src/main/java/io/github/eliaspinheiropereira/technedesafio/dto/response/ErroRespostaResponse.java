package io.github.eliaspinheiropereira.technedesafio.dto.response;

import java.util.List;

public record ErroRespostaResponse(
        Integer status,
        String mensagem,
        List<ErroCampoResponse> erros
) {
}
