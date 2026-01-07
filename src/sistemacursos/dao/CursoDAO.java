package sistemacursos.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sistemacursos.model.cursos.*;
import sistemacursos.model.usuarios.Professor;

public class CursoDAO {

    public void salvar(Curso c) {
        String sql = "INSERT INTO curso (titulo, carga_horaria, preco_base, modalidade, data_vencimento, data_aula, vagas, professor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getTitulo());
            ps.setInt(2, c.getCargaHoraria());
            ps.setDouble(3, c.getPrecoBase());
            ps.setString(4, c.getModalidade());

            if (c instanceof CursoGravado gravado) {
                ps.setDate(5, new java.sql.Date(gravado.getDataVencimento().getTime()));
                ps.setNull(6, Types.TIMESTAMP);
                ps.setNull(7, Types.INTEGER);
            } else if (c instanceof CursoAoVivo aoVivo) {
                ps.setNull(5, Types.DATE);
                ps.setTimestamp(6, Timestamp.valueOf(aoVivo.getDataAula()));
                ps.setInt(7, aoVivo.getVagas());
            } else {
                ps.setNull(5, Types.DATE);
                ps.setNull(6, Types.TIMESTAMP);
                ps.setNull(7, Types.INTEGER);
            }

            ps.setInt(8, c.getProfessor().getId());

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Curso c) {
        String sql = """
        UPDATE curso
        SET titulo=?, carga_horaria=?, preco_base=?, professor_id=?
        WHERE id=?
    """;

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getTitulo());
            ps.setInt(2, c.getCargaHoraria());
            ps.setDouble(3, c.getPrecoBase());
            ps.setInt(4, c.getProfessor().getId());
            ps.setInt(5, c.getId());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();

        String sql = "SELECT c.*, p.id as pid, p.nome, p.email, p.especialidade FROM curso c JOIN professor p ON c.professor_id = p.id";

        try (Connection con = Conexao.getConexao(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Professor professor = new Professor(
                        rs.getInt("pid"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("especialidade")
                );

                String modalidade = rs.getString("modalidade");

                if ("Gravado".equalsIgnoreCase(modalidade)) {

                    Date vencimento = rs.getDate("data_vencimento");

                    lista.add(new CursoGravado(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getInt("carga_horaria"),
                            rs.getDouble("preco_base"),
                            professor,
                            vencimento
                    ));
                } else if ("Ao Vivo".equalsIgnoreCase(modalidade)) {

                    Timestamp ts = rs.getTimestamp("data_aula");
                    LocalDateTime dataAula = ts.toLocalDateTime();

                    lista.add(new CursoAoVivo(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getInt("carga_horaria"),
                            rs.getDouble("preco_base"),
                            professor,
                            dataAula,
                            rs.getInt("vagas")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
