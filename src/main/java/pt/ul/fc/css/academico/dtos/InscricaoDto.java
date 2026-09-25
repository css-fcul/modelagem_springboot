package pt.ul.fc.css.academico.dtos;
import java.time.LocalDate;
public record InscricaoDto(Long alunoId, String alunoNome, Long cursoId, String cursoNome, Double notaFinal, LocalDate dataInscricao) {}