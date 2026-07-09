package io.github.eliaspinheiropereira.technedesafio.model;

import io.github.eliaspinheiropereira.technedesafio.model.enums.Status;
import io.github.eliaspinheiropereira.technedesafio.model.enums.Turno;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turmas")
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String periodo;
    @Enumerated(EnumType.STRING)
    private Turno turno;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int limiteVaga;
    private int vagaDisponivel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "turma_disciplina",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();
}
