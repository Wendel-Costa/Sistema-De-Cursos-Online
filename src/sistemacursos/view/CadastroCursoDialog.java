package sistemacursos.view;

import sistemacursos.dao.CursoDAO;
import sistemacursos.dao.ProfessorDAO;
import sistemacursos.model.cursos.*;
import sistemacursos.model.usuarios.Professor;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Date;

public class CadastroCursoDialog extends JDialog {
    public CadastroCursoDialog() {
        setTitle("Cadastro de Curso");
        setSize(400, 400);
        setModal(true);
        setLocationRelativeTo(null);

        CursoDAO cursoDAO = new CursoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();

        JComboBox<String> cbTipo = new JComboBox<>(new String[]{
                "Curso Gravado", "Curso Ao Vivo", "Workshop"
        });

        JComboBox<Professor> cbProfessor = new JComboBox<>();
        professorDAO.listar().forEach(cbProfessor::addItem);

        JTextField txtTitulo = new JTextField();
        JTextField txtCarga = new JTextField();
        JTextField txtPreco = new JTextField();
        JTextField txtExtra = new JTextField();

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            Professor prof = (Professor) cbProfessor.getSelectedItem();

            int tipo = cbTipo.getSelectedIndex();

            if (tipo == 0) {
                cursoDAO.salvar(new CursoGravado(
                        0, txtTitulo.getText(),
                        Integer.parseInt(txtCarga.getText()),
                        Double.parseDouble(txtPreco.getText()),
                        prof, new Date()
                ));
            } else if (tipo == 1) {
                cursoDAO.salvar(new CursoAoVivo(
                        0, txtTitulo.getText(),
                        Integer.parseInt(txtCarga.getText()),
                        Double.parseDouble(txtPreco.getText()),
                        prof, LocalDateTime.now().plusDays(7),
                        Integer.parseInt(txtExtra.getText())
                ));
            } else {
                cursoDAO.salvar(new Workshop(
                        0, txtTitulo.getText(),
                        Double.parseDouble(txtPreco.getText()),
                        Integer.parseInt(txtExtra.getText()),
                        prof
                ));
            }
            dispose();
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Tipo"));
        add(cbTipo);
        add(new JLabel("Título"));
        add(txtTitulo);
        add(new JLabel("Carga Horária / Duração"));
        add(txtCarga);
        add(new JLabel("Preço"));
        add(txtPreco);
        add(new JLabel("Professor"));
        add(cbProfessor);
        add(new JLabel("Vagas / Duração Workshop"));
        add(txtExtra);
        add(btnSalvar);

        setVisible(true);
    }
}
