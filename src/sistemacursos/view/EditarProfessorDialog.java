package sistemacursos.view;

import sistemacursos.dao.ProfessorDAO;
import sistemacursos.model.usuarios.Professor;

import javax.swing.*;

public class EditarProfessorDialog extends JDialog {
    public EditarProfessorDialog(Professor professor) {
        setTitle("Editar Professor");
        setSize(300, 250);
        setModal(true);
        setLocationRelativeTo(null);

        JTextField txtNome = new JTextField(professor.getNome(), 15);
        JTextField txtEmail = new JTextField(professor.getEmail(), 15);
        JTextField txtEsp = new JTextField(professor.getEspecialidade(), 15);

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            professor.setNome(txtNome.getText());
            professor.setEmail(txtEmail.getText());
            professor.setEspecialidade(txtEsp.getText());

            new ProfessorDAO().atualizar(professor);
            dispose();
        });

        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Especialidade:"));
        add(txtEsp);
        add(btnSalvar);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }
}
