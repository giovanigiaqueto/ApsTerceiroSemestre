/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

/**
 *
 * @author giovani
 */
public class ContaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form JTeste
     */
    public ContaJPanel() {
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

        jButtonVoltar = new javax.swing.JButton();
        jPanelMain = new javax.swing.JPanel();
        fillerMargemEsquerda = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        fillerMargemDireita = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        fillerMargemSuperior = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        fillerMargemInferior = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        fillerMargemCentro = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabelConta = new javax.swing.JLabel();
        fillerMargemConta = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanelConta = new javax.swing.JPanel();
        jPanelValoresConta = new javax.swing.JPanel();
        jLabelCampoNome = new javax.swing.JLabel();
        jLabelCampoEmail = new javax.swing.JLabel();
        jLabelCampoRua = new javax.swing.JLabel();
        jLabelCampoBairro = new javax.swing.JLabel();
        jLabelCampoCEP = new javax.swing.JLabel();
        jLabelCampoNumero = new javax.swing.JLabel();
        jLabelValorNome = new javax.swing.JLabel();
        jLabelValorEmail = new javax.swing.JLabel();
        jLabelValorRua = new javax.swing.JLabel();
        jLabelValorBairro = new javax.swing.JLabel();
        jLabelValorCEP = new javax.swing.JLabel();
        jLabelValorNumero = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelAcoesConta = new javax.swing.JPanel();
        jButtonRemoverConta = new javax.swing.JButton();
        jPanelConfigurarConta = new javax.swing.JPanel();
        jButtonAlterarNome = new javax.swing.JButton();
        jButtonAlterarEmail = new javax.swing.JButton();
        jButtonAlterarSenha = new javax.swing.JButton();
        jButtonAlterarEndereco = new javax.swing.JButton();
        jLabelHistorico = new javax.swing.JLabel();
        fillerMargemHistorico = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jScrollPaneHistoricoLivros = new javax.swing.JScrollPane();
        jPanelHistoricoLivros = new javax.swing.JPanel();

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jPanelMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelMain.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.3;
        jPanelMain.add(fillerMargemEsquerda, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.3;
        jPanelMain.add(fillerMargemDireita, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weighty = 0.25;
        jPanelMain.add(fillerMargemSuperior, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weighty = 0.35;
        jPanelMain.add(fillerMargemInferior, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanelMain.add(fillerMargemCentro, gridBagConstraints);

        jLabelConta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelConta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelConta.setText("Conta");
        jLabelConta.setMaximumSize(new java.awt.Dimension(80, 22));
        jLabelConta.setMinimumSize(new java.awt.Dimension(80, 22));
        jLabelConta.setPreferredSize(new java.awt.Dimension(80, 22));
        jLabelConta.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanelMain.add(jLabelConta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.15;
        jPanelMain.add(fillerMargemConta, gridBagConstraints);

        jPanelConta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelConta.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanelConta.setLayout(new java.awt.GridBagLayout());

        jPanelValoresConta.setLayout(new java.awt.GridBagLayout());

        jLabelCampoNome.setText("Nome:");
        jLabelCampoNome.setMaximumSize(new java.awt.Dimension(60, 15));
        jLabelCampoNome.setMinimumSize(new java.awt.Dimension(60, 15));
        jLabelCampoNome.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelCampoNome, gridBagConstraints);

        jLabelCampoEmail.setText("E-mail:");
        jLabelCampoEmail.setMaximumSize(new java.awt.Dimension(60, 15));
        jLabelCampoEmail.setMinimumSize(new java.awt.Dimension(60, 15));
        jLabelCampoEmail.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelCampoEmail, gridBagConstraints);

        jLabelCampoRua.setText("Rua:");
        jLabelCampoRua.setMaximumSize(new java.awt.Dimension(60, 15));
        jLabelCampoRua.setMinimumSize(new java.awt.Dimension(60, 15));
        jLabelCampoRua.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelCampoRua, gridBagConstraints);

        jLabelCampoBairro.setText("Bairro:");
        jLabelCampoBairro.setMaximumSize(new java.awt.Dimension(60, 15));
        jLabelCampoBairro.setMinimumSize(new java.awt.Dimension(60, 15));
        jLabelCampoBairro.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelCampoBairro, gridBagConstraints);

        jLabelCampoCEP.setText("CEP:");
        jLabelCampoCEP.setMaximumSize(new java.awt.Dimension(60, 15));
        jLabelCampoCEP.setMinimumSize(new java.awt.Dimension(60, 15));
        jLabelCampoCEP.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelCampoCEP, gridBagConstraints);

        jLabelCampoNumero.setText("Número:");
        jLabelCampoNumero.setMaximumSize(new java.awt.Dimension(60, 15));
        jLabelCampoNumero.setMinimumSize(new java.awt.Dimension(60, 15));
        jLabelCampoNumero.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelCampoNumero, gridBagConstraints);

        jLabelValorNome.setText("jLabelNome");
        jLabelValorNome.setMaximumSize(new java.awt.Dimension(240, 15));
        jLabelValorNome.setMinimumSize(new java.awt.Dimension(240, 15));
        jLabelValorNome.setPreferredSize(new java.awt.Dimension(240, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelValorNome, gridBagConstraints);

        jLabelValorEmail.setText("jLabelEmail");
        jLabelValorEmail.setMaximumSize(new java.awt.Dimension(240, 15));
        jLabelValorEmail.setMinimumSize(new java.awt.Dimension(240, 15));
        jLabelValorEmail.setPreferredSize(new java.awt.Dimension(240, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelValorEmail, gridBagConstraints);

        jLabelValorRua.setText("jLabelRua");
        jLabelValorRua.setMaximumSize(new java.awt.Dimension(240, 15));
        jLabelValorRua.setMinimumSize(new java.awt.Dimension(240, 15));
        jLabelValorRua.setPreferredSize(new java.awt.Dimension(240, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelValorRua, gridBagConstraints);

        jLabelValorBairro.setText("jLabelBairro");
        jLabelValorBairro.setMaximumSize(new java.awt.Dimension(240, 15));
        jLabelValorBairro.setMinimumSize(new java.awt.Dimension(240, 15));
        jLabelValorBairro.setPreferredSize(new java.awt.Dimension(240, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelValorBairro, gridBagConstraints);

        jLabelValorCEP.setText("jLabelCep");
        jLabelValorCEP.setMaximumSize(new java.awt.Dimension(240, 15));
        jLabelValorCEP.setMinimumSize(new java.awt.Dimension(240, 15));
        jLabelValorCEP.setPreferredSize(new java.awt.Dimension(240, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelValorCEP, gridBagConstraints);

        jLabelValorNumero.setText("jLabelNumero");
        jLabelValorNumero.setMaximumSize(new java.awt.Dimension(240, 15));
        jLabelValorNumero.setMinimumSize(new java.awt.Dimension(240, 15));
        jLabelValorNumero.setPreferredSize(new java.awt.Dimension(240, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanelValoresConta.add(jLabelValorNumero, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        jPanelValoresConta.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelConta.add(jPanelValoresConta, gridBagConstraints);

        jPanelAcoesConta.setLayout(new java.awt.GridBagLayout());

        jButtonRemoverConta.setText("Remover Conta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanelAcoesConta.add(jButtonRemoverConta, gridBagConstraints);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0};
        jPanel3Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanelConfigurarConta.setLayout(jPanel3Layout);

        jButtonAlterarNome.setText("alterar nome");
        jButtonAlterarNome.setMaximumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarNome.setMinimumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarNome.setPreferredSize(new java.awt.Dimension(120, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPanelConfigurarConta.add(jButtonAlterarNome, gridBagConstraints);

        jButtonAlterarEmail.setText("alterar e-mail");
        jButtonAlterarEmail.setMaximumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarEmail.setMinimumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarEmail.setPreferredSize(new java.awt.Dimension(120, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPanelConfigurarConta.add(jButtonAlterarEmail, gridBagConstraints);

        jButtonAlterarSenha.setText("alterar senha");
        jButtonAlterarSenha.setMaximumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarSenha.setMinimumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarSenha.setPreferredSize(new java.awt.Dimension(120, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPanelConfigurarConta.add(jButtonAlterarSenha, gridBagConstraints);

        jButtonAlterarEndereco.setText("alterar endereço");
        jButtonAlterarEndereco.setMaximumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarEndereco.setMinimumSize(new java.awt.Dimension(120, 25));
        jButtonAlterarEndereco.setPreferredSize(new java.awt.Dimension(120, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPanelConfigurarConta.add(jButtonAlterarEndereco, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelAcoesConta.add(jPanelConfigurarConta, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelConta.add(jPanelAcoesConta, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.75;
        jPanelMain.add(jPanelConta, gridBagConstraints);

        jLabelHistorico.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelHistorico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHistorico.setText("Histórico");
        jLabelHistorico.setMaximumSize(new java.awt.Dimension(80, 22));
        jLabelHistorico.setMinimumSize(new java.awt.Dimension(80, 22));
        jLabelHistorico.setPreferredSize(new java.awt.Dimension(80, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanelMain.add(jLabelHistorico, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.15;
        jPanelMain.add(fillerMargemHistorico, gridBagConstraints);

        jPanelHistoricoLivros.setLayout(new javax.swing.BoxLayout(jPanelHistoricoLivros, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneHistoricoLivros.setViewportView(jPanelHistoricoLivros);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.75;
        jPanelMain.add(jScrollPaneHistoricoLivros, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar)
                .addContainerGap(1292, Short.MAX_VALUE))
            .addComponent(jPanelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar)
                .addGap(18, 18, 18)
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler fillerMargemCentro;
    private javax.swing.Box.Filler fillerMargemConta;
    private javax.swing.Box.Filler fillerMargemDireita;
    private javax.swing.Box.Filler fillerMargemEsquerda;
    private javax.swing.Box.Filler fillerMargemHistorico;
    private javax.swing.Box.Filler fillerMargemInferior;
    private javax.swing.Box.Filler fillerMargemSuperior;
    private javax.swing.JButton jButtonAlterarEmail;
    private javax.swing.JButton jButtonAlterarEndereco;
    private javax.swing.JButton jButtonAlterarNome;
    private javax.swing.JButton jButtonAlterarSenha;
    private javax.swing.JButton jButtonRemoverConta;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabelCampoBairro;
    private javax.swing.JLabel jLabelCampoCEP;
    private javax.swing.JLabel jLabelCampoEmail;
    private javax.swing.JLabel jLabelCampoNome;
    private javax.swing.JLabel jLabelCampoNumero;
    private javax.swing.JLabel jLabelCampoRua;
    private javax.swing.JLabel jLabelConta;
    private javax.swing.JLabel jLabelHistorico;
    private javax.swing.JLabel jLabelValorBairro;
    private javax.swing.JLabel jLabelValorCEP;
    private javax.swing.JLabel jLabelValorEmail;
    private javax.swing.JLabel jLabelValorNome;
    private javax.swing.JLabel jLabelValorNumero;
    private javax.swing.JLabel jLabelValorRua;
    private javax.swing.JPanel jPanelAcoesConta;
    private javax.swing.JPanel jPanelConfigurarConta;
    private javax.swing.JPanel jPanelConta;
    private javax.swing.JPanel jPanelHistoricoLivros;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelValoresConta;
    private javax.swing.JScrollPane jScrollPaneHistoricoLivros;
    // End of variables declaration//GEN-END:variables
}