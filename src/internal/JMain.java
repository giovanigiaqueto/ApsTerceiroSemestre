package internal;

// awt
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

// swing
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

// java util
import java.util.List;
import java.util.LinkedList;

// java security
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// java charset
import java.nio.charset.StandardCharsets;

// dao
import dao.UsuarioDAO;

// modelo
import model.Usuario;
import model.Cliente;
import model.Livro;

// dados
import widget.dados.*;

// suporte
import widget.support.IPanelCRUD;

// cadastro
import graphic.cadastro.*;

// listar
import widget.listas.*;

// deletar
import graphic.deletar.*;

// graphic
import graphic.CheckoutJPanel;
import graphic.ContaJUsuario;
import graphic.ContaJCliente;

public class JMain extends javax.swing.JFrame {
    
    // sauce: https://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    // sauce: https://www.baeldung.com/sha-256-hashing-java, modificado levemente
    public static byte[] sha256(String senha) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
            senha.getBytes(StandardCharsets.UTF_8)
        );
        return encodedhash;
    }
    
    public static enum JanelaCRUD {
        
        // ========== Cadastro ==========
        
        CadastroUsuario,
        CadastroCliente,
        CadastroCategoria,
        CadastroExemplar,
        CadastroLivro,
        CadastroEmprestimo,  // vulgo checkout / carrinho
        CadastroMulta,
        
        // ========== Listagem ==========

        ListarUsuario,
        ListarCliente,
        ListarCategoria,
        ListarExemplar,
        ListarLivro,        // vulgo janela principal
        ListarEmprestimo,
        ListarMulta,
        
        // ========== Alteração ==========
        
        AlterarUsuario,
        AlterarCliente,
        AlterarCategoria,
        AlterarExemplar,
        AlterarLivro,
        AlterarEmprestimo,
        AlterarMulta,
        
        // ========== Remoção ==========
        
        RemoverUsuario,
        RemoverCliente,
        RemoverCategoria,
        RemoverExemplar,
        RemoverLivro,
        RemoverEmprestimo,
        RemoverMulta,
        
        // ========== Extras ==========
        
        ContaCliente,
        ContaUsuario,
        Checkout
    }
    
    private class MessageDialogPrimer {
        
        private String msg;
        private String title;
        
        public MessageDialogPrimer(String msg, String title) {
            this.msg = msg;
            this.title = title;
        }
        
        public void prime() {
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    private JFrame framePopup;
    private IPanelCRUD panelCRUD;
    public List<Livro> livros;
    
    public Cliente cliente;
    public Usuario usuario;
    
    private MessageDialogPrimer mensagemCRUD;
    
    // ======================== Classe Singleton ========================
    
    private static JMain instanciaSingleton;
    public static JMain getInstancia() { return JMain.instanciaSingleton; }
    
    {
        if (instanciaSingleton != null) {
            throw new RuntimeException(new SingletonInstantiationException());
        } else {
            instanciaSingleton = this;
        }
    }
    
    {
        livros = new LinkedList<>();
    }
    
    // ========================== javax.swing ===========================
    
    /**
     * Creates new form JMain
     */
    public JMain() {
        initComponents();
        initMenus();
    }
    
    private void initMenus() {
        
        // -------------------- Cadastro --------------------
        
        ActionListener listenerCadastrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { CadastrarCRUD(evt); }
        };
        
        jMenuItemCadastrarUsuario.addActionListener(listenerCadastrar);
        jMenuItemCadastrarCliente.addActionListener(listenerCadastrar);
        jMenuItemCadastrarCategoria.addActionListener(listenerCadastrar);
        jMenuItemCadastrarLivro.addActionListener(listenerCadastrar);
        jMenuItemCadastrarExemplar.addActionListener(listenerCadastrar);
        
        // -------------------- Listagem --------------------
        
        ActionListener listenerListagem = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { ListarCRUD(evt); }
        };
        
        jMenuItemListarUsuario.addActionListener(listenerListagem);
        jMenuItemListarCliente.addActionListener(listenerListagem);
        jMenuItemListarCategoria.addActionListener(listenerListagem);
        jMenuItemListarLivro.addActionListener(listenerListagem);
        jMenuItemListarExemplar.addActionListener(listenerListagem);
        jMenuItemListarEmprestimo.addActionListener(listenerListagem);
        jMenuItemListarMulta.addActionListener(listenerListagem);
        
        // -------------------- Listagem --------------------
        
        ActionListener alterarListagem = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { AlterarCRUD(evt); }
        };
        
        jMenuItemAlterarUsuario.addActionListener(alterarListagem);
        jMenuItemAlterarCliente.addActionListener(alterarListagem);
        jMenuItemAlterarCategoria.addActionListener(alterarListagem);
        jMenuItemAlterarLivro.addActionListener(alterarListagem);
        jMenuItemAlterarExemplar.addActionListener(alterarListagem);
        
        // -------------------- Remoção --------------------
        
        ActionListener listenerRemover = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { RemoverCRUD(evt); }
        };
        
        jMenuItemRemoverUsuario.addActionListener(listenerRemover);
        jMenuItemRemoverCliente.addActionListener(listenerRemover);
        jMenuItemRemoverCategoria.addActionListener(listenerRemover);
        jMenuItemRemoverLivro.addActionListener(listenerRemover);
        jMenuItemRemoverExemplar.addActionListener(listenerRemover);
        
        // --------------------- Misc. ---------------------
        
        ActionListener listenerMisc = new ActionListener() {
            public void actionPerformed(ActionEvent evt) { MiscCRUD(evt); }
        };
        
        jMenuItemMiscCheckout.addActionListener(listenerMisc);
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
        jMenuCadastrar = new javax.swing.JMenu();
        jMenuItemCadastrarUsuario = new javax.swing.JMenuItem();
        jMenuItemCadastrarCliente = new javax.swing.JMenuItem();
        jMenuItemCadastrarCategoria = new javax.swing.JMenuItem();
        jMenuItemCadastrarLivro = new javax.swing.JMenuItem();
        jMenuItemCadastrarExemplar = new javax.swing.JMenuItem();
        jMenuListar = new javax.swing.JMenu();
        jMenuItemListarUsuario = new javax.swing.JMenuItem();
        jMenuItemListarCliente = new javax.swing.JMenuItem();
        jMenuItemListarCategoria = new javax.swing.JMenuItem();
        jMenuItemListarLivro = new javax.swing.JMenuItem();
        jMenuItemListarExemplar = new javax.swing.JMenuItem();
        jMenuItemListarEmprestimo = new javax.swing.JMenuItem();
        jMenuItemListarMulta = new javax.swing.JMenuItem();
        jMenuAlterar = new javax.swing.JMenu();
        jMenuItemAlterarUsuario = new javax.swing.JMenuItem();
        jMenuItemAlterarCliente = new javax.swing.JMenuItem();
        jMenuItemAlterarCategoria = new javax.swing.JMenuItem();
        jMenuItemAlterarLivro = new javax.swing.JMenuItem();
        jMenuItemAlterarExemplar = new javax.swing.JMenuItem();
        jMenuRemover = new javax.swing.JMenu();
        jMenuItemRemoverUsuario = new javax.swing.JMenuItem();
        jMenuItemRemoverCliente = new javax.swing.JMenuItem();
        jMenuItemRemoverCategoria = new javax.swing.JMenuItem();
        jMenuItemRemoverLivro = new javax.swing.JMenuItem();
        jMenuItemRemoverExemplar = new javax.swing.JMenuItem();
        jMenuMisc = new javax.swing.JMenu();
        jMenuItemMiscCheckout = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelContent.setLayout(new java.awt.CardLayout());

        jMenuCadastrar.setText("Cadastrar");

        jMenuItemCadastrarUsuario.setText("Usuário");
        jMenuCadastrar.add(jMenuItemCadastrarUsuario);

        jMenuItemCadastrarCliente.setText("Cliente");
        jMenuCadastrar.add(jMenuItemCadastrarCliente);

        jMenuItemCadastrarCategoria.setText("Categoria");
        jMenuCadastrar.add(jMenuItemCadastrarCategoria);

        jMenuItemCadastrarLivro.setText("Livro");
        jMenuCadastrar.add(jMenuItemCadastrarLivro);

        jMenuItemCadastrarExemplar.setText("Exemplar");
        jMenuCadastrar.add(jMenuItemCadastrarExemplar);

        jMenuBar.add(jMenuCadastrar);

        jMenuListar.setText("Listar");

        jMenuItemListarUsuario.setText("Usuário");
        jMenuListar.add(jMenuItemListarUsuario);

        jMenuItemListarCliente.setText("Cliente");
        jMenuListar.add(jMenuItemListarCliente);

        jMenuItemListarCategoria.setText("Categoria");
        jMenuListar.add(jMenuItemListarCategoria);

        jMenuItemListarLivro.setText("Livro");
        jMenuListar.add(jMenuItemListarLivro);

        jMenuItemListarExemplar.setText("Exemplar");
        jMenuListar.add(jMenuItemListarExemplar);

        jMenuItemListarEmprestimo.setText("Empréstimo");
        jMenuListar.add(jMenuItemListarEmprestimo);

        jMenuItemListarMulta.setText("Multa");
        jMenuListar.add(jMenuItemListarMulta);

        jMenuBar.add(jMenuListar);

        jMenuAlterar.setText("Alterar");

        jMenuItemAlterarUsuario.setText("Usuário");
        jMenuAlterar.add(jMenuItemAlterarUsuario);

        jMenuItemAlterarCliente.setText("Cliente");
        jMenuAlterar.add(jMenuItemAlterarCliente);

        jMenuItemAlterarCategoria.setText("Categoria");
        jMenuAlterar.add(jMenuItemAlterarCategoria);

        jMenuItemAlterarLivro.setText("Livro");
        jMenuAlterar.add(jMenuItemAlterarLivro);

        jMenuItemAlterarExemplar.setText("Exemplar");
        jMenuAlterar.add(jMenuItemAlterarExemplar);

        jMenuBar.add(jMenuAlterar);

        jMenuRemover.setText("Remover");

        jMenuItemRemoverUsuario.setText("Usuário");
        jMenuRemover.add(jMenuItemRemoverUsuario);

        jMenuItemRemoverCliente.setText("Cliente");
        jMenuRemover.add(jMenuItemRemoverCliente);

        jMenuItemRemoverCategoria.setText("Categoria");
        jMenuRemover.add(jMenuItemRemoverCategoria);

        jMenuItemRemoverLivro.setText("Livro");
        jMenuRemover.add(jMenuItemRemoverLivro);

        jMenuItemRemoverExemplar.setText("Exemplar");
        jMenuRemover.add(jMenuItemRemoverExemplar);

        jMenuBar.add(jMenuRemover);

        jMenuMisc.setText("Outros");

        jMenuItemMiscCheckout.setText("Checkout");
        jMenuMisc.add(jMenuItemMiscCheckout);

        jMenuItem1.setText("Selecionar Cliente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuMisc.add(jMenuItem1);

        jMenuBar.add(jMenuMisc);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Cliente cliente = ((JListaClientes) this.panelCRUD).getClienteSelecionado().getCliente();
            if (cliente != null) {
                this.cliente = cliente;
                System.out.println("novo cliente: " + cliente.getNomeCliente());
            }
        } catch(ClassCastException cst) {}
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void CadastrarCRUD(ActionEvent evt) {
        
        /* DEBUG */ System.out.println("cadastrarCRUD: " + evt.getActionCommand().toLowerCase());
        
        switch (evt.getActionCommand().toLowerCase()) {
            case "usuário":
                setJanela(JanelaCRUD.CadastroUsuario);
                break;
            case "cliente":
                setJanela(JanelaCRUD.CadastroCliente);
                break;
            case "categoria":
                setJanela(JanelaCRUD.CadastroCategoria);
                break;
            case "livro":
                setJanela(JanelaCRUD.CadastroLivro);
                break;
            case "exemplar":
                setJanela(JanelaCRUD.CadastroExemplar);
                break;
        }
        
    }
    private void ListarCRUD(ActionEvent evt) {
    
        switch (evt.getActionCommand().toLowerCase()) {
            case "usuário":
                setJanela(JanelaCRUD.ListarUsuario);
                break;
            case "cliente":
                setJanela(JanelaCRUD.ListarCliente);
                break;
            case "categoria":
                setJanela(JanelaCRUD.ListarCategoria);
                break;
            case "livro":
                setJanela(JanelaCRUD.ListarLivro);
                break;
            case "exemplar":
                setJanela(JanelaCRUD.ListarExemplar);
                break;
            case "empréstimo":
                setJanela(JanelaCRUD.ListarEmprestimo);
                break;
            case "multa":
                setJanela(JanelaCRUD.ListarMulta);
                break;
        }
        
    }
    private void RemoverCRUD(ActionEvent evt) {
        
        switch (evt.getActionCommand().toLowerCase()) {
            case "usuário":
                setJanela(JanelaCRUD.RemoverUsuario);
                break;
            case "cliente":
                setJanela(JanelaCRUD.RemoverCliente);
                break;
            case "categoria":
                setJanela(JanelaCRUD.RemoverCategoria);
                break;
            case "livro":
                setJanela(JanelaCRUD.RemoverLivro);
                break;
            case "exemplar":
                setJanela(JanelaCRUD.RemoverExemplar);
                break;
        }
    }
    private void AlterarCRUD(ActionEvent evt) {
        
        switch (evt.getActionCommand().toLowerCase()) {
            case "usuário":
                setJanela(JanelaCRUD.AlterarUsuario);
                break;
            case "cliente":
                setJanela(JanelaCRUD.AlterarCliente);
                break;
            case "categoria":
                setJanela(JanelaCRUD.AlterarCategoria);
                break;
            case "livro":
                setJanela(JanelaCRUD.AlterarLivro);
                break;
            case "exemplar":
                setJanela(JanelaCRUD.AlterarExemplar);
                break;
        }
    }
    
    private void MiscCRUD(ActionEvent evt) {
        
        switch (evt.getActionCommand().toLowerCase()) {
            case "checkout":
                setJanela(JanelaCRUD.CadastroEmprestimo);
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                UsuarioDAO dao;
                try {
                    dao = new UsuarioDAO();
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(null, "não foi possível conectar com o banco",
                        "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                final JComponent[] inputs = new JComponent[] {
                    new JLabel("Usuário"), new JTextField(),
                    new JLabel("Senha"), new JPasswordField()
                };
                
                Usuario usuario = null;
                while (true) {
                    // ler usuário e senha
                    int resultado = JOptionPane.showConfirmDialog(null, inputs,
                        "Login", JOptionPane.PLAIN_MESSAGE);    
                    
                    // cancelamento
                    if (resultado != JOptionPane.OK_OPTION) return;
                    
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
                    
                    usuario = dao.procurarUsuarioPorNome(nome);
                    if (usuario != null) {
                        
                        System.out.println(senhaHash);
                        System.out.println(usuario.getSenhaUsuario());
                        
                        if (senhaHash.trim().equals(usuario.getSenhaUsuario().trim())) {
                            System.out.println("LOGIN");
                            break;
                        }
                    }
                    
                    JOptionPane.showMessageDialog(null, "usuário ou senha incorretos",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
                
                if (usuario != null) {
                    new JMain().setVisible(true);
                    getInstancia().usuario = usuario;
                    // getInstancia().setJanela(JanelaCRUD.ListarLivro);
                }
            }
        });
    }
    
    // ==================== Gerenciadores de Janela =====================
    
    private IPanelCRUD gerarJanelaCRUD(JanelaCRUD janela) {
        
        IPanelCRUD panel = null;
        switch (janela) {
            
            case CadastroUsuario:
                return new JCadastroUsuario();
            case CadastroCliente:
                return new JCadastroCliente();
            case CadastroCategoria:
                return new JCadastroCategoria();
            case CadastroLivro:
                return new JCadastroLivro();
            case CadastroExemplar:
                return new JCadastroExemplar();
            case CadastroEmprestimo:
            case Checkout:
                panel = new CheckoutJPanel();
                ((CheckoutJPanel) panel).inserirLivros(livros);
                return panel;
            // vindo da janela de conta do usuário ou da lista de emprestimos
            case CadastroMulta:
                // TODO: adicionar código
                break;
            
            case ListarUsuario:
                panel = new JListaUsuarios();
                ((JListaUsuarios) panel).carregar();
                return panel;
            case ListarCliente:
                panel = new JListaClientes();
                ((JListaClientes) panel).carregar();
                ((JListaClientes) panel).setObservarSelecao(true);
                return panel;
            case ListarCategoria:
                panel = new JListaCategorias();
                ((JListaCategorias) panel).carregar();
                return panel;
            case ListarExemplar:
                panel = new JListaExemplares();
                ((JListaExemplares) panel).carregar();
                return panel;
            case ListarLivro:
                panel = new JListaLivros();
                ((JListaLivros) panel).carregar();
                return panel;
            case ListarEmprestimo:
                panel = new JListaEmprestimos();
                ((JListaEmprestimos) panel).carregar();
                return panel;
            case ListarMulta:
                panel = new JListaMultas();
                ((JListaMultas) panel).carregar();
                return panel;
                
            case AlterarUsuario:
                panel = new JListaUsuarios();
                ((JListaUsuarios) panel).addObservadorSelecao(
                    new JListaUsuarios.ObservadorSelecao() {
                        @Override
                        public void selecao(JDadosUsuario usuario) {
                            if (usuario != null) {
                                IPanelCRUD panel = new JCadastroUsuario(usuario.getUsuario());
                                setJanelaCRUD(panel);
                            }
                        }
                    }
                );
                ((JListaUsuarios) panel).setObservarSelecao(true);
                ((JListaUsuarios) panel).carregar();
                this.mensagemCRUD = new MessageDialogPrimer("selecione um usuário", "Aviso");
                return panel;
            case AlterarCliente:
                panel = new JListaClientes();
                ((JListaClientes) panel).addObservadorSelecao(
                    new JListaClientes.ObservadorSelecao() {
                        @Override
                        public void selecao(JDadosCliente cliente) {
                            if (cliente != null) {
                                IPanelCRUD panel = new JCadastroCliente(cliente.getCliente());
                                setJanelaCRUD(panel);
                            }
                        }
                    }
                );
                ((JListaClientes) panel).setObservarSelecao(true);
                ((JListaClientes) panel).carregar();
                this.mensagemCRUD = new MessageDialogPrimer("selecione um cliente", "Aviso");
                return panel;
            case AlterarCategoria:
                panel = new JListaCategorias();
                ((JListaCategorias) panel).addObservadorSelecao(
                    new JListaCategorias.ObservadorSelecao() {
                        @Override
                        public void selecao(JDadosCategoria categoria) {
                            if (cliente != null) {
                                IPanelCRUD panel = new JCadastroCategoria(categoria.getCategoria());
                                setJanelaCRUD(panel);
                            }
                        }
                    }
                );
                ((JListaCategoria) panel).setObservarSelecao(true);
                ((JListaCategoria) panel).carregar();
                this.mensagemCRUD = new MessageDialogPrimer("selecione uma categoria", "Aviso");
                return panel;
            case AlterarExemplar:
                return null;
            case AlterarLivro:
                return null;
            case AlterarEmprestimo:
                return null;
            case AlterarMulta:
                return null;
        }
        
        return null;
    }
    
    public boolean setJanela(JanelaCRUD janela) {
        
        IPanelCRUD panelCRUD = gerarJanelaCRUD(janela);
        if (panelCRUD == null) return false;
        
        setJanelaCRUD(panelCRUD);
        return true;
    }
    
    private void setJanelaCRUD(IPanelCRUD panelCRUD) {
        
        if (this.mensagemCRUD != null) {
            this.mensagemCRUD.prime();
            this.mensagemCRUD = null;
        }
        
        JPanel panel = (JPanel) panelCRUD;
        
        if (panelCRUD.mostrarComoPopup()) {
            this.framePopup = new JFrame(panelCRUD.getTituloCRUD());
            this.framePopup.add(panel);
            this.framePopup.pack();
            this.framePopup.setVisible(true);
        } else {
            popJanelaCRUD();
            
            // muda o titulo da janela
            this.setTitle(panelCRUD.getTituloCRUD());
            
            // adição da janela e configuração do layout
            jPanelContent.add(panel, panelCRUD.getTituloCRUD());
            CardLayout layout = (CardLayout) jPanelContent.getLayout();
            layout.first(jPanelContent);
            
            this.panelCRUD = panelCRUD;
            
            /* // DEBUG START
            System.out.println(
                panel.getMinimumSize().toString() + '\n' +
                panel.getMaximumSize().toString() + '\n' +
                panel.getPreferredSize().toString());
            */ // DEBUG END
            
            // configuração do tamanho do painel
            jPanelContent.setMinimumSize(panel.getMinimumSize());
            jPanelContent.setMaximumSize(panel.getMaximumSize());
            jPanelContent.setPreferredSize(panel.getPreferredSize());
            pack();
        }
    }
    
    
    /**
     * 
     */
    public boolean pushJanelaCRUD(JanelaCRUD janela) {
        
        IPanelCRUD panel = gerarJanelaCRUD(janela);
        if (panel == null || panel.mostrarComoPopup()) return false;
        
        this.setTitle(panel.getTituloCRUD());
        jPanelContent.add((JPanel) panel, panel.getTituloCRUD());
        CardLayout layout = (CardLayout) jPanelContent.getLayout();
        layout.last(jPanelContent);

        return true;
    }
    
    /**
     * remove a janela CRUD em exibição atual e mostra a próxima (caso exista)
     */
    public void popJanelaCRUD() {
        
        if (this.framePopup != null && this.framePopup.isDisplayable()) {
            this.framePopup.dispose();
            this.framePopup = null;
        } else {
            int cnt = jPanelContent.getComponentCount();
            if (cnt > 0) {
                jPanelContent.remove(0);
            }
            if (cnt > 1) {
                this.setTitle(
                    ((IPanelCRUD) jPanelContent.getComponent(0)).getTituloCRUD()
                );
            }
        }
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
    private javax.swing.JMenu jMenuAlterar;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuCadastrar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAlterarCategoria;
    private javax.swing.JMenuItem jMenuItemAlterarCliente;
    private javax.swing.JMenuItem jMenuItemAlterarExemplar;
    private javax.swing.JMenuItem jMenuItemAlterarLivro;
    private javax.swing.JMenuItem jMenuItemAlterarUsuario;
    private javax.swing.JMenuItem jMenuItemCadastrarCategoria;
    private javax.swing.JMenuItem jMenuItemCadastrarCliente;
    private javax.swing.JMenuItem jMenuItemCadastrarExemplar;
    private javax.swing.JMenuItem jMenuItemCadastrarLivro;
    private javax.swing.JMenuItem jMenuItemCadastrarUsuario;
    private javax.swing.JMenuItem jMenuItemListarCategoria;
    private javax.swing.JMenuItem jMenuItemListarCliente;
    private javax.swing.JMenuItem jMenuItemListarEmprestimo;
    private javax.swing.JMenuItem jMenuItemListarExemplar;
    private javax.swing.JMenuItem jMenuItemListarLivro;
    private javax.swing.JMenuItem jMenuItemListarMulta;
    private javax.swing.JMenuItem jMenuItemListarUsuario;
    private javax.swing.JMenuItem jMenuItemMiscCheckout;
    private javax.swing.JMenuItem jMenuItemRemoverCategoria;
    private javax.swing.JMenuItem jMenuItemRemoverCliente;
    private javax.swing.JMenuItem jMenuItemRemoverExemplar;
    private javax.swing.JMenuItem jMenuItemRemoverLivro;
    private javax.swing.JMenuItem jMenuItemRemoverUsuario;
    private javax.swing.JMenu jMenuListar;
    private javax.swing.JMenu jMenuMisc;
    private javax.swing.JMenu jMenuRemover;
    private javax.swing.JPanel jPanelContent;
    // End of variables declaration//GEN-END:variables
}
