package graphic;

// java swing
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

// java.util
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.function.Predicate;

// java.time
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// dao
import dao.LivroDAO;
import dao.ExemplarDAO;
import dao.EmprestimoDAO;

// model
import model.Usuario;
import model.Cliente;
import model.Livro;
import model.Exemplar;
import model.Emprestimo;

// widget
import widget.dados.JDadosLivro;
import widget.listas.JListaLivros;
import widget.dados.JDadosExemplar;
import widget.listas.JListaExemplares;
import widget.support.IComponenteLivro;

// suporte
import internal.JMain;
import widget.support.IPanelCRUD;

public class CheckoutJPanel extends javax.swing.JPanel implements IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Checkout"; }
    
    private IComponenteLivro iLivroSelecionado;
    private Map<Integer, Exemplar> exemplares;
    
    /**
     * Creates new form CheckoutJPanel
     */
    public CheckoutJPanel() {
        initComponents();
        init();
    }
    
    {
        exemplares = new TreeMap<>();
    }
    
    private void init() {
        jListaLivros.setObservarSelecao(true);
        jListaLivros.addObservadorSelecao(
            new JListaLivros.ObservadorSelecao() {
                @Override
                public void selecao(IComponenteLivro iLivro) {
                    
                    // evita seleção de nada (desseleção)
                    if (iLivro == null) return;
                    
                    // seleção do livro
                    Livro livro = iLivro.getLivro();
                    iLivroSelecionado = iLivro;
                    
                    // carregamento dos exemplares
                    __innerCarregarExemplares(livro.getIdLivro());
                }
            }
        );
        
        jListaExemplares.setObservarSelecao(true);
        jListaExemplares.addObservadorSelecao(
            new JListaExemplares.ObservadorSelecao() {
                @Override
                public void selecao(JDadosExemplar exemplar) {
                    
                    // evita seleção de nada (desseleção)
                    if (exemplar == null) return;
                    
                    IComponenteLivro iLivro = iLivroSelecionado;
                    if (iLivro != null) {
                        int id_livro = iLivro.getLivro().getIdLivro();
                        /* // DEBUG START
                        System.out.println("Checkout seleção exemplar:" +
                            id_livro + ", " + exemplar.getExemplar().getIdExemplar());
                        */ // DEBUG END
                        exemplares.put(id_livro, exemplar.getExemplar());
                    }
                }
            }
        );
    }
    
    /**
     * função interna, carrega os exemplares do banco
     * pertencentes ao livro de id idLivro
     * 
     * @param idLivro o id do livro para carregar os exemplares 
     */
    private void __innerCarregarExemplares(int idLivro) {
        
        // carregamento dos exemplares do banco
        ExemplarDAO dao = new ExemplarDAO();
        List<Exemplar> lista = dao.listarExemplaresLivro(idLivro);
        
        // filtragem (remoção de exemplares já alocados)
        lista.removeIf(new Predicate() {
            @Override
            public boolean test(Object obj) {
                return !((Exemplar) obj).getEstaAlocado();
            }
        });
        
        // substituição dos exemplares (esvaziamento seguido de inserção)
        jListaExemplares.esvaziar();
        jListaExemplares.inserirExemplares(lista);
        
        // seleção do exemplar (se houver)
        Exemplar exemplar = exemplares.get(idLivro);
        if (exemplar != null) {
            jListaExemplares.selecionar(exemplar.getIdExemplar());
        }
    }
    
    public void inserirLivros(List<Livro> livros) {
        jListaLivros.inserirLivros(livros, JDadosLivro.class);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jListaLivros = new widget.listas.JListaLivros();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jListaExemplares = new widget.listas.JListaExemplares();
        jPanelConcluir = new javax.swing.JPanel();
        jButtonConcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jListaLivros.setPreferredSize(new java.awt.Dimension(500, 500));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 4.0;
        jPanel1.add(jListaLivros, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        jPanel1.add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.4;
        jPanel1.add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.2;
        jPanel1.add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 0.05;
        jPanel1.add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        jPanel1.add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 4.0;
        jPanel1.add(jListaExemplares, gridBagConstraints);

        jPanelConcluir.setMaximumSize(new java.awt.Dimension(304, 49));
        jPanelConcluir.setMinimumSize(new java.awt.Dimension(304, 49));

        jButtonConcluir.setText("Concluir");
        jButtonConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConcluirActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConcluirLayout = new javax.swing.GroupLayout(jPanelConcluir);
        jPanelConcluir.setLayout(jPanelConcluirLayout);
        jPanelConcluirLayout.setHorizontalGroup(
            jPanelConcluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConcluirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelConcluirLayout.setVerticalGroup(
            jPanelConcluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConcluirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConcluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonConcluir))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weighty = 0.2;
        jPanel1.add(jPanelConcluir, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.05;
        jPanel1.add(filler6, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        JMain main = JMain.getInstancia();
        if (main != null) { main.popJanelaCRUD(); }
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConcluirActionPerformed
        JMain main = JMain.getInstancia();
        if (main != null) {
            
            // tenta conetar com o banco
            EmprestimoDAO daoEmprestimo;
            ExemplarDAO daoExemplar;
            LivroDAO daoLivro;
            try {
                daoEmprestimo = new EmprestimoDAO();
                daoExemplar = new ExemplarDAO();
                daoLivro = new LivroDAO();
            } catch(RuntimeException e) {
                
                JOptionPane.showMessageDialog(new JFrame(),
                    "não foi possível conectar com o banco de dados,\n" +
                    "o empréstimo não pode ser concluido",
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
                
                main.popJanelaCRUD();
                
                throw e;
            }
            
            // verifica se foram selecionados exemplares para todos os livros,
            // se o livro existe (isso não deve acontecer, mas é melhor previnir)
            // e soma o valor total do empréstimo
            double valor = 0;
            for (Map.Entry<Integer, Exemplar> entry : exemplares.entrySet()) {
                
                Livro livro;
                try {
                    livro = daoLivro.procurarLivro(entry.getKey());
                    valor += livro.getPrecoLivro();
                } catch(RuntimeException e) {
                    
                    JOptionPane.showMessageDialog(new JFrame(),
                        "o livro de ID " + entry.getKey() +
                        " não pode ser encontrado no sistema,\n" + 
                        "o emprestimo será cancelado",
                        "Erro - Livro Inexistente",
                        JOptionPane.ERROR_MESSAGE);
                    
                    throw e;
                }
                
                if (entry.getValue() == null) {
                    
                    JOptionPane.showMessageDialog(new JFrame(),
                        "o exemplar para o livro " + livro.getNomeLivro() +
                        " não foi selecionado", 
                        "Erro - Exemplar Não Selecionado",
                        JOptionPane.ERROR_MESSAGE);
                    
                    main.popJanelaCRUD();
                    return;
                }
            }
            
            while (true) {
                int v = JOptionPane.showConfirmDialog(new JFrame(), 
                    "preço total: " + valor, "Confirmar", JOptionPane.YES_NO_OPTION);
                
                if (v == JOptionPane.YES_OPTION) break;
                else {
                    v = JOptionPane.showConfirmDialog(new JFrame(),
                        "tem certeza que quer cancelar o empréstimo?", 
                        "Confirmar", JOptionPane.YES_NO_OPTION);
                    
                    if (v == JOptionPane.YES_OPTION) {
                        main.popJanelaCRUD();
                        return;
                    }
                }
            }
            
            Usuario usuario = main.usuario;
            Cliente cliente = main.cliente;
            if (usuario == null) {
                
                JOptionPane.showMessageDialog(new JFrame(),
                    "nenhum usuário está conectado atualmente," +
                    "conecte a um usuário e tente novamente", 
                    "Erro - Usuário Não Conectado",
                    JOptionPane.ERROR_MESSAGE);
                
                main.popJanelaCRUD();
                return;
            }
            if (cliente == null) {
                
                JOptionPane.showMessageDialog(new JFrame(),
                    "nenhum usuário está conectado atualmente," +
                    "conecte a um usuário e tente novamente", 
                    "Erro - Usuário Não Conectado",
                    JOptionPane.ERROR_MESSAGE);
                
                main.popJanelaCRUD();
                return;
            }
            
            String dataEmprestimo =
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
            
            final JComponent[] inputs = new JComponent[] { 
                new JLabel("Data de Devolução"), new JTextField()
            };
        
            int resultado = JOptionPane.showConfirmDialog(null, inputs,
                "Data (ano/mês/dia)", JOptionPane.PLAIN_MESSAGE);
            
            if (resultado != JOptionPane.OK_OPTION) {
                main.popJanelaCRUD();
                return;
            }
            String dataDevolucao = main.formataData(((JTextField) inputs[1]).getText());
            
            for (Map.Entry<Integer, Exemplar> entry : exemplares.entrySet()) {
                
                Exemplar exemplar = entry.getValue();
                
                try {
                    Emprestimo emprestimo = 
                        new Emprestimo(0, cliente.getIdCliente(), usuario.getIdUsuario(),
                            entry.getValue().getIdExemplar(), dataEmprestimo, dataDevolucao);
                    
                    // aloca o exemplar
                    exemplar.setEstaAlocado(true);
                    daoExemplar.alterar(exemplar);
                    
                    // salva o emprestimo
                    daoEmprestimo.salvar(emprestimo);
                    
                } catch(RuntimeException e) {
                    JOptionPane.showMessageDialog(new JFrame(),
                        "alguns exemplares não puderam ser emprestados,\n" +
                        "o empréstimo será cancelado devido a esse erro\n" +
                        "chame um técnico para concertar o problema",
                        "Erro - estado inconsistente encontrado",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
            main.popJanelaCRUD();
        }
    }//GEN-LAST:event_jButtonConcluirActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConcluir;
    private widget.listas.JListaExemplares jListaExemplares;
    private widget.listas.JListaLivros jListaLivros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelConcluir;
    // End of variables declaration//GEN-END:variables
}
