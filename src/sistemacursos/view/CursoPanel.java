package sistemacursos.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import sistemacursos.dao.CursoDAO;
import sistemacursos.dao.MatriculaDAO;
import sistemacursos.model.cursos.Curso;

public class CursoPanel extends JPanel {
    private final CursoDAO cursoDAO = new CursoDAO();
    private final MatriculaDAO matriculaDAO = new MatriculaDAO();

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public CursoPanel() {
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Título", "Modalidade", "Professor"}, 0
        );

        tabela = new JTable(modeloTabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel botoes = new JPanel();

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        botoes.add(btnCadastrar);
        botoes.add(btnAtualizar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);

        add(botoes, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> {
            new CadastroCursoDialog();
            atualizarTabela();
        });

        btnAtualizar.addActionListener(e -> atualizarTabela());

        btnEditar.addActionListener(e -> editarCurso());

        btnExcluir.addActionListener(e -> excluirCurso());

        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        for (Curso c : cursoDAO.listar()) {
            modeloTabela.addRow(new Object[]{
                c.getId(),
                c.getTitulo(),
                c.getModalidade(),
                c.getProfessor().getNome()
            });
        }
    }

    private void editarCurso() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso");
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);

        Curso curso = cursoDAO.listar()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (curso != null) {
            new EditarCursoDialog(curso);
            atualizarTabela();
        }
    }

    private void excluirCurso() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso");
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);
        String titulo = (String) modeloTabela.getValueAt(linha, 1);

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Excluir o curso \"" + titulo + "\"?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            matriculaDAO.excluirPorCurso(id);
            cursoDAO.excluir(id);
            atualizarTabela();
        }
    }
}
