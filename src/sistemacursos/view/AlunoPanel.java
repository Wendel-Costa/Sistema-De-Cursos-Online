package sistemacursos.view;

import sistemacursos.dao.*;
import sistemacursos.model.cursos.Curso;
import sistemacursos.model.usuarios.Aluno;

import javax.swing.*;
import java.util.List;

public class AlunoPanel extends JPanel {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final MatriculaDAO matriculaDAO = new MatriculaDAO();

    public AlunoPanel() {
        JButton btnCadastrar = new JButton("Cadastrar Aluno");
        JButton btnListar = new JButton("Listar Alunos");
        JButton btnVerCursos = new JButton("Ver Cursos do Aluno");

        btnCadastrar.addActionListener(e -> new CadastroAlunoDialog());

        btnListar.addActionListener(e -> {
            List<Aluno> alunos = alunoDAO.listar();

            String[] colunas = {"ID", "Nome", "Email"};
            Object[][] dados = new Object[alunos.size()][3];

            for (int i = 0; i < alunos.size(); i++) {
                Aluno a = alunos.get(i);
                dados[i][0] = a.getId();
                dados[i][1] = a.getNome();
                dados[i][2] = a.getEmail();
            }

            JTable tabela = new JTable(dados, colunas);
            JOptionPane.showMessageDialog(this, new JScrollPane(tabela));
        });

        btnVerCursos.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID do aluno:");
            if (idStr == null) return;

            int alunoId = Integer.parseInt(idStr);
            List<Curso> cursos = matriculaDAO.listarCursosDoAluno(alunoId);

            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aluno não está matriculado em nenhum curso.");
                return;
            }

            String[] colunas = {"Curso", "Modalidade", "Professor"};
            Object[][] dados = new Object[cursos.size()][3];

            for (int i = 0; i < cursos.size(); i++) {
                Curso c = cursos.get(i);
                dados[i][0] = c.getTitulo();
                dados[i][1] = c.getModalidade();
                dados[i][2] = c.getProfessor().getNome();
            }

            JTable tabela = new JTable(dados, colunas);
            JOptionPane.showMessageDialog(this, new JScrollPane(tabela));
        });

        add(btnCadastrar);
        add(btnListar);
        add(btnVerCursos);
    }
}
