package pt.ul.fc.css.academico.services;

import pt.ul.fc.css.academico.dtos.ProfessorDto;
import pt.ul.fc.css.academico.entities.Professor;
import pt.ul.fc.css.academico.repositories.ProfessorRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorDto> listarProfessores() {

        List<Professor> professors = professorRepository.findAll();
        List<ProfessorDto> professorDtos = new ArrayList<>();

        for (Professor p : professors) {
            ProfessorDto dto = new ProfessorDto(p.getId(), p.getNome(), p.getEmail());
            professorDtos.add(dto);
        }
        return professorDtos;
    }

    public ProfessorDto criarProfessor(ProfessorDto professorDto) {
        Professor professor = new Professor();
        professor.setNome(professorDto.nome());
        professor.setEmail(professorDto.email());
        professor = professorRepository.save(professor);
        return new ProfessorDto(professor.getId(), professor.getNome(), professor.getEmail());
    }
}