package sistemacursos.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import sistemacursos.dao.AlunoDAO;
import sistemacursos.dao.MatriculaDAO;
import sistemacursos.model.cursos.Curso;
import sistemacursos.model.usuarios.Aluno;

public class AlunoPanel extends JPanel {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final MatriculaDAO matriculaDAO = new MatriculaDAO();
    
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public AlunoPanel() {
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Email"}, 0
        );

        tabela = new JTable(modeloTabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel botoes = new JPanel();

        JButton btnCadastrar = new JButton("Cadastrar Aluno");
        JButton btnAtualizar = new JButton("Atualizar Lista");
        JButton btnVerCursos = new JButton("Ver Cursos");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        botoes.add(btnCadastrar);
        botoes.add(btnAtualizar);
        botoes.add(btnVerCursos);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);

        add(botoes, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> {
            new CadastroAlunoDialog();
            atualizarTabela();
        });

        btnAtualizar.addActionListener(e -> atualizarTabela());

        btnVerCursos.addActionListener(e -> verCursosAluno());

        btnEditar.addActionListener(e -> editarAluno());

        btnExcluir.addActionListener(e -> excluirAluno());

        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        for (Aluno a : alunoDAO.listar()) {
            modeloTabela.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getEmail()
            });
        }
    }

    private void verCursosAluno() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno");
            return;
        }

        int alunoId = (int) modeloTabela.getValueAt(linha, 0);
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

        JTable tabelaCursos = new JTable(dados, colunas);
        JOptionPane.showMessageDialog(this, new JScrollPane(tabelaCursos));
    }

    private void editarAluno() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno");
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);

        Aluno aluno = alunoDAO.listar()
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (aluno != null) {
            new EditarAlunoDialog(aluno);
            atualizarTabela();
        }
    }

    private void excluirAluno() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno");
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);
        String nome = (String) modeloTabela.getValueAt(linha, 1);

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Excluir o aluno " + nome + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            matriculaDAO.excluirPorAluno(id);
            alunoDAO.excluir(id);
            atualizarTabela();
        }
    }
}
