package pt.ul.fc.css.academico.repositories;


import pt.ul.fc.css.academico.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {}