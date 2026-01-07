package sistemacursos.view;

import java.util.List;
import javax.swing.*;
import sistemacursos.dao.ProfessorDAO;
import sistemacursos.model.usuarios.Professor;

public class ProfessorPanel extends JPanel {
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public ProfessorPanel() {
        JButton btnCadastrar = new JButton("Cadastrar Professor");
        JButton btnListar = new JButton("Listar Professores");
        JButton btnEditar = new JButton("Editar Professor");

        btnCadastrar.addActionListener(e
                -> new CadastroProfessorDialog()
        );

        btnListar.addActionListener(e -> {
            List<Professor> lista = professorDAO.listar();

            String[] col = {"ID", "Nome", "Email", "Especialidade"};
            Object[][] dados = new Object[lista.size()][4];

            for (int i = 0; i < lista.size(); i++) {
                dados[i][0] = lista.get(i).getId();
                dados[i][1] = lista.get(i).getNome();
                dados[i][2] = lista.get(i).getEmail();
                dados[i][3] = lista.get(i).getEspecialidade();
            }

            JTable tabela = new JTable(dados, col);
            JOptionPane.showMessageDialog(this, new JScrollPane(tabela));
        });

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID do professor:");
            if (idStr == null) {
                return;
            }

            int id = Integer.parseInt(idStr);
            Professor prof = professorDAO.listar()
                    .stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (prof != null) {
                new EditarProfessorDialog(prof);
            }
        });

        add(btnCadastrar);
        add(btnListar);
        add(btnEditar);
    }
}
