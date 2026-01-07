package sistemacursos.view;

import sistemacursos.dao.AlunoDAO;
import sistemacursos.model.usuarios.Aluno;

import javax.swing.*;

public class EditarAlunoDialog extends JDialog {

    public EditarAlunoDialog(Aluno aluno) {

        setTitle("Editar Aluno");
        setSize(300, 200);
        setModal(true);
        setLocationRelativeTo(null);

        JTextField txtNome = new JTextField(aluno.getNome(), 15);
        JTextField txtEmail = new JTextField(aluno.getEmail(), 15);

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            aluno.setNome(txtNome.getText());
            aluno.setEmail(txtEmail.getText());

            new AlunoDAO().atualizar(aluno);
            dispose();
        });

        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(btnSalvar);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }
}
