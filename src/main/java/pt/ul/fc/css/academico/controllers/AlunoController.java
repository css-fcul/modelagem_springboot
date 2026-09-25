
package pt.ul.fc.css.academico.controllers;
import pt.ul.fc.css.academico.dtos.AlunoDto;
import pt.ul.fc.css.academico.services.AlunoService;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private final AlunoService alunoService;
    public AlunoController(AlunoService alunoService) { this.alunoService = alunoService; }

    @GetMapping
    public List<AlunoDto> listar() {
        return alunoService.listarAlunos();
    }

    @PostMapping
    public ResponseEntity<AlunoDto> criar(@RequestBody AlunoDto alunoDto) {
        return ResponseEntity.ok(alunoService.criarAluno(alunoDto));
    }
}