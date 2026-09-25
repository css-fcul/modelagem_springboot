package pt.ul.fc.css.academico.controllers;

import pt.ul.fc.css.academico.dtos.CursoDto;
import pt.ul.fc.css.academico.dtos.InscricaoDto;
import pt.ul.fc.css.academico.exception.BusinessRuleException;
import pt.ul.fc.css.academico.services.CursoService;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    private final CursoService cursoService;
    public CursoController(CursoService cursoService) { this.cursoService = cursoService; }

    @PostMapping
    public ResponseEntity<CursoDto> criar(@RequestBody CursoDto cursoDto) {
        return ResponseEntity.ok(cursoService.criarCurso(cursoDto));
    }
    
    @PostMapping("/{cursoId}/inscricoes")
    public ResponseEntity<InscricaoDto> inscreverAluno(
            @PathVariable Long cursoId,
            @RequestBody Map<String, Long> payload) {

        Long alunoId = payload.get("alunoId");
        InscricaoDto inscricao;
        try {
            inscricao = cursoService.inscreverAluno(cursoId, alunoId);
            return ResponseEntity.ok(inscricao);
        } catch (BusinessRuleException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}