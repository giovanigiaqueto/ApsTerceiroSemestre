package graphic;

// java.util
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.function.Predicate;

// dao
import dao.ExemplarDAO;

// model
import model.Livro;
import model.Exemplar;

// widget
import widget.dados.JDadosLivro;
import widget.listas.JListaLivros;
import widget.dados.JDadosExemplar;
import widget.listas.JListaExemplares;
import widget.support.IComponenteLivro;

// suporte
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

        jButtonVoltar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jListaLivros = new widget.listas.JListaLivros();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jListaExemplares = new widget.listas.JListaExemplares();

        jButtonVoltar.setText("Voltar");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jListaLivros.setPreferredSize(new java.awt.Dimension(500, 500));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jListaLivros, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        jPanel1.add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
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
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 0.1;
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
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jListaExemplares, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JButton jButtonVoltar;
    private widget.listas.JListaExemplares jListaExemplares;
    private widget.listas.JListaLivros jListaLivros;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
