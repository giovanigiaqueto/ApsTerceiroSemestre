package widget.listas;

// awt
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.Point;

// swing
import javax.swing.JPanel;
import javax.swing.border.Border;

// java util
import java.util.List;

// dao
import dao.ClienteDAO;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.BorderFactory;

// modelos
import model.Cliente;

// widget
import widget.dados.JDadosCliente;
import widget.dados.JDadosEmprestimo;

// model
import widget.support.IPanelCRUD;

public class JListaClientes extends javax.swing.JPanel implements IListaDados, IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Lista de Clientes"; }
    
    // se está observando seleções
    private boolean observarSelecao;
    
    // observador de seleção (MouseClick event handler)
    private MouseAdapter observadorSelecao;
    
    private JDadosCliente clienteSelecionado; // cliente selecionado 
    private Border        bordaSalva;         // borda normal do livro selecionado
    private Border        bordaSelecao;       // borda do livro selecionado
    
    public interface ObservadorSelecao {
        public void selecao(JDadosCliente cliente);
    };
    
    private List<ObservadorSelecao> observadoresSelecao;
    
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
        
        observadoresSelecao = new LinkedList<ObservadorSelecao>();
        
        // borda de seleção
        bordaSelecao = BorderFactory.createLineBorder(Color.orange);
        
        // observador de seleção
        observadorSelecao = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                // DEBUG System.out.println("observador executado");
                JPanel panel = (JPanel) bsearchComponente(ev.getPoint());
                // verifica se um livro foi selecionado
                if (panel != null) {
                    // reseta a borda do livro que estava selecionado
                    if (clienteSelecionado != null) {
                        clienteSelecionado.setBorder(bordaSalva);
                    }
                    // salva a borda do livro que foi selecionado
                    // e troca sua borda autal pela borda de seleção
                    bordaSalva = panel.getBorder();
                    panel.setBorder(bordaSelecao);
                    
                    // salva o exemplar selecionado
                    clienteSelecionado = (JDadosCliente) panel;
                    
                    // executa todos os observadores de selecao
                    for (ObservadorSelecao obs : observadoresSelecao) {
                        obs.selecao(clienteSelecionado);
                    }
                }
            }
        };
    }
    
    public void inserirClientes(List<Cliente> clientes) {
        Dimension dim = jPanelClientes.getPreferredSize();
        
        final int h_gap = 10;
        
        for (Cliente p : clientes) {
            JPanel panel = new JPanel();
            JDadosCliente cliente = new JDadosCliente(p);
            panel.add(cliente);
            Dimension tmp = cliente.getPreferredSize();
            
            tmp.height += h_gap;
            panel.setPreferredSize(tmp);
            jPanelClientes.add(panel);
            
            dim.height += tmp.height;
            dim.width = (tmp.width > dim.width ? tmp.width:dim.width);
        }
        jPanelClientes.setPreferredSize(dim);
        jPanelClientes.revalidate();
    }
    
    /**
     * procura o componente na localização dada, retorna null caso não exista
     * 
     * implementação: procura binaria, seguido de procura de colisão
     * 
     * @param pt localização relativa a jPanelLivros
     * @return o exmplar na localização
     */
    private Component bsearchComponente(Point pt) {
        int len = jPanelClientes.getComponentCount();
        int idx = 0;
        do {
            Component comp = jPanelClientes.getComponent(idx);
            Point pos = comp.getLocation();
            Dimension dim = comp.getSize();
            len /= 2;
            // DEBUG System.out.println("curr line: " + idx);
            if (pt.y < pos.y) {
                // falha, procurar na metade de baixo
                // DEBUG System.out.println("dec");
                idx -= len;
            } else if (pt.y >= pos.y + dim.height) {
                // falha, procurar na metade de cima
                // DEBUG System.out.println("inc");
                idx += len;
            } else {
                // linha encontrada, retorna o componente dentro da linha que colidir
                Component p = comp.getComponentAt(
                    new Point(pt.x - pos.x, pt.y - pos.y)
                );
                if (p != comp) {
                    return p;
                }
            }
        } while (len > 0);
        
        // linha não encontrada, retorna o componente na ultima linha se colidir
        Component linha = jPanelClientes.getComponent(jPanelClientes.getComponentCount() - 1);
        Point pos = linha.getLocation();
        
        Component p = linha.getComponentAt(
            new Point(pt.x - pos.x, pt.y - pos.y)
        );
        if (p != linha) {
            return p;
        }
        
        return null;
    }
    
    public JDadosCliente getClienteSelecionado() {
        return clienteSelecionado;
    }
    
    public void setObservarSelecao(boolean observarSelecao) {
        if (this.observarSelecao == observarSelecao) return;
        
        if (observarSelecao) {
            jPanelClientes.addMouseListener(observadorSelecao);
        } else {
            jPanelClientes.removeMouseListener(observadorSelecao);
        }
        this.observarSelecao = observarSelecao;
    }
    
    public boolean getObservarSelecao() {
        return this.observarSelecao;
    }
    
    public void addObservadorSelecao(ObservadorSelecao obs) {
        if (observadoresSelecao != null && obs != null) {
            observadoresSelecao.add(obs);
        }
    }
    public void removeObservadorSelecao(ObservadorSelecao obs) {
        if (observadoresSelecao != null && obs != null) {
            observadoresSelecao.remove(obs);
        }
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
