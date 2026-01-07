package sistemacursos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sistemacursos.model.cursos.Curso;
import sistemacursos.model.cursos.CursoAoVivo;
import sistemacursos.model.cursos.CursoGravado;
import sistemacursos.model.usuarios.Professor;

public class MatriculaDAO {

    public void matricular(int alunoId, int cursoId) {
        String sql = "INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (?, ?)";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ps.setInt(2, cursoId);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirPorAluno(int alunoId) {
        String sql = "DELETE FROM aluno_curso WHERE aluno_id = ?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirPorCurso(int cursoId) {
        String sql = "DELETE FROM aluno_curso WHERE curso_id = ?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cursoId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Curso> listarCursosDoAluno(int alunoId) {
        List<Curso> cursos = new ArrayList<>();

        String sql = """
            SELECT c.*, p.id pid, p.nome pnome, p.email, p.especialidade
            FROM curso c
            JOIN aluno_curso ac ON ac.curso_id = c.id
            JOIN professor p ON p.id = c.professor_id
            WHERE ac.aluno_id = ?
        """;

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Professor prof = new Professor(
                        rs.getInt("pid"),
                        rs.getString("pnome"),
                        rs.getString("email"),
                        rs.getString("especialidade")
                );

                if ("Gravado".equals(rs.getString("modalidade"))) {
                    cursos.add(new CursoGravado(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getInt("carga_horaria"),
                            rs.getDouble("preco_base"),
                            prof,
                            rs.getDate("data_vencimento")
                    ));
                } else {
                    cursos.add(new CursoAoVivo(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getInt("carga_horaria"),
                            rs.getDouble("preco_base"),
                            prof,
                            rs.getTimestamp("data_aula").toLocalDateTime(),
                            rs.getInt("vagas")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursos;
    }
}
