package sistemacursos.model.cursos;

import java.util.Date;
import sistemacursos.model.usuarios.Professor;

public class CursoGravado extends Curso {
    private Date dataVencimento;

    public CursoGravado(int id, String titulo, int cargaHoraria, double precoBase, Professor professor, Date dataVencimento) {
        super(id, titulo, cargaHoraria, precoBase, professor);
        this.dataVencimento = dataVencimento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void renovarContrato(Date novaDataVencimento) {
        this.dataVencimento = novaDataVencimento;
    }

    @Override
    public double calcularPrecoFinal() {
        return precoBase * 0.9;
    }

    @Override
    public String getDescricao() {
        return String.format("Curso gravado disponível até %s", dataVencimento);
    }

    @Override
    public String getModalidade() {
        return "Gravado";
    }

    @Override
    public String getCurso() {
        return String.format(
                "CursoGravado [id=%d, titulo=%s, cargaHoraria=%d, precoFinal=%.2f, vencimento=%s, professor=%s]",
                id, titulo, cargaHoraria, calcularPrecoFinal(), dataVencimento, professor.getNome()
        );
    }
}
