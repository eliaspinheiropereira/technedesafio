package io.github.eliaspinheiropereira.technedesafio.validator;

import io.github.eliaspinheiropereira.technedesafio.dto.request.AlunoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.CursoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.DisciplinaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.EnderecoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.TurmaRequest;
import io.github.eliaspinheiropereira.technedesafio.exception.MatriculaException;
import io.github.eliaspinheiropereira.technedesafio.exception.TurmaNaoEncontradoException;
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
import io.github.eliaspinheiropereira.technedesafio.service.AlunoService;
import io.github.eliaspinheiropereira.technedesafio.service.CursoService;
import io.github.eliaspinheiropereira.technedesafio.service.DisciplinaService;
import io.github.eliaspinheiropereira.technedesafio.service.MatriculaService;
import io.github.eliaspinheiropereira.technedesafio.service.TurmaService;
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
public class MatriculaValidatorTest {

    @Autowired
    private MatriculaValidator matriculaValidator;

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
    @DisplayName("Deve validar que turma está aberta com sucesso")
    public void testValidarTurmasAbertas() {
        // Act & Assert - Não deve lançar exceção
        assertThatNoException()
                .isThrownBy(() -> matriculaValidator.validarTurmasAbertas(turmaId));
    }

    @Test
    @DisplayName("Deve lançar TurmaNaoEncontradoException quando turma não existe")
    public void testValidarTurmasAbertasComTurmaInexistente() {
        // Arrange
        Long turmaIdInexistente = 999L;

        // Act & Assert
        assertThatThrownBy(() -> matriculaValidator.validarTurmasAbertas(turmaIdInexistente))
                .isInstanceOf(TurmaNaoEncontradoException.class)
                .hasMessage("Turma não encontrada na base de dados.");
    }

    @Test
    @DisplayName("Deve validar a primeira matricula sem exceções")
    public void testValidarDuplicidadeAluno() {
        // Act & Assert - Não deve lançar exceção
        assertThatNoException()
                .isThrownBy(() -> matriculaValidator.validarDuplicidadeAluno(alunoId, turmaId));
    }

    @Test
    @DisplayName("Deve lançar MatriculaException quando aluno já tem matrícula na turma")
    public void testValidarDuplicidadeAlunoComDuplicata() {
        // Arrange - Criar primeira matrícula
        matriculaService.cadastrarMatricula(new MatriculaRequest(alunoId, turmaId, LocalDate.now()));

        // Act & Assert
        assertThatThrownBy(() -> matriculaValidator.validarDuplicidadeAluno(alunoId, turmaId))
                .isInstanceOf(MatriculaException.class)
                .hasMessage("Aluno já possui matrícula cadastrada nesta turma.");
    }

    @Test
    @DisplayName("Deve validar matrícula confirmada e decrementar vagas")
    public void testValidarMatriculaConfirmada() {
        // Arrange - Criar matrícula
        matriculaService.cadastrarMatricula(new MatriculaRequest(alunoId, turmaId, LocalDate.now()));
        Matricula matricula = matriculaRepository.findAll().get(0);
        Turma turmaAntes = turmaRepository.findById(turmaId).orElseThrow();
        Integer vagasAntes = turmaAntes.getVagaDisponivel();

        // Atualizar status para CONFIRMADA
        matricula.setStatusMatricula(StatusMatricula.CONFIRMADA);

        // Act
        matriculaValidator.validarMatricula(matricula);

        // Assert
        Turma turmaDepois = turmaRepository.findById(turmaId).orElseThrow();
        assertThat(turmaDepois.getVagaDisponivel()).isEqualTo(vagasAntes - 1);
    }

    @Test
    @DisplayName("Deve validar matrícula cancelada e incrementar vagas")
    public void testValidarMatriculaCancelada() {
        // Arrange - Criar matrícula confirmada
        matriculaService.cadastrarMatricula(new MatriculaRequest(alunoId, turmaId, LocalDate.now()));
        Matricula matricula = matriculaRepository.findAll().get(0);
        matricula.setStatusMatricula(StatusMatricula.CONFIRMADA);
        matriculaValidator.validarMatricula(matricula);

        Turma turmaAntes = turmaRepository.findById(turmaId).orElseThrow();
        Integer vagasAntes = turmaAntes.getVagaDisponivel();

        // Cancelar matrícula
        matricula.setStatusMatricula(StatusMatricula.CANCELADA);

        // Act
        matriculaValidator.validarMatricula(matricula);

        // Assert
        Turma turmaDepois = turmaRepository.findById(turmaId).orElseThrow();
        assertThat(turmaDepois.getVagaDisponivel()).isEqualTo(vagasAntes + 1);
    }
}

