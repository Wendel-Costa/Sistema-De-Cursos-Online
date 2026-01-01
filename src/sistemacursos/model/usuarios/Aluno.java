package sistemacursos.model.usuarios;

import java.util.ArrayList;
import java.util.List;
import sistemacursos.model.cursos.Curso;

public class Aluno extends Usuario {
    private final List<Curso> cursosMatriculados;

    public Aluno(int id, String nome, String email) {
        super(id, nome, email);
        this.cursosMatriculados = new ArrayList<>();
    }

    public void matricularCurso(Curso curso) {
        cursosMatriculados.add(curso);
    }

    public List<Curso> getCursosMatriculados() {
        return cursosMatriculados;
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

    @Override
    public String getUsuario() {
        return String.format(
                "Aluno [id=%d, nome=%s, email=%s, totalCursos=%d]",
                id, nome, email, cursosMatriculados.size()
        );
    }
}
