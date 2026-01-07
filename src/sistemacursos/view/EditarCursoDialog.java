package sistemacursos.view;

import sistemacursos.dao.CursoDAO;
import sistemacursos.dao.ProfessorDAO;
import sistemacursos.model.cursos.Curso;
import sistemacursos.model.usuarios.Professor;

import javax.swing.*;

public class EditarCursoDialog extends JDialog {
    public EditarCursoDialog(Curso curso) {
        setTitle("Editar Curso");
        setSize(350, 300);
        setModal(true);
        setLocationRelativeTo(null);

        JTextField txtTitulo = new JTextField(curso.getTitulo(), 15);
        JTextField txtCarga = new JTextField(String.valueOf(curso.getCargaHoraria()), 5);
        JTextField txtPreco = new JTextField(String.valueOf(curso.getPrecoBase()), 5);

        JComboBox<Professor> cbProfessor = new JComboBox<>();
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.listar().forEach(cbProfessor::addItem);
        cbProfessor.setSelectedItem(curso.getProfessor());

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            curso.setTitulo(txtTitulo.getText());
            curso.setCargaHoraria(Integer.parseInt(txtCarga.getText()));
            curso.setPrecoBase(Double.parseDouble(txtPreco.getText()));
            curso.setProfessor((Professor) cbProfessor.getSelectedItem());

            new CursoDAO().atualizar(curso);
            dispose();
        });

        add(new JLabel("Título:"));
        add(txtTitulo);
        add(new JLabel("Carga Horária:"));
        add(txtCarga);
        add(new JLabel("Preço Base:"));
        add(txtPreco);
        add(new JLabel("Professor:"));
        add(cbProfessor);
        add(btnSalvar);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }
}
