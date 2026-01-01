package sistemacursos.model.cursos;

public abstract class Curso implements Produto {
    protected int id;
    protected String titulo;
    protected int cargaHoraria;
    protected double precoBase;

    public Curso(int id, String titulo, int cargaHoraria, double precoBase) {
        this.id = id;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.precoBase = precoBase;
    }

    public int getId() {
        return id;
    }
    
    public abstract String getModalidade();

    public abstract String getCurso();
}