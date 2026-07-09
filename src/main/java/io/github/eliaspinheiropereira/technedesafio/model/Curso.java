package io.github.eliaspinheiropereira.technedesafio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int duracaoEmSemestre;

    @OneToMany(
            mappedBy = "curso",
            cascade = CascadeType.ALL
    )
    private List<Disciplina> disciplinas = new ArrayList<>();
}
