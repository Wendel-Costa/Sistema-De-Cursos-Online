package sistemacursos.model.cursos;

import java.time.LocalDateTime;
import sistemacursos.model.usuarios.Professor;

public class CursoAoVivo extends Curso {
    private final LocalDateTime dataAula;
    private final int vagas;

    public CursoAoVivo(int id, String titulo, int cargaHoraria, double precoBase, Professor professor, LocalDateTime dataAula, int vagas) {

        super(id, titulo, cargaHoraria, precoBase, professor);
        this.dataAula = dataAula;
        this.vagas = vagas;
    }

    public LocalDateTime getDataAula() {
        return dataAula;
    }

    public int getVagas() {
        return vagas;
    }

    @Override
    public double calcularPrecoFinal() {
        return precoBase * 1.2;
    }

    @Override
    public String getDescricao() {
        return "Curso ao vivo";
    }

    @Override
    public String getModalidade() {
        return "Ao Vivo";
    }

    @Override
    public String getCurso() {
        return String.format(
            "CursoAoVivo [id=%d, titulo=%s, data=%s, vagas=%d, precoFinal=%.2f, professor=%s]",
            id, titulo, dataAula, vagas, calcularPrecoFinal(), professor.getNome()
        );
    }
}
