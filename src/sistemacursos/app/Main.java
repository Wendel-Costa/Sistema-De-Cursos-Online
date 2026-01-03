package sistemacursos.app;

import sistemacursos.dao.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.inicializar();

        System.out.println("Aplicação iniciada com sucesso.");
    }
}
