package pt.ul.fc.css.academico.services;
import pt.ul.fc.css.academico.dtos.AlunoDto;
import pt.ul.fc.css.academico.entities.Aluno;
import pt.ul.fc.css.academico.repositories.AlunoRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }
    
    public List<AlunoDto> listarAlunos() {
        return alunoRepository.findAll().stream()
                .map(a -> new AlunoDto(a.getId(), a.getNome(), a.getEmail()))
                .collect(Collectors.toList());
    }

    public AlunoDto criarAluno(AlunoDto alunoDto) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDto.nome());
        aluno.setEmail(alunoDto.email());
        aluno = alunoRepository.save(aluno);
        return new AlunoDto(aluno.getId(), aluno.getNome(), aluno.getEmail());
    }
}