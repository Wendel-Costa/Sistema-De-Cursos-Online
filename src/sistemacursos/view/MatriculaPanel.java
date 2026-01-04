package sistemacursos.view;

import sistemacursos.dao.*;
import sistemacursos.model.cursos.Curso;
import sistemacursos.model.usuarios.Aluno;

import javax.swing.*;
import java.awt.event.HierarchyEvent;

public class MatriculaPanel extends JPanel {
    private final JComboBox<Aluno> cbAlunos = new JComboBox<>();
    private final JComboBox<Curso> cbCursos = new JComboBox<>();

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final CursoDAO cursoDAO = new CursoDAO();
    private final MatriculaDAO matriculaDAO = new MatriculaDAO();

    public MatriculaPanel() {

        JButton btnMatricular = new JButton("Matricular");

        btnMatricular.addActionListener(e -> {
            Aluno a = (Aluno) cbAlunos.getSelectedItem();
            Curso c = (Curso) cbCursos.getSelectedItem();

            if (a != null && c != null) {
                matriculaDAO.matricular(a.getId(), c.getId());
                JOptionPane.showMessageDialog(this, "Aluno matriculado com sucesso!");
            }
        });

        add(new JLabel("Aluno:"));
        add(cbAlunos);
        add(new JLabel("Curso:"));
        add(cbCursos);
        add(btnMatricular);

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                recarregarDados();
            }
        });
    }

    private void recarregarDados() {
        cbAlunos.removeAllItems();
        cbCursos.removeAllItems();

        alunoDAO.listar().forEach(cbAlunos::addItem);
        cursoDAO.listar().forEach(cbCursos::addItem);
    }
}
