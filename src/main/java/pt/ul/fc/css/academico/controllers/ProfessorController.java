package pt.ul.fc.css.academico.controllers;

import pt.ul.fc.css.academico.dtos.ProfessorDto;
import pt.ul.fc.css.academico.services.ProfessorService;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    private final ProfessorService professorService;
    public ProfessorController(ProfessorService professorService) { this.professorService = professorService; }

    @GetMapping
    public List<ProfessorDto> listar() {
        return professorService.listarProfessores();
    }
    
    @PostMapping
    public ResponseEntity<ProfessorDto> criar(@RequestBody ProfessorDto professorDto) {
        return ResponseEntity.ok(professorService.criarProfessor(professorDto));
    }
}