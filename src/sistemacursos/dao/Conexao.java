package sistemacursos.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = "jdbc:sqlite:sistemacursos.db";

    public static Connection getConexao() throws Exception {
        return DriverManager.getConnection(URL);
    }
}
