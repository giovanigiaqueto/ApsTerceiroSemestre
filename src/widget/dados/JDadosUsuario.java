/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package widget.dados;

import model.Usuario;

/**
 *
 * @author giovani
 */
public class JDadosUsuario extends javax.swing.JPanel {

    private Usuario usuario;
    
    /** Creates new form JCadastroUsuario */
    public JDadosUsuario() {
        initComponents();
    }
    
    public JDadosUsuario(Usuario usuario) {
        this(); // chama o construtor padrão
        this.usuario = usuario;
        if (usuario == null) {
            jTextFieldNome.setText("");
            jTextAreaEndereco.setText("");
            jTextFieldCPF.setText("");
            jTextFieldSexo.setText("");
            jTextFieldEmail.setText("");
            jTextFieldTelefone.setText("");
        } else {
            jTextFieldNome.setText(usuario.getNomeUsuario());
            jTextAreaEndereco.setText(usuario.getEnderecoUsuario());
            jTextFieldCPF.setText(usuario.getCPFUsuario());
            jTextFieldSexo.setText(usuario.getSexoUsuario());
            jTextFieldEmail.setText(usuario.getEmailUsuario());
            jTextFieldTelefone.setText(usuario.getTelefoneUsuario());
        }
    }
    
    // ========== GETTERS ==========
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    // ========== SETTERS ==========
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario == null) {
            jTextFieldNome.setText("");
            jTextAreaEndereco.setText("");
            jTextFieldCPF.setText("");
            jTextFieldSexo.setText("");
            jTextFieldEmail.setText("");
            jTextFieldTelefone.setText("");
        } else {
            jTextFieldNome.setText(usuario.getNomeUsuario());
            jTextAreaEndereco.setText(usuario.getEnderecoUsuario());
            jTextFieldCPF.setText(usuario.getCPFUsuario());
            jTextFieldSexo.setText(usuario.getSexoUsuario());
            jTextFieldEmail.setText(usuario.getEmailUsuario());
            jTextFieldTelefone.setText(usuario.getTelefoneUsuario());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelEndereço = new javax.swing.JLabel();
        jPanelEndereco = new javax.swing.JPanel();
        jTextAreaEndereco = new javax.swing.JTextArea();
        jLabelCPF = new javax.swing.JLabel();
        jTextFieldCPF = new javax.swing.JTextField();
        jLabelTelefone = new javax.swing.JLabel();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelSexo = new javax.swing.JLabel();
        jTextFieldSexo = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(524, 307));
        setPreferredSize(new java.awt.Dimension(524, 307));

        jLabelNome.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelNome.setText("Nome");

        jTextFieldNome.setEditable(false);
        jTextFieldNome.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNome.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldNome.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jLabelEndereço.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelEndereço.setText("Endereço");

        jPanelEndereco.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("TextField.inactiveForeground")));
        jPanelEndereco.setMinimumSize(new java.awt.Dimension(500, 42));
        jPanelEndereco.setName(""); // NOI18N

        jTextAreaEndereco.setEditable(false);
        jTextAreaEndereco.setColumns(20);
        jTextAreaEndereco.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextAreaEndereco.setLineWrap(true);
        jTextAreaEndereco.setRows(5);
        jTextAreaEndereco.setWrapStyleWord(true);
        jTextAreaEndereco.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jTextAreaEndereco.setMinimumSize(new java.awt.Dimension(498, 40));
        jTextAreaEndereco.setPreferredSize(new java.awt.Dimension(498, 40));

        javax.swing.GroupLayout jPanelEnderecoLayout = new javax.swing.GroupLayout(jPanelEndereco);
        jPanelEndereco.setLayout(jPanelEnderecoLayout);
        jPanelEnderecoLayout.setHorizontalGroup(
            jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextAreaEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelEnderecoLayout.setVerticalGroup(
            jPanelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextAreaEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabelCPF.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelCPF.setText("CPF");

        jTextFieldCPF.setEditable(false);
        jTextFieldCPF.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldCPF.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldCPF.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jLabelTelefone.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelTelefone.setText("Telefone");

        jTextFieldTelefone.setEditable(false);
        jTextFieldTelefone.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTelefone.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldTelefone.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jLabelEmail.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelEmail.setText("E-mail");

        jTextFieldEmail.setEditable(false);
        jTextFieldEmail.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldEmail.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldEmail.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jLabelSexo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelSexo.setText("Sexo");

        jTextFieldSexo.setEditable(false);
        jTextFieldSexo.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldSexo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldSexo.setMargin(new java.awt.Insets(2, 2, 2, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSexo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEndereço, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCPF)
                            .addComponent(jLabelCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTelefone)
                            .addComponent(jLabelTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldEmail)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNome))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelEndereço)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereço;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JPanel jPanelEndereco;
    private javax.swing.JTextArea jTextAreaEndereco;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldSexo;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables

}
