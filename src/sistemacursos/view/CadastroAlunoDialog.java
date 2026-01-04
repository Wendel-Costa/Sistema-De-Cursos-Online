package sistemacursos.view;

import sistemacursos.dao.AlunoDAO;
import sistemacursos.model.usuarios.Aluno;

import javax.swing.*;

public class CadastroAlunoDialog extends JDialog {
    public CadastroAlunoDialog() {
        setTitle("Cadastro de Aluno");
        setSize(300, 200);
        setModal(true);
        setLocationRelativeTo(null);

        JTextField txtNome = new JTextField();
        JTextField txtEmail = new JTextField();
        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            new AlunoDAO().salvar(new Aluno(0, txtNome.getText(), txtEmail.getText()));
            dispose();
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Nome"));
        add(txtNome);
        add(new JLabel("Email"));
        add(txtEmail);
        add(btnSalvar);

        setVisible(true);
    }
}
