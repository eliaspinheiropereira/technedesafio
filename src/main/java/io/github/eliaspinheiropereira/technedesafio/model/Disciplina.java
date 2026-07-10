package io.github.eliaspinheiropereira.technedesafio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disciplinas")
@Data
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int cargaHoraria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Turma> turmas = new ArrayList<>();
}
