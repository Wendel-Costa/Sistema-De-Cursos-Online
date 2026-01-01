package sistemacursos.model.cursos;

import java.time.LocalDateTime;
import sistemacursos.model.usuarios.Professor;

public class CursoAoVivo extends Curso {
    private final LocalDateTime dataHoraAula;
    private final int vagas;

    public CursoAoVivo(int id, String titulo, int cargaHoraria, double precoBase, Professor professor, LocalDateTime dataHoraAula, int vagas) {
        super(id, titulo, cargaHoraria, precoBase, professor);
        this.dataHoraAula = dataHoraAula;
        this.vagas = vagas;
    }

    public boolean possuiVagas() {
        return vagas > 0;
    }

    @Override
    public double calcularPrecoFinal() {
        return precoBase * 1.2;
    }

    @Override
    public String getDescricao() {
        return String.format("Curso ao vivo em %s | Vagas: %d", dataHoraAula, vagas);
    }

    @Override
    public String getModalidade() {
        return "Ao Vivo";
    }

    @Override
    public String getCurso() {
        return String.format(
                "CursoAoVivo [id=%d, titulo=%s, data=%s, vagas=%d, precoFinal=%.2f, professor=%s]",
                id, titulo, dataHoraAula, vagas, calcularPrecoFinal(), professor.getNome()
        );
    }
}
