package sistemacursos.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import sistemacursos.dao.ProfessorDAO;
import sistemacursos.model.usuarios.Professor;

public class ProfessorPanel extends JPanel {
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ProfessorPanel() {
        setLayout(new BorderLayout());
        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Email", "Especialidade"}, 0
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
            new CadastroProfessorDialog();
            atualizarTabela();
        });

        btnAtualizar.addActionListener(e -> atualizarTabela());

        btnEditar.addActionListener(e -> editarProfessor());

        btnExcluir.addActionListener(e -> excluirProfessor());

        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        for (Professor p : professorDAO.listar()) {
            modeloTabela.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getEmail(),
                    p.getEspecialidade()
            });
        }
    }

    private void editarProfessor() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor");
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);

        Professor prof = professorDAO.listar()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (prof != null) {
            new EditarProfessorDialog(prof);
            atualizarTabela();
        }
    }

    private void excluirProfessor() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor");
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);
        String nome = (String) modeloTabela.getValueAt(linha, 1);

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Excluir o professor " + nome + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            professorDAO.excluir(id);
            atualizarTabela();
        }
    }
}
