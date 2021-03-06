package widget.support.livro;

import widget.dados.JDadosLivro;
import model.Livro;

public class JLivroEstendido extends javax.swing.JPanel {
    
    private JDadosLivro mestre;
    
    /**
     * Creates new form DadosLivroExtendido
     */
    public JLivroEstendido() {
        initComponents();
    }
    
    public void setConteudo(Livro livro) {
        jLabelEdicaoPaginas.setText(
            livro.getEdicaoLivro() + "ª edição, " + 
            String.valueOf(livro.getPaginasLivro()) + " páginas"
        );
        jLabelEditora.setText(livro.getEditoraLivro());
        jLabelValorEstoque.setText(
            String.valueOf(livro.getLocacaoLivro()) + '/' +
            String.valueOf(livro.getEstoqueLivro())
        );
        jLabelNomeAutor.setText(livro.getAutorLivro());
        jLabelValorPreco.setText(String.valueOf(livro.getPrecoLivro()) + "R$");
        
        jTextAreaSinopse.setText(livro.getSinopseLivro());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNomeAutor = new javax.swing.JLabel();
        jLabelEditora = new javax.swing.JLabel();
        jLabelCampoEstoque = new javax.swing.JLabel();
        jLabelValorEstoque = new javax.swing.JLabel();
        jLabelCampoPreco = new javax.swing.JLabel();
        jLabelValorPreco = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSinopse = new javax.swing.JTextArea();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 78), new java.awt.Dimension(0, 78), new java.awt.Dimension(32767, 78));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabelEdicaoPaginas = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(500, 0));
        setPreferredSize(new java.awt.Dimension(600, 300));

        jLabelNomeAutor.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNomeAutor.setText("Nome Completo do Autor");

        jLabelEditora.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelEditora.setText("Editora - Ano");

        jLabelCampoEstoque.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelCampoEstoque.setText("Estoque:");

        jLabelValorEstoque.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelValorEstoque.setText("XX/YY");

        jLabelCampoPreco.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelCampoPreco.setText("Preço:");

        jLabelValorPreco.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelValorPreco.setText("XYZ.W R$");

        jScrollPane1.setMinimumSize(new java.awt.Dimension(576, 78));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(576, 78));

        jTextAreaSinopse.setColumns(20);
        jTextAreaSinopse.setLineWrap(true);
        jTextAreaSinopse.setRows(5);
        jTextAreaSinopse.setWrapStyleWord(true);
        jTextAreaSinopse.setPreferredSize(new java.awt.Dimension(777, 75));
        jScrollPane1.setViewportView(jTextAreaSinopse);

        jLabelEdicaoPaginas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelEdicaoPaginas.setText("1ª edição, 125 páginas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelEditora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEdicaoPaginas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelCampoPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCampoEstoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelValorPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelValorEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelNomeAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filler3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addGap(249, 249, 249))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNomeAutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelCampoEstoque)
                                    .addComponent(jLabelValorEstoque))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCampoPreco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelValorPreco)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelEditora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelEdicaoPaginas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabelCampoEstoque;
    private javax.swing.JLabel jLabelCampoPreco;
    private javax.swing.JLabel jLabelEdicaoPaginas;
    private javax.swing.JLabel jLabelEditora;
    private javax.swing.JLabel jLabelNomeAutor;
    private javax.swing.JLabel jLabelValorEstoque;
    private javax.swing.JLabel jLabelValorPreco;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaSinopse;
    // End of variables declaration//GEN-END:variables
}
