package sistemacursos.view;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Sistema de Cursos Online");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.add("Cursos", new CursoPanel());
        abas.add("Professores", new ProfessorPanel());
        abas.add("Alunos", new AlunoPanel());
        abas.add("Matrículas", new MatriculaPanel());

        add(abas);
        setVisible(true);
    }
}
