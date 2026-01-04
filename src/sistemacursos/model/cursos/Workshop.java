package sistemacursos.model.cursos;

import sistemacursos.model.usuarios.Professor;

public class Workshop extends Curso {
    private int duracaoHoras;

    public Workshop(int id, String titulo, double precoBase, int duracaoHoras, Professor professor) {
        super(id, titulo, duracaoHoras, precoBase, professor);
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    @Override
    public double calcularPrecoFinal() {
        return precoBase;
    }

    @Override
    public String getDescricao() {
        return "Workshop de " + duracaoHoras + " horas";
    }

    @Override
    public String getModalidade() {
        return "Workshop";
    }

    @Override
    public String getCurso() {
        return String.format(
                "Workshop [id=%d, titulo=%s, duracao=%dh, preco=%.2f, professor=%s]",
                id, titulo, duracaoHoras, calcularPrecoFinal(), professor.getNome()
        );
    }
}
