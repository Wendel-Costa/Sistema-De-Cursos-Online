package sistemacursos.dao;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void inicializar() {
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS professor (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL,
                    especialidade TEXT
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS aluno (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS curso (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT,
                    carga_horaria INTEGER,
                    preco_base REAL,
                    modalidade TEXT,
                    data_vencimento DATE,
                    data_aula DATETIME,
                    vagas INTEGER,
                    professor_id INTEGER,
                    FOREIGN KEY (professor_id) REFERENCES professor(id)
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS workshop (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tema TEXT,
                    preco REAL,
                    duracao_horas INTEGER,
                    professor_id INTEGER,
                    FOREIGN KEY (professor_id) REFERENCES professor(id)
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS aluno_curso (
                    aluno_id INTEGER,
                    curso_id INTEGER,
                    PRIMARY KEY (aluno_id, curso_id),
                    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
                    FOREIGN KEY (curso_id) REFERENCES curso(id)
                );
            """);

            System.out.println("Banco de dados inicializado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inicializar banco:");
            e.printStackTrace();
        }
    }
}
