package widget.listas;

// awt
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Point;

// swing
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

// java util
import java.util.List;
import java.util.LinkedList;

// dao
import dao.UsuarioDAO;

// modelos
import model.Usuario;

// widget
import widget.dados.JDadosUsuario;

// suporte
import widget.support.IPanelCRUD;

public class JListaUsuarios extends javax.swing.JPanel implements IListaDados, IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Lista de Usuários"; }
    
    // se está observando seleções
    private boolean observarSelecao;
    
    // observador de seleção (MouseClick event handler)
    private MouseAdapter observadorSelecao;
    
    private JDadosUsuario usuarioSelecionado; // usuário selecionado 
    private Border        bordaSalva;         // borda normal do livro selecionado
    private Border        bordaSelecao;       // borda do livro selecionado
    
    public interface ObservadorSelecao {
        public void selecao(JDadosUsuario usuario);
    };
    
    private List<ObservadorSelecao> observadoresSelecao;
    
    /**
     * Creates new form JListaLivros
     */
    public JListaUsuarios() {
        initComponents();
        init();
    }
    
    private void init() {
        Dimension dim = jPanelUsuarios.getPreferredSize();
        dim.height = 0;
        jPanelUsuarios.setPreferredSize(dim);
        
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
                    if (usuarioSelecionado != null) {
                        usuarioSelecionado.setBorder(bordaSalva);
                    }
                    // salva a borda do livro que foi selecionado
                    // e troca sua borda autal pela borda de seleção
                    bordaSalva = panel.getBorder();
                    panel.setBorder(bordaSelecao);
                    
                    // salva o exemplar selecionado
                    usuarioSelecionado = (JDadosUsuario) panel;
                    
                    System.out.println("teste");
                    
                    // executa todos os observadores de selecao
                    for (ObservadorSelecao obs : observadoresSelecao) {
                        obs.selecao(usuarioSelecionado);
                    }
                }
            }
        };
    }
    
    public void inserirUsuarios(List<Usuario> usuarios) {
        Dimension dim = jPanelUsuarios.getPreferredSize();
        
        final int h_gap = 10;
        
        for (Usuario p : usuarios) {
            JPanel panel = new JPanel();
            JDadosUsuario usuario = new JDadosUsuario(p);
            panel.add(usuario);
            Dimension tmp = usuario.getPreferredSize();
            
            tmp.height += h_gap;
            panel.setPreferredSize(tmp);
            jPanelUsuarios.add(panel);
            
            dim.height += tmp.height;
            dim.width = (tmp.width > dim.width ? tmp.width:dim.width);
        }
        jPanelUsuarios.setPreferredSize(dim);
        jPanelUsuarios.revalidate();
    }
    
    /**
     * procura o componente na localização dada, retorna null caso não exista
     * 
     * implementação: procura binaria, seguido de procura de colisão
     * 
     * @param pt localização relativa a jPanelUsuarios
     * @return o usuário na localização
     */
    private Component bsearchComponente(Point pt) {
        int len = jPanelUsuarios.getComponentCount();
        int idx = 0;
        do {
            Component comp = jPanelUsuarios.getComponent(idx);
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
        Component linha = jPanelUsuarios.getComponent(jPanelUsuarios.getComponentCount() - 1);
        Point pos = linha.getLocation();
        
        Component p = linha.getComponentAt(
            new Point(pt.x - pos.x, pt.y - pos.y)
        );
        if (p != linha) {
            return p;
        }
        
        return null;
    }
    
    public JDadosUsuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }
    
    public void setObservarSelecao(boolean observarSelecao) {
        if (this.observarSelecao == observarSelecao) return;
        
        if (observarSelecao) {
            jPanelUsuarios.addMouseListener(observadorSelecao);
        } else {
            jPanelUsuarios.removeMouseListener(observadorSelecao);
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
        if (jPanelUsuarios.getComponentCount() > 0) return false;
        
        // carrega os dados
        UsuarioDAO dao = new UsuarioDAO();
        inserirUsuarios(dao.listarUsuarios());
        return true;
    }
    
    /**
     * carrega mais usuários do banco de dados, inserindo-os na lista
     * 
     * @param contagem número de usuários para carregar
     */
    @Override
    public void carregar(int contagem) {
        UsuarioDAO dao = new UsuarioDAO();
        inserirUsuarios(dao.listarUsuarios(this.comprimento(), contagem));
    }
    
    /**
     * remove todos os usuários da lista, tornando-a vazia
     */
    @Override
    public void esvaziar() {
        jPanelUsuarios.removeAll();
        jPanelUsuarios.setPreferredSize(
            new Dimension(jPanelUsuarios.getPreferredSize().width, 0)
        );
    }
    
    /**
     * retorna o comprimento da lista em número de usuários
     * 
     * @return o comprimento da lista
     */
    @Override
    public int comprimento() {
        return jPanelUsuarios.getComponentCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneUsuarios = new javax.swing.JScrollPane();
        jPanelUsuarios = new javax.swing.JPanel();
        jBarraPesquisaSimples1 = new widget.JBarraPesquisaSimples();

        setPreferredSize(new java.awt.Dimension(566, 600));

        jScrollPaneUsuarios.setMinimumSize(new java.awt.Dimension(542, 22));
        jScrollPaneUsuarios.setPreferredSize(new java.awt.Dimension(542, 533));

        jPanelUsuarios.setPreferredSize(new java.awt.Dimension(524, 530));
        jPanelUsuarios.setLayout(new javax.swing.BoxLayout(jPanelUsuarios, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneUsuarios.setViewportView(jPanelUsuarios);

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
                    .addComponent(jScrollPaneUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.JBarraPesquisaSimples jBarraPesquisaSimples1;
    private javax.swing.JPanel jPanelUsuarios;
    private javax.swing.JScrollPane jScrollPaneUsuarios;
    // End of variables declaration//GEN-END:variables
}
