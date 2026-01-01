package sistemacursos.model.cursos;

import sistemacursos.model.usuarios.Professor;

public abstract class Curso implements Produto {
    protected int id;
    protected String titulo;
    protected int cargaHoraria;
    protected double precoBase;
    protected Professor professor;

    public Curso(int id, String titulo, int cargaHoraria, double precoBase, Professor professor) {
        this.id = id;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.precoBase = precoBase;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public abstract String getModalidade();

    public abstract String getCurso();
}
