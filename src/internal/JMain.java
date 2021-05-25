package internal;

// awt
import java.awt.CardLayout;

// swing
import javax.swing.JPanel;
import javax.swing.JFrame;

// cadastro
import graphic.cadastro.JCadastroUsuario;
import graphic.cadastro.JCadastroCliente;
import graphic.cadastro.JCadastroCategoria;
import graphic.cadastro.JCadastroLivro;
import graphic.cadastro.JCadastroExemplar;

public class JMain extends javax.swing.JFrame {
    
    public static enum Janela {
        CadastroUsuario,
        CadastroCliente,
        CadastroLivro
    }
    
    public static enum Popup {
        CadastroCategoria,
        CadastroExemplar
    }
    
    private Janela janela;
    private JPanel panelJanela;
    
    private Popup popup;
    private JFrame framePopup;
    
    // ======================== Classe Singleton ========================
    
    private static JMain instanciaSingleton;
    private static JMain getInstancia() { return JMain.instanciaSingleton; }
    
    {
        if (instanciaSingleton != null) {
            throw new RuntimeException(new SingletonInstantiationException());
        } else {
            instanciaSingleton = this;
        }
    }
    
    // ========================== javax.swing ===========================
    
    /**
     * Creates new form JMain
     */
    public JMain() {
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

        jPanelContent = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCadastrarUsuario = new javax.swing.JMenuItem();
        jMenuItemCadastrarCliente = new javax.swing.JMenuItem();
        jMenuItemCadastrarCategoria = new javax.swing.JMenuItem();
        jMenuItemCadastrarLivro = new javax.swing.JMenuItem();
        jMenuItemCadastrarExemplar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelContent.setLayout(new java.awt.CardLayout());

        jMenu1.setText("Cadastrar");

        jMenuItemCadastrarUsuario.setText("Usuário");
        jMenuItemCadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrarUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCadastrarUsuario);

        jMenuItemCadastrarCliente.setText("Cliente");
        jMenuItemCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCadastrarCliente);

        jMenuItemCadastrarCategoria.setText("Categoria");
        jMenuItemCadastrarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrarCategoriaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCadastrarCategoria);

        jMenuItemCadastrarLivro.setText("Livro");
        jMenuItemCadastrarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrarLivroActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCadastrarLivro);

        jMenuItemCadastrarExemplar.setText("Exemplar");
        jMenuItemCadastrarExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastrarExemplarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCadastrarExemplar);

        jMenuBar.add(jMenu1);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ==================== Gerenciadores de Evento =====================
    
    private void jMenuItemCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastrarUsuarioActionPerformed
        setJanela(Janela.CadastroUsuario);
    }//GEN-LAST:event_jMenuItemCadastrarUsuarioActionPerformed

    private void jMenuItemCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastrarClienteActionPerformed
        setJanela(Janela.CadastroCliente);
    }//GEN-LAST:event_jMenuItemCadastrarClienteActionPerformed

    private void jMenuItemCadastrarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastrarCategoriaActionPerformed
        setJanela(Popup.CadastroCategoria);
    }//GEN-LAST:event_jMenuItemCadastrarCategoriaActionPerformed

    private void jMenuItemCadastrarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastrarLivroActionPerformed
        setJanela(Janela.CadastroLivro);
    }//GEN-LAST:event_jMenuItemCadastrarLivroActionPerformed

    private void jMenuItemCadastrarExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastrarExemplarActionPerformed
        setJanela(Popup.CadastroExemplar);
    }//GEN-LAST:event_jMenuItemCadastrarExemplarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JMain().setVisible(true);
            }
        });
    }
    
    // ==================== Gerenciadores de Janela =====================
    
    public boolean setJanela(Janela janela) {
        
        JPanel panel = null;
        String tag = null;
        switch (janela) {
            case CadastroUsuario:
                panel = new JCadastroUsuario();
                tag = "CadastroUsuario";
                break;
            case CadastroCliente:
                panel = new JCadastroCliente();
                tag = "CadastroCliente";
                break;
            case CadastroLivro:
                panel = new JCadastroLivro();
                tag = "CadastroLivro";
                break;
        }
        
        if (panel != null) {
            jPanelContent.removeAll();
            jPanelContent.add(panel, tag);
            CardLayout layout = (CardLayout) jPanelContent.getLayout();
            layout.first(jPanelContent);
            return true;
        }
        
        return false;
    }
    
    public boolean setJanela(Popup popup) {
        
        JPanel tmpPanel = null;
        switch (popup) {
            case CadastroCategoria:
                tmpPanel = new JCadastroCategoria();
                break;
            case CadastroExemplar:
                tmpPanel = new JCadastroExemplar();
                break;
        }
        
        if (tmpPanel != null) {
            this.framePopup = new JFrame();
            this.framePopup.add(tmpPanel);
            this.framePopup.pack();
            this.framePopup.setVisible(true);
            return true;
        }
        return false;
    }
    
    // ==================== funções estaticas ====================
    
    //Fica aqui por enquanto, já que várias classes precisam
    public static String formataData(String data){
        String[] pedacosData = data.split("/");

        return pedacosData[2]+"-"+pedacosData[1]+"-"+pedacosData[0];
    }
    
    //Fica aqui por enquanto, já que várias classes precisam
    public static boolean ultimoCharIgual(char c, String texto){
        if (!texto.isEmpty()){
            return c == texto.charAt(texto.length()-1);
        }
        
        return false;
    }
    
    /**
     * Essas são as regras que o ISBN deve seguir.
     * Fica aqui por enquanto, já que várias classes precisam.
     * 
     * @param isbn o ISBN para ser verificado
     * @return true se for válido e caso contrári, retorna false
     */
    public static boolean validaISBN(String isbn){
        int qtdTracos = quantidadeCaracter('-', isbn);
        String textoPuro = isbn.replace("-", "");
        
        if ((qtdTracos == 3 || qtdTracos == 4) &&
                !isbn.contains("--") &&
                textoPuro.matches("[0-9]{10}||[0-9]{13}")){
            return true;
        }
        
        return false;
    }
    
    //Fica aqui por enquanto, já que várias classes precisam
    public static int quantidadeCaracter(char c, String s){
        int quantidade = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c)
                quantidade++;
        }
        
        return quantidade;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemCadastrarCategoria;
    private javax.swing.JMenuItem jMenuItemCadastrarCliente;
    private javax.swing.JMenuItem jMenuItemCadastrarExemplar;
    private javax.swing.JMenuItem jMenuItemCadastrarLivro;
    private javax.swing.JMenuItem jMenuItemCadastrarUsuario;
    private javax.swing.JPanel jPanelContent;
    // End of variables declaration//GEN-END:variables
}
