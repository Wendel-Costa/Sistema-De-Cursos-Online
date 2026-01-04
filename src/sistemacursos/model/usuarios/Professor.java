package sistemacursos.model.usuarios;

public class Professor extends Usuario {
    private final String especialidade;

    public Professor(int id, String nome, String email, String especialidade) {
        super(id, nome, email);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String getTipo() {
        return "Professor";
    }

    @Override
    public String getUsuario() {
        return String.format(
                "Professor [id=%d, nome=%s, email=%s, especialidade=%s]",
                id, nome, email, especialidade
        );
    }

    @Override
    public String toString() {
        return nome;
    }

}