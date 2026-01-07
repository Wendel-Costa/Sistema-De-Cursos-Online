package sistemacursos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sistemacursos.model.usuarios.Professor;

public class ProfessorDAO {

    public void salvar(Professor p) {
        String sql = "INSERT INTO professor (nome, email, especialidade) VALUES (?, ?, ?)";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getEspecialidade());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Professor p) {
        String sql = "UPDATE professor SET nome=?, email=?, especialidade=? WHERE id=?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getEspecialidade());
            ps.setInt(4, p.getId());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM professor WHERE id = ?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Professor> listar() {
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professor";

        try (Connection con = Conexao.getConexao(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("especialidade")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
