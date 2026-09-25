package pt.ul.fc.css.academico;
import pt.ul.fc.css.academico.dtos.AlunoDto;
import pt.ul.fc.css.academico.dtos.CursoDto;
import pt.ul.fc.css.academico.dtos.ProfessorDto;
import pt.ul.fc.css.academico.exception.BusinessRuleException;
import pt.ul.fc.css.academico.services.AlunoService;
import pt.ul.fc.css.academico.services.CursoService;
import pt.ul.fc.css.academico.services.ProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleClient{


    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SimpleClient.class, args);
    }

    private final ProfessorService professorService;
    private final AlunoService alunoService;
    private final CursoService cursoService;

    public SimpleClient(ProfessorService professorService, AlunoService alunoService, CursoService cursoService) {
        this.professorService = professorService;
        this.alunoService = alunoService;
        this.cursoService = cursoService;
    }

   @Bean
    public CommandLineRunner demo() {
        return args -> {
            System.out.println("\n\n--- Demonstração do programa ---");

        System.out.println("\n>>> Criando alguns dados iniciais");
        ProfessorDto professorW = professorService.criarProfessor(new ProfessorDto(null, "Prof. Wellington", "wdojunior@fc.ul.pt"));
        System.out.println("  [OK] Professor criado: " + professorW.nome());

         ProfessorDto professorB = professorService.criarProfessor(new ProfessorDto(null, "Prof. Bernardo", "bpdalmeida@ciencias.ulisboa.pt"));
        System.out.println("  [OK] Professor criado: " + professorB.nome());

        AlunoDto alunaAna = alunoService.criarAluno(new AlunoDto(null, "Ana", "fc1234567@fc.ul.pt"));
        System.out.println("  [OK] Aluno criado: " + alunaAna.nome());

        AlunoDto alunoBruno = alunoService.criarAluno(new AlunoDto(null, "Bruno", "fc1234568@fc.ul.pt"));
        System.out.println("  [OK] Aluno criado: " + alunoBruno.nome());

        // Criar um curso com capacidade para apenas 1 aluno para facilitar o teste
        CursoDto curso = cursoService.criarCurso(new CursoDto(null, "Construção de Sistemas de Software", 1, professorW.id()));
        System.out.println("  [OK] Curso criado: " + curso.nome() + " com capacidade " + curso.capacidade());

        System.out.println("\n>>> Testando uma inscrição válida");
        try {
            cursoService.inscreverAluno(curso.id(), alunaAna.id());
            System.out.println("  [SUCESSO] Aluna '" + alunaAna.nome() + "' inscrita no curso '" + curso.nome() + "'.");
        } catch (Exception e) {
            System.err.println("  [ERRO INESPERADO] Falha na inscrição válida: " + e.getMessage());
        }

        System.out.println("\n>>> Testando uma inscrição duplicada (esperado falhar)");
        try {
            cursoService.inscreverAluno(curso.id(), alunaAna.id());
            // Se chegar aqui, o teste falhou, pois a exceção deveria ter sido lançada
            System.err.println("  [FALHA NO TESTE] A aplicação permitiu uma inscrição duplicada.");
        } catch (BusinessRuleException e) {
            System.out.println("  [SUCESSO NO TESTE] A aplicação impediu a inscrição duplicada como esperado.");
            System.out.println("     -> Mensagem recebida: '" + e.getMessage() + "'");
        } catch (Exception e) {
            System.err.println("  [ERRO INESPERADO] Ocorreu uma exceção diferente da esperada: " + e.getMessage());
        }

        System.out.println("\n>>> Testando se a capacidade foi excedida (esperado falhar)");
        try {
            cursoService.inscreverAluno(curso.id(), alunoBruno.id());
            // Se chegar aqui, o teste falhou
            System.err.println("  [FALHA NO TESTE] A aplicação permitiu exceder a capacidade do curso.");
        } catch (BusinessRuleException e) {
            // Este é o resultado esperado
            System.out.println("  [SUCESSO NO TESTE] A aplicação impediu a inscrição por falta de capacidade como esperado.");
            System.out.println("     -> Mensagem recebida: '" + e.getMessage() + "'");
        } catch (Exception e) {
            System.err.println("  [ERRO INESPERADO] Ocorreu uma exceção diferente da esperada: " + e.getMessage());
        }

        System.out.println("\n--- Final do cliente ---\n\n");
        };
}
}