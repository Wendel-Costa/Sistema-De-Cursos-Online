package sistemacursos.view;

import sistemacursos.dao.ProfessorDAO;
import sistemacursos.model.usuarios.Professor;

import javax.swing.*;

public class CadastroProfessorDialog extends JDialog {
    public CadastroProfessorDialog() {
        setTitle("Cadastro de Professor");
        setSize(300, 250);
        setModal(true);
        setLocationRelativeTo(null);

        JTextField txtNome = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtEsp = new JTextField();

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            new ProfessorDAO().salvar(
                    new Professor(0, txtNome.getText(), txtEmail.getText(), txtEsp.getText())
            );
            dispose();
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Nome"));
        add(txtNome);
        add(new JLabel("Email"));
        add(txtEmail);
        add(new JLabel("Especialidade"));
        add(txtEsp);
        add(btnSalvar);

        setVisible(true);
    }
}
