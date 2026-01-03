package sistemacursos.app;

import sistemacursos.dao.Conexao;
import java.sql.Connection;

public class TesteSQLite {
    public static void main(String[] args) {
        try {
            Connection con = Conexao.getConexao();
            System.out.println("Conectou com SQLite!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
