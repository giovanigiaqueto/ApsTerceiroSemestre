/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic.cadastro;

import dao.ExemplarDAO;
import dao.LivroDAO;
import internal.JMain;
import internal.Main;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Exemplar;
import model.Livro;

// suporte
import widget.support.IPanelCRUD;

/**
 *
 * @author giovani
 */
public class JCadastroExemplar extends javax.swing.JPanel implements IPanelCRUD {

    @Override
    public boolean mostrarComoPopup() { return true; }
    
    @Override
    public String getTituloCRUD() { return "Cadastrar Exemplar"; }
    
    /**
     * Creates new form JCadastroExemplar
     */
    public JCadastroExemplar() {
        initComponents();
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

        jLabel1 = new javax.swing.JLabel();
        jPanelMargem = new javax.swing.JPanel();
        jPanelForm = new javax.swing.JPanel();
        jTextFieldISBN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldDataObtencao = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFieldQuantidade = new javax.swing.JFormattedTextField();
        jPanelButtons = new javax.swing.JPanel();
        fillerLeft = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        fillerCenter = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        fillerRight = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButtonConcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        fillerTop = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        fillerBottom = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setMinimumSize(new java.awt.Dimension(283, 291));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro Exemplar");

        jPanelMargem.setLayout(new java.awt.GridBagLayout());

        jPanelForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldISBN.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldISBN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldISBN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldISBNKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ISBN");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setText("Data  de Compra");

        try {
            jFormattedTextFieldDataObtencao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataObtencao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldDataObtencao.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jFormattedTextFieldDataObtencao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldDataObtencaoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Qtd.");

        jFormattedTextFieldQuantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldQuantidade.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jFormattedTextFieldQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldQuantidadeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldDataObtencao, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldQuantidade)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldISBN))
                .addContainerGap())
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldDataObtencao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelMargem.add(jPanelForm, gridBagConstraints);

        jPanelButtons.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanelButtons.add(fillerLeft, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanelButtons.add(fillerCenter, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanelButtons.add(fillerRight, gridBagConstraints);

        jButtonConcluir.setText("Concluir");
        jButtonConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConcluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanelButtons.add(jButtonConcluir, gridBagConstraints);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanelButtons.add(jButtonCancelar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.2;
        jPanelButtons.add(fillerTop, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.2;
        jPanelButtons.add(fillerBottom, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMargem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelMargem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextFieldDataObtencaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataObtencaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDataObtencaoActionPerformed

    private void jFormattedTextFieldQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantidadeKeyTyped
        String charsPermitidos = "0123456789";
        if(!charsPermitidos.contains(evt.getKeyChar()+""))
            evt.consume();
    }//GEN-LAST:event_jFormattedTextFieldQuantidadeKeyTyped

    private void jButtonConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConcluirActionPerformed
        ExemplarDAO exemplarDao = new ExemplarDAO();

        String isbn = jTextFieldISBN.getText();
        String dataObtencao = jFormattedTextFieldDataObtencao.getText();
        String quantidade = jFormattedTextFieldQuantidade.getText();
        
        if(Main.validaISBN(isbn) &&
            !dataObtencao.equals("  /  /    ") &&
            !quantidade.isEmpty()) {

            LivroDAO livroDAO = new LivroDAO();
            Livro livro = livroDAO.procurarPorISBN(isbn.replace("-", ""));
            if (livro != null) {

                for (int i = 0; i < Integer.parseInt(quantidade); i++) {
                    Exemplar exemplar = new Exemplar();
                    exemplar.setIdExemplarLivro(livro.getIdLivro());
                    exemplar.setEstaAlocado(false);
                    exemplar.setDataObtencao(Main.formataData(dataObtencao));

                    exemplarDao.salvar(exemplar);
                }

                JOptionPane.showMessageDialog(new JFrame(), 
                    "Todos os " + quantidade + " exemplares foram cadastrados com sucesso!",
                        "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                
                JMain.getInstancia().popJanelaCRUD();
                
            } else {
                JOptionPane.showMessageDialog(new JFrame(), 
                    "N??o foi poss??vel salvar os exemplares!",
                        "Algo deu errado!", JOptionPane.ERROR_MESSAGE);
            }
        }

        lembreteCamposEmBranco();
    }//GEN-LAST:event_jButtonConcluirActionPerformed

    private void jTextFieldISBNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldISBNKeyTyped
        String charsPermitidos = "0123456789-";
        if(!charsPermitidos.contains(evt.getKeyChar()+"") ||
                jTextFieldISBN.getText().length() >= 17)//13 n??meros + 4 tra????s no m??ximo = 17 caracteres
            evt.consume();
    }//GEN-LAST:event_jTextFieldISBNKeyTyped

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        JMain.getInstancia().popJanelaCRUD();
    }//GEN-LAST:event_jButtonCancelarActionPerformed
    
    /**
     * Coloca a cor dos componentes para uma cor vermelho claro 
     * para mostrar que o usuario n??o os preencheu
     */
    private void lembreteCamposEmBranco(){
        Color cor = new Color(248, 220, 219);

        if(!Main.validaISBN(jTextFieldISBN.getText()))
            jTextFieldISBN.setBackground(cor);
        else
            jTextFieldISBN.setBackground(Color.WHITE);

        if(jFormattedTextFieldDataObtencao.getText().equals("  /  /    "))
            jFormattedTextFieldDataObtencao.setBackground(cor);
        else
            jFormattedTextFieldDataObtencao.setBackground(Color.WHITE);

        if(jFormattedTextFieldQuantidade.getText().isEmpty())
            jFormattedTextFieldQuantidade.setBackground(cor);
        else
            jFormattedTextFieldQuantidade.setBackground(Color.WHITE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler fillerBottom;
    private javax.swing.Box.Filler fillerCenter;
    private javax.swing.Box.Filler fillerLeft;
    private javax.swing.Box.Filler fillerRight;
    private javax.swing.Box.Filler fillerTop;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConcluir;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataObtencao;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JPanel jPanelMargem;
    private javax.swing.JTextField jTextFieldISBN;
    // End of variables declaration//GEN-END:variables
}
