package io.github.eliaspinheiropereira.technedesafio.model;

import io.github.eliaspinheiropereira.technedesafio.model.enums.StatusMatricula;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "matriculas")
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigoMatricula;
    private LocalDate dataMatricula;
    private StatusMatricula statusMatricula;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turma_id", referencedColumnName = "id", nullable = false)
    private Turma turma;
}
