# Modelagem de Domínio com Spring Boot: Sistema de Gestão Académica

O objetivo deste exercício é aplicar os conceitos de um modelo de domínio rico para construir a lógica de negócio de uma aplicação, utilizando o Spring Boot.

O seu trabalho será dividido em três fases: primeiro, a modelagem conceitual do domínio; segundo, o design das classes; e terceiro, a implementação em código.

**Entidades Iniciais:**
1.  **Professor**: Representa um docente.
2.  **Curso**: Representa uma disciplina ou unidade curricular.
3.  **Aluno**: Representa um estudante.

**Relacionamentos e Regras de Negócio:**
*   Um `Professor` leciona vários `Cursos`
*   Um `Aluno` pode inscrever-se em vários `Cursos`, e um `Curso` pode ter vários `Alunos`
*   Um curso deve ter uma **capacidade máxima** de alunos
*   Um aluno **não se pode inscrever duas vezes** no mesmo curso
*   O sistema deve ser capaz de, futuramente, armazenar a **nota final** de um aluno *em um determinado curso*

---

# Modelagem

### Ferramentas Recomendadas para a Modelagem

**Caneta e Papel** são suficienetes para fazer o desenho da aplicação. É a solução recomendada pela velocidade com que conseguem realizar mudanças o que pode ser muito importante num primeiro momento.

Se preferir, podem utilizar ferramentas online como **draw.io**, **Lucidchart**, **FigJam**, **Mermaid**, ...

O importante é que, no final desta fase, você tenha uma visão clara de como o seu código será estruturado.

## Passo 1: Modelo de domínio


Crie um **modelo de domínio** que represente a solução para o problema descrito. O seu diagrama deve incluir:
* As entidades principais (`Professor`, `Curso`, `Aluno`) e **qualquer outra que você descubra ser necessária** para suportar os requisitos.
* Os **atributos** mais importantes para cada entidade (ex: `nome`, `email`, `capacidade`).
* Os **relacionamentos** entre as entidades, incluindo a cardinalidade (ex: 1..*, *..*).

## Passo 2: Diagrama de classes

Utilizando o modelo de domínio criado no exercício anterior, crie um **Diagrama de classes** simplificado. O seu diagrama deve incluir:
* Todas as entidades do sistema, com seus respectivos atributos e tipos.
* Os **métodos** que cada entidade deve ter.

---

# Implementação

Com base nos diagramas construídos anteriormente, traduza o seu diagrama de classes para Java. Crie e complete as classes de domínio, garantindo que elas contenham os métodos que você identificou.

Todas os `controllers` e `repositories` já estão implementados. Devem focar sua implementação nas entidades, handlers e DTOs que forem necessários. 

Toda a stack do `Professor` já se encontra implementada e deve ser utilizada como exemplo.

Vamos utilizar uma camada de serviço (*Service Layer*) que funciona como orquestradora.

## Arquitetura

O projeto está dividido em três camadas principais:

*   **presentation Layer:** Ignorada nesta aula.
*   **business Layer:** Onde você irá trabalhar.
*   **dataaccess Layer:** Responsável pela comunicação com a base de dados via `Repositories`.

### Exemplo de Implementação

Este exemplo foca na **lógica de negócio** de `CursoService`:.

```java
public class CursoService {
        
    /**
     * Inscreve um aluno no curso, aplicando as regras de negócio.
     * @param cursoId O id do curso que terá o aluno inscrito
     * @param alunoId O id do aluno a ser inscrito.
     * 
     * @throws BusinessRuleException Se a inscrição violar alguma regra de negócio.
     */
    @Transactional
    public InscricaoDto inscreverAluno(Long cursoId, Long alunoId) throws BusinessRuleException {

        Curso curso = null; // TODO ir buscar o curso correto no repositorio
        Aluno aluno = null; // TODO ir buscar o aluno correto no repositorio

        // Verificar se o curso atingiu a capacidade máxima
        if (this.getNumeroInscricoes(curso) >= curso.getCapacidade()) {
            throw new BusinessRuleException("O curso já atingiu a capacidade máxima.");
        }
        
        // Verificar se o aluno já está inscrito
        if (this.alunoJaInscrito(curso, aluno)) {
            throw new BusinessRuleException("O aluno já está inscrito neste curso.");
        }
        
        //TODO

        return null; // TODO
    }
    
    private int getNumeroInscricoes(Curso curso) {
        // Lógica para contar quantos alunos estão inscritos.
        return 0; // TODO
    }

    private boolean alunoJaInscrito(Curso curso, Aluno aluno) {
        // Lógica para verificar se o aluno já faz parte deste curso.
        return false; // TODO
    }
}
```

**Transações:** Com o Spring Boot, a gestão de transações bastante simplificada. Deve anotar os métodos da camada de serviço (`CursoService`) com `@Transactional` e o Spring garante que todas as operações ocorram de forma atómica.

## Como testar o seu código

O ficheiro `SimpleClient.java` foi preparado para usar os Serviços. Após implementar a sua lógica, pode executar o projeto para ver um exemplo de uma transação completa.

## Como executar o projeto

Certifique-se de que tem o Docker Engine a correr na sua máquina. Depois, dentro da pasta do projeto, execute o seguinte comando no terminal:

```bash
docker-compose up --build
```

## Visualizar a base de dados

Use uma ferramenta com interface gráfica, como o **DBeaver**, para inspecionar a base de dados.

### Detalhes da ligação:

*   **Host:** `localhost`
*   **Porta:** `5432`
*   **Base de Dados:** `testdb`
*   **Utilizador:** `postgres`
*   **Palavra-passe:** `admin`
