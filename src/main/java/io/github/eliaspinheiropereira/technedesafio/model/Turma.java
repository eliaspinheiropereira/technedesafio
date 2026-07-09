package io.github.eliaspinheiropereira.technedesafio.model;

import io.github.eliaspinheiropereira.technedesafio.model.enums.Status;
import io.github.eliaspinheiropereira.technedesafio.model.enums.Turno;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "turmas")
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String periodo;
    private Turno turno;
    private Status status;
    private int limiteVaga;
    private int vagaDisponivel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disciplina_id", referencedColumnName = "id", nullable = false)
    private Disciplina disciplina;
}
