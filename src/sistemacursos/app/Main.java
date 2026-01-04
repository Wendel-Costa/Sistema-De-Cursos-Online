package sistemacursos.app;

import sistemacursos.dao.DatabaseInitializer;
import sistemacursos.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.inicializar();
        new MainFrame();
    }
}
