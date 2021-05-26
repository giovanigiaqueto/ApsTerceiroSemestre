package widget.listas;

// awt
import java.awt.Dimension;

// java util
import java.util.List;

// dao
import dao.ClienteDAO;

// modelos
import model.Cliente;

// widget
import widget.dados.JDadosCliente;

// model
import widget.support.IPanelCRUD;

public class JListaClientes extends javax.swing.JPanel implements IListaDados, IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Lista de Clientes"; }
    
    /**
     * Creates new form JListaLivros
     */
    public JListaClientes() {
        initComponents();
        init();
    }
    
    private void init() {
        Dimension dim = jPanelClientes.getPreferredSize();
        dim.height = 0;
        jPanelClientes.setPreferredSize(dim);
    }
    
    public void inserirClientes(List<Cliente> clientes) {
        Dimension dim = jPanelClientes.getPreferredSize();
        
        for (Cliente p : clientes) {
            JDadosCliente cliente = new JDadosCliente(p);
            jPanelClientes.add(cliente);
            Dimension tmp = cliente.getPreferredSize();
            dim.height += tmp.height;
            dim.width = (tmp.width > dim.width ? tmp.width:dim.width);
        }
        jPanelClientes.setPreferredSize(dim);
        jPanelClientes.revalidate();
    }
    
    // ==================== implements IListaDados ====================
    
    /**
     * carrega os usuários do banco na lista se a lista estiver vazia e
     * retorna se o conteúdo da lista foi alterado
     * 
     * @return true se o conteúdo da lista for alterado, do contrario false
     */
    @Override
    public boolean carregar() {
        // previne duplicação de dados
        if (jPanelClientes.getComponentCount() > 0) return false;
        
        // carrega os dados
        ClienteDAO dao = new ClienteDAO();
        inserirClientes(dao.listarClientes());
        return true;
    }
    
    /**
     * carrega mais usuários do banco de dados, inserindo-os na lista
     * 
     * @param contagem número de usuários para carregar
     */
    @Override
    public void carregar(int contagem) {
        ClienteDAO dao = new ClienteDAO();
        inserirClientes(dao.listarClientes(this.comprimento(), contagem));
    }
    
    /**
     * remove todos os usuários da lista, tornando-a vazia
     */
    @Override
    public void esvaziar() {
        jPanelClientes.removeAll();
        jPanelClientes.setPreferredSize(
            new Dimension(jPanelClientes.getPreferredSize().width, 0)
        );
    }
    
    /**
     * retorna o comprimento da lista em número de usuários
     * 
     * @return o comprimento da lista
     */
    @Override
    public int comprimento() {
        return jPanelClientes.getComponentCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneClientes = new javax.swing.JScrollPane();
        jPanelClientes = new javax.swing.JPanel();
        jBarraPesquisaSimples1 = new widget.JBarraPesquisaSimples();

        setMinimumSize(new java.awt.Dimension(566, 50));
        setPreferredSize(new java.awt.Dimension(566, 600));

        jScrollPaneClientes.setMinimumSize(new java.awt.Dimension(542, 22));
        jScrollPaneClientes.setPreferredSize(new java.awt.Dimension(542, 533));

        jPanelClientes.setPreferredSize(new java.awt.Dimension(524, 530));
        jPanelClientes.setLayout(new javax.swing.BoxLayout(jPanelClientes, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneClientes.setViewportView(jPanelClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPaneClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.JBarraPesquisaSimples jBarraPesquisaSimples1;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JScrollPane jScrollPaneClientes;
    // End of variables declaration//GEN-END:variables
}
