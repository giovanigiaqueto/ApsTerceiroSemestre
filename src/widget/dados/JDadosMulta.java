package widget.dados;

import model.Multa;
import dao.UsuarioDAO;

public class JDadosMulta extends javax.swing.JPanel {

    /**
     * Creates new form JDadosMulta
     */
    public JDadosMulta() {
        initComponents();
    }
    
    public JDadosMulta(Multa multa) {
        this();
        RuntimeException exception = null;
        
        String usuario = "Usuário Desconhecido";
        try {
            UsuarioDAO dao = new UsuarioDAO();
            
            
        } catch(RuntimeException e) {
            
        }
        jTextFieldUsuario.setText(usuario);
        jTextFieldValor.setText(String.valueOf(multa.getValorMulta()) + " R$");
        jTextFieldEstado.setText(multa.getPagamentoMulta() ? "paga":"pendente");
        jTextAreaDescricao.setText(multa.getDescricaoMulta());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelValor = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jTextFieldValor = new javax.swing.JTextField();
        jTextFieldEstado = new javax.swing.JTextField();
        jLabelDescricao = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(400, 281));
        setPreferredSize(new java.awt.Dimension(400, 281));

        jLabelValor.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelValor.setText("Valor:");

        jLabelEstado.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelEstado.setText("Estado:");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("TextField.inactiveForeground")));

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextAreaDescricao)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextAreaDescricao, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jLabelUsuario.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelUsuario.setText("Usuário Emissor: ");

        jTextFieldUsuario.setEditable(false);
        jTextFieldUsuario.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldUsuario.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jTextFieldValor.setEditable(false);
        jTextFieldValor.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldValor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldValor.setText("9999.99 R$");
        jTextFieldValor.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jTextFieldEstado.setEditable(false);
        jTextFieldEstado.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldEstado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEstado.setText("Pendente");
        jTextFieldEstado.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jLabelDescricao.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelDescricao.setText("Descrição da Multa:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldUsuario)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 58, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 58, Short.MAX_VALUE))
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValor)
                    .addComponent(jLabelEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelValor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
