package io.github.eliaspinheiropereira.technedesafio.service;

import io.github.eliaspinheiropereira.technedesafio.dto.request.AlunoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.CursoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.DisciplinaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.EnderecoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaUpdateRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.TurmaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.MatriculaResponse;
import io.github.eliaspinheiropereira.technedesafio.model.Aluno;
import io.github.eliaspinheiropereira.technedesafio.model.Curso;
import io.github.eliaspinheiropereira.technedesafio.model.Disciplina;
import io.github.eliaspinheiropereira.technedesafio.model.Matricula;
import io.github.eliaspinheiropereira.technedesafio.model.Turma;
import io.github.eliaspinheiropereira.technedesafio.model.enums.Status;
import io.github.eliaspinheiropereira.technedesafio.model.enums.StatusMatricula;
import io.github.eliaspinheiropereira.technedesafio.model.enums.Turno;
import io.github.eliaspinheiropereira.technedesafio.repository.AlunoRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.CursoRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.DisciplinaRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.MatriculaRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.TurmaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class MatriculaServiceTest {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    private Long alunoId;
    private Long turmaId;

    @BeforeEach
    public void setUp() {
        // Limpar dados anteriores
        matriculaRepository.deleteAll();
        turmaRepository.deleteAll();
        disciplinaRepository.deleteAll();
        cursoRepository.deleteAll();
        alunoRepository.deleteAll();

        EnderecoRequest enderecoRequest = new EnderecoRequest("Rua das Flores", "123", "Bairro Central", "São Paulo", "SP", "12345678");
        AlunoRequest alunoRequest = new AlunoRequest("João Silva", "123.456.789-10", "joao@email.com", LocalDate.of(1995, 5, 15), enderecoRequest);
        alunoService.cadastrarAluno(alunoRequest);
        Aluno aluno = alunoRepository.findAll().get(0);
        alunoId = aluno.getId();

        CursoRequest cursoRequest = new CursoRequest("Engenharia de Software", 8);
        cursoService.cadastrarCurso(cursoRequest);
        Curso curso = cursoRepository.findAll().get(0);

        DisciplinaRequest disciplinaRequest = new DisciplinaRequest("Programação Java", 120, curso.getId());
        disciplinaService.cadastrarDisciplina(disciplinaRequest);
        Disciplina disciplina = disciplinaRepository.findAll().get(0);

        TurmaRequest turmaRequest = new TurmaRequest("2024/1", Turno.MANHA, Status.ABERTA, 30, List.of(disciplina.getId()));
        turmaService.cadastrarTurma(turmaRequest);
        Turma turma = turmaRepository.findAll().get(0);
        turmaId = turma.getId();
    }

    @Test
    @DisplayName("Deve cadastrar uma matrícula com sucesso")
    public void testCadastrarMatricula() {
        // Arrange
        MatriculaRequest matriculaRequest = new MatriculaRequest(alunoId, turmaId, LocalDate.now());

        // Act
        matriculaService.cadastrarMatricula(matriculaRequest);

        // Assert
        List<Matricula> matriculas = matriculaRepository.findAll();
        assertThat(matriculas).hasSize(1);
        assertThat(matriculas.get(0).getAluno().getId()).isEqualTo(alunoId);
        assertThat(matriculas.get(0).getTurma().getId()).isEqualTo(turmaId);
        assertThat(matriculas.get(0).getStatusMatricula()).isEqualTo(StatusMatricula.PENDENTE);
        assertThat(matriculas.get(0).getDataMatricula()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Deve atualizar o status da matrícula para CONFIRMADA com sucesso")
    public void testAtualizarMatricula() {
        // Arrange - Criar matrícula inicial
        matriculaService.cadastrarMatricula(new MatriculaRequest(alunoId, turmaId, LocalDate.now()));
        Matricula matriculaCriada = matriculaRepository.findAll().get(0);
        var matriculaId = matriculaCriada.getCodigoMatricula();

        // Act - Atualizar para CONFIRMADA
        var updateRequest = new MatriculaUpdateRequest(StatusMatricula.CONFIRMADA);
        matriculaService.atualizarMatricula(matriculaId, updateRequest);

        // Assert
        Matricula matriculaAtualizada = matriculaRepository.findById(matriculaId).orElseThrow();
        assertThat(matriculaAtualizada.getStatusMatricula()).isEqualTo(StatusMatricula.CONFIRMADA);
        assertThat(matriculaAtualizada.getAluno().getId()).isEqualTo(alunoId);
        assertThat(matriculaAtualizada.getTurma().getId()).isEqualTo(turmaId);
        assertThat(matriculaAtualizada.getDataMatricula()).isEqualTo(LocalDate.now());
    }

    @Test
    public void testRemoverMatricula() {
        // Arrange - Criar matrícula
        matriculaService.cadastrarMatricula(new MatriculaRequest(alunoId, turmaId, LocalDate.now()));
        Matricula matriculaCriada = matriculaRepository.findAll().get(0);
        var matriculaId = matriculaCriada.getCodigoMatricula();

        // Validar que a matrícula foi criada
        assertThat(matriculaRepository.findById(matriculaId)).isPresent();

        // Act - Remover matrícula
        matriculaService.removerMatricula(matriculaId);

        // Assert
        assertThat(matriculaRepository.findById(matriculaId)).isEmpty();
        assertThat(matriculaRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("Deve buscar matrículas por nome do aluno com sucesso")
    public void testBuscarMatriculasPorAluno() {
        // Arrange - Criar uma matrícula
        matriculaService.cadastrarMatricula(new MatriculaRequest(alunoId, turmaId, LocalDate.now()));

        // Act - Buscar matrículas pelo nome do aluno
        List<MatriculaResponse> matriculas = matriculaService.buscarMatriculasPorAluno("João");

        // Assert
        assertThat(matriculas).isNotNull();
        assertThat(matriculas).isNotEmpty();
        assertThat(matriculas.get(0).alunoNome()).isEqualTo("João Silva");
        assertThat(matriculas.get(0).alunoId()).isEqualTo(alunoId);
        assertThat(matriculas.get(0).turmaId()).isEqualTo(turmaId);
    }

    @Test
    @DisplayName("Deve buscar matrículas por período da turma com sucesso")
    public void testBuscarMatriculasPorTurma() {
        // Arrange - Criar uma matrícula
        matriculaService.cadastrarMatricula(new MatriculaRequest(alunoId, turmaId, LocalDate.now()));

        // Act - Buscar matrículas pelo período da turma
        List<MatriculaResponse> matriculas = matriculaService.buscarMatriculasPorTurma("2024/1");

        // Assert
        assertThat(matriculas).isNotNull();
        assertThat(matriculas).isNotEmpty();
        assertThat(matriculas.get(0).turmaPeriodo()).isEqualTo("2024/1");
        assertThat(matriculas.get(0).turmaId()).isEqualTo(turmaId);
        assertThat(matriculas.get(0).alunoId()).isEqualTo(alunoId);
    }
}
