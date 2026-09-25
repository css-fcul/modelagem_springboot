package pt.ul.fc.css.academico.services;
import pt.ul.fc.css.academico.dtos.CursoDto;
import pt.ul.fc.css.academico.dtos.InscricaoDto;
import pt.ul.fc.css.academico.entities.Aluno;
import pt.ul.fc.css.academico.entities.Curso;
import pt.ul.fc.css.academico.entities.Professor;
import pt.ul.fc.css.academico.exception.BusinessRuleException;
import pt.ul.fc.css.academico.exception.ResourceNotFoundException;
import pt.ul.fc.css.academico.repositories.AlunoRepository;
import pt.ul.fc.css.academico.repositories.CursoRepository;
import pt.ul.fc.css.academico.repositories.ProfessorRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public CursoService(CursoRepository cursoRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.cursoRepository = cursoRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public CursoDto criarCurso(CursoDto cursoDto) {
        return null; // TODO
    }


    /**
     * Inscreve um aluno no curso, aplicando as regras de negócio.
     * @param cursoId O id do curso que terá o aluno inscrito
     * @param alunoId O id do aluno a ser inscrito.
     * 
     * @throws BusinessRuleException Se a inscrição violar alguma regra de negócio.
     */
    @Transactional
    public InscricaoDto inscreverAluno(Long cursoId, Long alunoId) throws BusinessRuleException {

        Curso curso = null; // TODO ir buscar o curso correto no repositorio
        Aluno aluno = null; // TODO ir buscar o aluno correto no repositorio

        // Verificar se o curso atingiu a capacidade máxima
        if (this.getNumeroInscricoes(curso) >= curso.getCapacidade()) {
            throw new BusinessRuleException("O curso já atingiu a capacidade máxima.");
        }
        
        // Verificar se o aluno já está inscrito
        if (this.alunoJaInscrito(curso, aluno)) {
            throw new BusinessRuleException("O aluno já está inscrito neste curso.");
        }
        
        //TODO

        return null; // TODO
    }

    private int getNumeroInscricoes(Curso curso) {
        return 0; // TODO
    }

    private boolean alunoJaInscrito(Curso curso, Aluno aluno) {
        return false; // TODO
    }

}