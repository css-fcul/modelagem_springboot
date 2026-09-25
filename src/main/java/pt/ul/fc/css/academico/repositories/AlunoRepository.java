package pt.ul.fc.css.academico.repositories;

import pt.ul.fc.css.academico.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {}