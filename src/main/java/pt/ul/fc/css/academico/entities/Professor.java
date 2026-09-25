package pt.ul.fc.css.academico.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private Set<Curso> cursos = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<Curso> getCursos() { return cursos; }
    public void setCursos(Set<Curso> cursos) { this.cursos = cursos; }
}