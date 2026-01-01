package sistemacursos.model.cursos;

import sistemacursos.model.usuarios.Professor;

public class Workshop implements Produto {
    private final int id;
    private final String tema;
    private final double preco;
    private final int duracaoHoras;
    private final Professor professor;

    public Workshop(int id, String tema, double preco, int duracaoHoras, Professor professor) {
        this.id = id;
        this.tema = tema;
        this.preco = preco;
        this.duracaoHoras = duracaoHoras;
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double calcularPrecoFinal() {
        return preco;
    }

    @Override
    public String getDescricao() {
        return String.format("Workshop [tema=%s, duracao=%dh, preco=%.2f, professor=%s]",
        tema, duracaoHoras, preco, professor.getNome());
    }
}
