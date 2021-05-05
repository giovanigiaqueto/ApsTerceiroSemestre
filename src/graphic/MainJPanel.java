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
public class MainJPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainJPanel
     */
    public MainJPanel() {
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

        jToolBar = new javax.swing.JToolBar();
        jButtonConta = new javax.swing.JButton();
        jButtonCheckout = new javax.swing.JButton();
        jPanel = new javax.swing.JPanel();
        fillerNE = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        fillerSE = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        fillerSW = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        fillerNW = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jListaLivros = new graphic.util.JListaLivros();

        jToolBar.setRollover(true);

        jButtonConta.setText("Conta");
        jButtonConta.setFocusable(false);
        jButtonConta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonConta.setMaximumSize(new java.awt.Dimension(63, 27));
        jButtonConta.setMinimumSize(new java.awt.Dimension(63, 27));
        jButtonConta.setPreferredSize(new java.awt.Dimension(63, 27));
        jButtonConta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar.add(jButtonConta);

        jButtonCheckout.setText("Checkout");
        jButtonCheckout.setFocusable(false);
        jButtonCheckout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCheckout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar.add(jButtonCheckout);

        jPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.4;
        jPanel.add(fillerNE, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.4;
        jPanel.add(fillerSE, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.2;
        jPanel.add(fillerSW, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.2;
        jPanel.add(fillerNW, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.8;
        jPanel.add(jListaLivros, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler fillerNE;
    private javax.swing.Box.Filler fillerNW;
    private javax.swing.Box.Filler fillerSE;
    private javax.swing.Box.Filler fillerSW;
    private javax.swing.JButton jButtonCheckout;
    private javax.swing.JButton jButtonConta;
    private graphic.util.JListaLivros jListaLivros;
    private javax.swing.JPanel jPanel;
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables
}
