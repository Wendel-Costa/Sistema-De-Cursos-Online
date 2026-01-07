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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public abstract String getModalidade();

    public abstract String getCurso();
}
