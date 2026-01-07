package sistemacursos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sistemacursos.model.usuarios.Aluno;

public class AlunoDAO {

    public void salvar(Aluno a) {
        String sql = "INSERT INTO aluno (nome, email) VALUES (?, ?)";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNome());
            ps.setString(2, a.getEmail());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Aluno a) {
        String sql = "UPDATE aluno SET nome=?, email=? WHERE id=?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNome());
            ps.setString(2, a.getEmail());
            ps.setInt(3, a.getId());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection con = Conexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try (Connection con = Conexao.getConexao(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
