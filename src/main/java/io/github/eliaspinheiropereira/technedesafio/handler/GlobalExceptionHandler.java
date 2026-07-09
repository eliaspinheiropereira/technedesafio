package io.github.eliaspinheiropereira.technedesafio.handler;

import io.github.eliaspinheiropereira.technedesafio.dto.response.ErroCampoResponse;
import io.github.eliaspinheiropereira.technedesafio.dto.response.ErroRespostaResponse;
import io.github.eliaspinheiropereira.technedesafio.exception.AlunoCadastradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.AlunoNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.CursoCadastradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.CursoNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.DisciplinaCadastradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.DisciplinaNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.EnderecoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<ErroCampoResponse> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErroCampoResponse(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação nos campos enviados",
                erros
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(EnderecoNaoEncontradoException.class)
    public ResponseEntity<ErroRespostaResponse> handleEnderecoNaoEncontradoException(
            EnderecoNaoEncontradoException ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<ErroRespostaResponse> handleAlunoNaoEncontradoException(
            AlunoNaoEncontradoException ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(AlunoCadastradoException.class)
    public ResponseEntity<ErroRespostaResponse> handleAlunoCadastradoException(
            AlunoCadastradoException ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

    @ExceptionHandler(CursoNaoEncontradoException.class)
    public ResponseEntity<ErroRespostaResponse> handleCursoNaoEncontradoException(
            CursoNaoEncontradoException ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(DisciplinaNaoEncontradoException.class)
    public ResponseEntity<ErroRespostaResponse> handleDisciplinaNaoEncontradoException(
            DisciplinaNaoEncontradoException ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(CursoCadastradoException.class)
    public ResponseEntity<ErroRespostaResponse> handleCursoCadastradoException(
            CursoCadastradoException ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

    @ExceptionHandler(DisciplinaCadastradoException.class)
    public ResponseEntity<ErroRespostaResponse> handleDisciplinaCadastradoException(
            DisciplinaCadastradoException ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespostaResponse> handleGenericException(Exception ex) {

        ErroRespostaResponse resposta = new ErroRespostaResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                List.of()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}

