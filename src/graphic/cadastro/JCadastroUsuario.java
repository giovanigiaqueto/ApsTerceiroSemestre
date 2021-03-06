package graphic.cadastro;

// swing
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// awt
import java.awt.Color;

// java security
import java.security.NoSuchAlgorithmException;

// dao
import dao.UsuarioDAO;
import internal.JMain;

// modelo
import model.Usuario;

// suporte
import widget.support.IPanelCRUD;
import static internal.JMain.sha256;
import static internal.JMain.bytesToHex;

public class JCadastroUsuario extends javax.swing.JPanel implements IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Cadastrar Usuário"; }
    
    private int idUsuario;
    
    private static final DocumentFilter jTextFieldNumeroDocumentFilter =
        new DocumentFilter() {

        private boolean verificar(String string) {
            return string.matches("[1-9][0-9]{0,4}[a-zA-Z]?");
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset,
            String text, AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, text);

            if (verificar(sb.toString())) {
                super.insertString(fb, offset, text, attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset,
            int length, String text, AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (verificar(sb.toString())) {
                super.replace(fb, offset, length, text, attr);
            }
        }
    };
    
    private static final DocumentFilter textoSemVirgulaDocumentFilter =
        new DocumentFilter() {

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset,
            String text, AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, text);

            if (!sb.toString().contains(",")) {
                super.insertString(fb, offset, text, attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset,
            int length, String text, AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (!sb.toString().contains(",")) {
                super.replace(fb, offset, length, text, attr);
            }
        }
    };
    
    /** Creates new form JCadastroUsuario */
    public JCadastroUsuario() {
        initComponents();
    }
    
    public JCadastroUsuario(Usuario usuario) {
        this(); // chama o construtor padrão
        setUsuario(usuario);
    }
    
    // ========== GETTERS ==========
    
    private Usuario __innerGetUsuario(boolean mostrarErros) {
        
        String nome = jTextFieldNome.getText().trim();
        String cpf = jTextFieldCPF.getText().trim();
        String telefone = jTextFieldTelefone.getText().trim();
        String sexo = (String) jComboBoxSexo.getSelectedItem();
        if (!(sexo.equals("feminino") || sexo.equals("masculino"))) {
            sexo = jTextFieldSexo.getText().trim();
        }
        String complemento = jTextFieldComplemento.getText().trim();
        String rua = jTextFieldRua.getText().trim();
        String numero = jTextFieldNumero.getText().trim();
        String cidade = jTextFieldCidade.getText().trim();
        String estado = (String) jComboBoxEstado.getSelectedItem();
        String email = jTextFieldEmail.getText().trim();
        
        String campo = null;
        if (nome.isEmpty()) campo = "nome";
        else if (cidade.isEmpty()) campo = "cidade";
        else if (estado.isEmpty()) campo = "estado";
        else if (rua.isEmpty()) campo = "rua";
        else if (numero.isEmpty()) campo = "numero";
        else if (cpf.isEmpty()) campo = "cpf";
        else if (telefone.isEmpty()) campo = "telefone";
        else if (sexo.isEmpty()) campo = "sexo";
        else if (email.isEmpty()) campo = "email";
        
        if (campo == null) {
            
            String endereco = rua + ", " + numero + ", " + cidade + ", " + estado;
            if (!complemento.isEmpty()) {
                endereco = complemento + ", " + endereco;
            }
            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);
            usuario.setNomeUsuario(nome);
            usuario.setCPFUsuario(cpf);
            usuario.setTelefoneUsuario(telefone);
            usuario.setSexoUsuario(sexo);
            usuario.setEnderecoUsuario(endereco);
            usuario.setEmailUsuario(email);
            
            return usuario;
        }
        
        if (mostrarErros) {
            JOptionPane.showMessageDialog(new JFrame(), 
                "o campo " + campo + " não foi preenchido",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
    public Usuario getUsuario() {
        return __innerGetUsuario(false);
    }
    
    // ========== SETTERS ==========
    
    public final void setUsuario(Usuario usuario) {
        
        if (usuario != null) {
            
            jTextFieldNome.setText(usuario.getNomeUsuario());
            jTextFieldCPF.setText(usuario.getCPFUsuario());
            jTextFieldTelefone.setText(usuario.getTelefoneUsuario());
            String sexo = usuario.getSexoUsuario().trim();
            if (sexo.isEmpty() || sexo.equals("masculino") || sexo.equals("feminino")) {
                jComboBoxSexo.setSelectedItem(sexo);
                jTextFieldSexo.setText("");
            } else {
                jComboBoxSexo.setSelectedItem("outro");
                jTextFieldSexo.setText(usuario.getSexoUsuario());
            }
            
            String[] endereco = usuario.getEnderecoUsuario().split(",");
            
            boolean sucesso;
            switch (endereco.length) {
            case 5:
                jTextFieldComplemento.setText(endereco[0].strip());
                jTextFieldRua.setText(endereco[1].strip());
                jTextFieldNumero.setText(endereco[2].strip());
                jTextFieldCidade.setText(endereco[3].strip());
                sucesso = false;
                for (int i = 0; i < jComboBoxEstado.getItemCount(); ++i) {
                    if (endereco[4].strip().equals(jComboBoxEstado.getItemAt(WIDTH))) {
                        jComboBoxEstado.setSelectedIndex(i);
                        sucesso = true;
                    }
                }
                if (!sucesso) {
                    jComboBoxEstado.setSelectedItem(endereco[4].strip());
                }
                break;
            case 4:
                jTextFieldComplemento.setText("");
                jTextFieldRua.setText(endereco[0].strip());
                jTextFieldNumero.setText(endereco[1].strip());
                jTextFieldCidade.setText(endereco[2].strip());
                sucesso = false;
                for (int i = 0; i < jComboBoxEstado.getItemCount(); ++i) {
                    if (endereco[3].strip().equals(jComboBoxEstado.getItemAt(WIDTH))) {
                        jComboBoxEstado.setSelectedIndex(i);
                        sucesso = true;
                    }
                }
                if (!sucesso) {
                    jComboBoxEstado.setSelectedItem(endereco[3].strip());
                }
                break;
            default:
                jTextFieldComplemento.setText("ERRO");
                jTextFieldRua.setText("ERRO");
                jTextFieldNumero.setText("ERRO");
                jTextFieldCidade.setText("ERRO");
                jComboBoxEstado.setSelectedItem("");
            }
            
            jTextFieldEmail.setText(usuario.getEmailUsuario());
            
            idUsuario = usuario.getIdUsuario();
            
        } else {
            
            jTextFieldNome.setText("");
            jTextFieldCPF.setText("");
            jTextFieldTelefone.setText("");
            
            jComboBoxSexo.setSelectedItem("");
            jTextFieldSexo.setText("");
            
            jTextFieldComplemento.setText("");
            jTextFieldRua.setText("");
            jTextFieldNumero.setText("");
            jTextFieldCidade.setText("");
            jComboBoxEstado.setSelectedItem("");
            
            jTextFieldEmail.setText("");
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

        jLabelHeader = new javax.swing.JLabel();
        jPanelForm = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelCPF = new javax.swing.JLabel();
        jTextFieldCPF = new javax.swing.JTextField();
        jLabelTelefone = new javax.swing.JLabel();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabelSexo = new javax.swing.JLabel();
        jTextFieldSexo = new javax.swing.JTextField();
        jLabelCidade = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabelEstado = new javax.swing.JLabel();
        jLabelRua = new javax.swing.JLabel();
        jTextFieldRua = new javax.swing.JTextField();
        jLabelNumero = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jComboBoxSexo = new javax.swing.JComboBox<>();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jLabelComplemento = new javax.swing.JLabel();
        jTextFieldComplemento = new javax.swing.JTextField();
        jPanelConfirmacao = new javax.swing.JPanel();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(620, 520));

        jLabelHeader.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHeader.setText("Cadastro Usuário");

        jPanelForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelForm.setMinimumSize(new java.awt.Dimension(548, 342));
        jPanelForm.setPreferredSize(new java.awt.Dimension(548, 342));

        jLabelNome.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelNome.setText("Nome Completo");

        jTextFieldNome.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldNome.setMinimumSize(new java.awt.Dimension(520, 25));
        jTextFieldNome.setPreferredSize(new java.awt.Dimension(520, 25));

        jLabelCPF.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelCPF.setText("CPF");

        jTextFieldCPF.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldCPF.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jTextFieldCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCPFKeyTyped(evt);
            }
        });

        jLabelTelefone.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelTelefone.setText("Telefone");

        jTextFieldTelefone.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldTelefone.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jTextFieldTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefoneKeyTyped(evt);
            }
        });

        jLabelSexo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelSexo.setText("Sexo");

        jTextFieldSexo.setEditable(false);
        jTextFieldSexo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldSexo.setMinimumSize(new java.awt.Dimension(122, 25));
        jTextFieldSexo.setPreferredSize(new java.awt.Dimension(122, 25));

        jLabelCidade.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelCidade.setText("Cidade");

        ((javax.swing.text.PlainDocument) jTextFieldCidade.getDocument()).setDocumentFilter(textoSemVirgulaDocumentFilter);
        jTextFieldCidade.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldCidade.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jLabelEstado.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelEstado.setText("Estado");

        jLabelRua.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelRua.setText("Rua");

        ((javax.swing.text.PlainDocument) jTextFieldRua.getDocument()).setDocumentFilter(textoSemVirgulaDocumentFilter);
        jTextFieldRua.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldRua.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jLabelNumero.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelNumero.setText("Nº");
        jLabelNumero.setMaximumSize(new java.awt.Dimension(48, 22));
        jLabelNumero.setMinimumSize(new java.awt.Dimension(48, 22));
        jLabelNumero.setPreferredSize(new java.awt.Dimension(48, 22));

        ((javax.swing.text.PlainDocument) jTextFieldNumero.getDocument()).setDocumentFilter(jTextFieldNumeroDocumentFilter);
        jTextFieldNumero.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldNumero.setMinimumSize(new java.awt.Dimension(32, 25));
        jTextFieldNumero.setPreferredSize(new java.awt.Dimension(32, 25));
        jTextFieldNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNumeroKeyTyped(evt);
            }
        });

        jLabelEmail.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelEmail.setText("E-mail");

        jTextFieldEmail.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldEmail.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jComboBoxSexo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "feminino", "masculino", "outro" }));
        jComboBoxSexo.setMinimumSize(new java.awt.Dimension(90, 26));
        jComboBoxSexo.setName(""); // NOI18N
        jComboBoxSexo.setPreferredSize(new java.awt.Dimension(90, 26));
        jComboBoxSexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSexoItemStateChanged(evt);
            }
        });

        jComboBoxEstado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins", "Distrito Federal" }));
        jComboBoxEstado.setToolTipText("");

        jLabelComplemento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelComplemento.setText("Complemento");

        ((javax.swing.text.PlainDocument) jTextFieldComplemento.getDocument()).setDocumentFilter(textoSemVirgulaDocumentFilter);
        jTextFieldComplemento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextFieldComplemento.setMargin(new java.awt.Insets(2, 2, 2, 2));

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCPF)
                            .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelefone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(jLabelSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelRua, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldRua))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTextFieldCidade, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldComplemento)
                                .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldEmail))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelCidade)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jLabelEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jLabelComplemento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jLabelRua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jLabelSexo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(jLabelCPF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addComponent(jLabelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setMaximumSize(new java.awt.Dimension(90, 25));
        jButtonConfirmar.setMinimumSize(new java.awt.Dimension(90, 25));
        jButtonConfirmar.setPreferredSize(new java.awt.Dimension(90, 25));
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setMaximumSize(new java.awt.Dimension(90, 25));
        jButtonCancelar.setMinimumSize(new java.awt.Dimension(90, 25));
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(90, 25));
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConfirmacaoLayout = new javax.swing.GroupLayout(jPanelConfirmacao);
        jPanelConfirmacao.setLayout(jPanelConfirmacaoLayout);
        jPanelConfirmacaoLayout.setHorizontalGroup(
            jPanelConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConfirmacaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConfirmacaoLayout.setVerticalGroup(
            jPanelConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfirmacaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelConfirmacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        
        Usuario usuario = this.__innerGetUsuario(true);
        if (usuario != null) {
            
            final JComponent[] inputs = new JComponent[] {
                new JLabel("Senha"), new JPasswordField(),
                new JLabel("Senha (confirmar)"), new JPasswordField()
            };
            
            while (true) {
                // ler senha
                int resultado =
                    JOptionPane.showConfirmDialog(null, inputs,
                        "Senha", JOptionPane.PLAIN_MESSAGE);    

                // cancelamento
                if (resultado != JOptionPane.OK_OPTION) return;
                
                // senha errada
                if ((new String(((JPasswordField) inputs[1]).getPassword())).trim().equals(
                    (new String(((JPasswordField) inputs[3]).getPassword())).trim())) {
                    break;
                }
                
                JOptionPane.showMessageDialog(null, "as senhas diferem",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }

            String nome = ((JTextField) inputs[1]).getText();
            String senhaHash = "";

            try {
                senhaHash = bytesToHex(sha256(
                    new String(((JPasswordField) inputs[3]).getPassword())
                ));
            } catch(NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(null, "SHA-256 não suportado",
                    "Erro", JOptionPane.PLAIN_MESSAGE);    
            }
            
            usuario.setSenhaUsuario(senhaHash);
            
            UsuarioDAO dao = new UsuarioDAO();
            if (usuario.getIdUsuario() == 0) {
                if (!dao.salvar(usuario)) {
                    JOptionPane.showMessageDialog(null, 
                        "não foi possível salvar o usuário", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "usuário cadastrado", "Aviso",
                        JOptionPane.PLAIN_MESSAGE);
                }
            } else if (!dao.alterar(usuario)) {
                
                JOptionPane.showMessageDialog(null, 
                    "não foi possível alterar o usuário", "Erro",
                    JOptionPane.ERROR_MESSAGE);
                
                return;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "usuário alterado", "Aviso",
                    JOptionPane.PLAIN_MESSAGE);
            }
            
            JMain.getInstancia().popJanelaCRUD();
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        JMain.getInstancia().popJanelaCRUD();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jComboBoxSexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSexoItemStateChanged
        if (evt.getItem() == "outro") {
            jTextFieldSexo.setEditable(true);
        } else {
            jTextFieldSexo.setEditable(false);
            jTextFieldSexo.setText("");
        }
    }//GEN-LAST:event_jComboBoxSexoItemStateChanged

    private void jTextFieldNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumeroKeyTyped
        String charsPermitidos = "0123456789";
            if(!charsPermitidos.contains(evt.getKeyChar()+""))
                evt.consume();
    }//GEN-LAST:event_jTextFieldNumeroKeyTyped

    private void jTextFieldCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCPFKeyTyped
        String charsPermitidos = "0123456789";
            if(!charsPermitidos.contains(evt.getKeyChar()+"") ||
                    jTextFieldCPF.getText().length() >= 11)
                evt.consume();
    }//GEN-LAST:event_jTextFieldCPFKeyTyped

    private void jTextFieldTelefoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefoneKeyTyped
        String charsPermitidos = "0123456789";
            if(!charsPermitidos.contains(evt.getKeyChar()+""))
                evt.consume();
    }//GEN-LAST:event_jTextFieldTelefoneKeyTyped

    private void lembreteCamposEmBranco(){
        Color cor = new Color(248, 220, 219);
        
        if (jTextFieldNome.getText().isEmpty()){
            jTextFieldNome.setBackground(cor);
        } else {
            jTextFieldNome.setBackground(Color.WHITE);
        }
        
        if (jTextFieldCidade.getText().isEmpty()){
            jTextFieldCidade.setBackground(cor);
        } else {
            jTextFieldCidade.setBackground(Color.WHITE);
        }
        
        if (jTextFieldRua.getText().isEmpty()){
            jTextFieldRua.setBackground(cor);
        } else {
            jTextFieldRua.setBackground(Color.WHITE);
        }
        
        if (jTextFieldNumero.getText().isEmpty()){
            jTextFieldNumero.setBackground(cor);
        } else {
            jTextFieldNumero.setBackground(Color.WHITE);
        }
        
        if (jTextFieldCPF.getText().isEmpty()){
            jTextFieldCPF.setBackground(cor);
        } else {
            jTextFieldCPF.setBackground(Color.WHITE);
        }
        
        if (jTextFieldTelefone.getText().isEmpty()){
            jTextFieldTelefone.setBackground(cor);
        } else {
            jTextFieldTelefone.setBackground(Color.WHITE);
        }
        
        if (jTextFieldEmail.getText().isEmpty()){
            jTextFieldEmail.setBackground(cor);
        } else {
            jTextFieldEmail.setBackground(Color.WHITE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxSexo;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelHeader;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelRua;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JPanel jPanelConfirmacao;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldRua;
    private javax.swing.JTextField jTextFieldSexo;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables

}
