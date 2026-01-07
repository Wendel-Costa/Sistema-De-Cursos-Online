package sistemacursos.view;

import java.util.List;
import javax.swing.*;
import sistemacursos.dao.CursoDAO;
import sistemacursos.model.cursos.Curso;

public class CursoPanel extends JPanel {

    private final CursoDAO cursoDAO = new CursoDAO();

    public CursoPanel() {
        JButton btnCadastrar = new JButton("Cadastrar Curso");
        JButton btnListar = new JButton("Listar Cursos");
        JButton btnExcluir = new JButton("Excluir Curso");
        JButton btnEditar = new JButton("Editar Curso");

        btnCadastrar.addActionListener(e
                -> new CadastroCursoDialog()
        );

        btnListar.addActionListener(e -> {
            List<Curso> cursos = cursoDAO.listar();

            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum curso cadastrado.");
                return;
            }

            String[] colunas = {"ID", "Título", "Modalidade", "Professor"};
            Object[][] dados = new Object[cursos.size()][4];

            for (int i = 0; i < cursos.size(); i++) {
                Curso c = cursos.get(i);
                dados[i][0] = c.getId();
                dados[i][1] = c.getTitulo();
                dados[i][2] = c.getModalidade();
                dados[i][3] = c.getProfessor().getNome();
            }

            JTable tabela = new JTable(dados, colunas);
            JOptionPane.showMessageDialog(this, new JScrollPane(tabela));
        });

        btnExcluir.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID do curso:");
            if (idStr != null) {
                cursoDAO.excluir(Integer.parseInt(idStr));
                JOptionPane.showMessageDialog(this, "Curso excluído.");
            }
        });

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID do curso:");
            if (idStr == null) {
                return;
            }

            int id = Integer.parseInt(idStr);
            Curso curso = cursoDAO.listar()
                    .stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (curso != null) {
                new EditarCursoDialog(curso);
            }
        });

        add(btnCadastrar);
        add(btnListar);
        add(btnExcluir);
        add(btnEditar);
    }
}
