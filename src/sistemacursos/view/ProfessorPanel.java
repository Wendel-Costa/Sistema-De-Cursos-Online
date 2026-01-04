package sistemacursos.view;

import sistemacursos.dao.ProfessorDAO;
import sistemacursos.model.usuarios.Professor;

import javax.swing.*;
import java.util.List;

public class ProfessorPanel extends JPanel {

    private final ProfessorDAO dao = new ProfessorDAO();

    public ProfessorPanel() {

        JButton btnCadastrar = new JButton("Cadastrar Professor");
        JButton btnListar = new JButton("Listar Professores");

        btnCadastrar.addActionListener(e ->
                new CadastroProfessorDialog()
        );

        btnListar.addActionListener(e -> {
            List<Professor> lista = dao.listar();

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

        add(btnCadastrar);
        add(btnListar);
    }
}
