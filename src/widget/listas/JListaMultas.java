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
import dao.MultaDAO;

// modelos
import model.Multa;

// widget
import widget.dados.JDadosMulta;

// suporte
import widget.support.IPanelCRUD;

public class JListaMultas extends javax.swing.JPanel implements IListaDados, IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Lista de Multas"; }
    
    // se está observando seleções
    private boolean observarSelecao;
    
    private JDadosMulta multaSelecionada; // multa selecionada
    private Border      bordaSalva;       // borda normal do livro selecionado
    private Border      bordaSelecao;     // borda do livro selecionado
    
    // observador de seleção (MouseClick event handler)
    private MouseAdapter observadorSelecao;
    
    public interface ObservadorSelecao {
        public void selecao(JDadosMulta multa);
    };
    
    private List<ObservadorSelecao> observadoresSelecao;
    
    
    /**
     * Creates new form JListaLivros
     */
    public JListaMultas() {
        initComponents();
        init();
    }
    
    private void init() {
        Dimension dim = jPanelMultas.getPreferredSize();
        dim.height = 0;
        jPanelMultas.setPreferredSize(dim);
        
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
                    if (multaSelecionada != null) {
                        multaSelecionada.setBorder(bordaSalva);
                    }
                    // salva a borda do livro que foi selecionado
                    // e troca sua borda autal pela borda de seleção
                    bordaSalva = panel.getBorder();
                    panel.setBorder(bordaSelecao);
                    
                    // salva o exemplar selecionado
                    multaSelecionada = (JDadosMulta) panel;
                    
                    // executa todos os observadores de selecao
                    for (JListaMultas.ObservadorSelecao obs : observadoresSelecao) {
                        obs.selecao(multaSelecionada);
                    }
                }
            }
        };
    }
    
    public void inserirMultas(List<Multa> multas) {
        Dimension dim = jPanelMultas.getPreferredSize();
        
        final int w_gap = 10;
        final int h_gap = 10;
        
        for (Multa p : multas) {
            JPanel panel = new JPanel();
            JDadosMulta multa = new JDadosMulta(p);
            panel.add(multa);
            
            Dimension tmp = multa.getPreferredSize();
            tmp.width  += w_gap;
            tmp.height += h_gap;
            panel.setPreferredSize(tmp);
            
            jPanelMultas.add(panel);
            dim.height += tmp.height;
        }
        
        jPanelMultas.setPreferredSize(dim);
        jPanelMultas.revalidate();
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
        int len = jPanelMultas.getComponentCount();
        int idx = 0;
        do {
            Component comp = jPanelMultas.getComponent(idx);
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
        return null;
    }
    
    public JDadosMulta getMultaSelecionada() {
        return multaSelecionada;
    }
    
    public void setObservarSelecao(boolean observarSelecao) {
        if (this.observarSelecao == observarSelecao) return;
        
        if (observarSelecao) {
            jPanelMultas.addMouseListener(observadorSelecao);
        } else {
            jPanelMultas.removeMouseListener(observadorSelecao);
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
     * carrega as multas do banco na lista se ela estiver vazia e
     * retorna se o conteúdo foi alterado
     * 
     * @return true se o conteúdo da lista for alterado, do contrario false
     */
    @Override
    public boolean carregar() {
        // previne duplicação de dados
        if (jPanelMultas.getComponentCount() > 0) return false;
        
        // carrega os dados
        MultaDAO dao = new MultaDAO();
        inserirMultas(dao.listarMultas());
        return true;
    }
    
    /**
     * carrega mais multas do banco de dados, inserindo-os na lista
     * 
     * @param contagem número de multas para carregar
     */
    @Override
    public void carregar(int contagem) {
        MultaDAO dao = new MultaDAO();
        inserirMultas(dao.listarMultas(this.comprimento(), contagem));
    }
    
    /**
     * remove todos as multas da lista, tornando-a vazia
     */
    @Override
    public void esvaziar() {
        jPanelMultas.removeAll();
        jPanelMultas.setPreferredSize(
            new Dimension(jPanelMultas.getPreferredSize().width, 0)
        );
    }
    
    /**
     * retorna o comprimento da lista em número de multas
     * 
     * @return o comprimento da lista
     */
    @Override
    public int comprimento() {
        return jPanelMultas.getComponentCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneMultas = new javax.swing.JScrollPane();
        jPanelMultas = new javax.swing.JPanel();
        jBarraPesquisaSimples1 = new widget.JBarraPesquisaSimples();

        setPreferredSize(new java.awt.Dimension(566, 600));

        jScrollPaneMultas.setMinimumSize(new java.awt.Dimension(537, 22));
        jScrollPaneMultas.setPreferredSize(new java.awt.Dimension(542, 533));

        jPanelMultas.setPreferredSize(new java.awt.Dimension(520, 520));
        jPanelMultas.setLayout(new javax.swing.BoxLayout(jPanelMultas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneMultas.setViewportView(jPanelMultas);

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
                    .addComponent(jScrollPaneMultas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneMultas, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.JBarraPesquisaSimples jBarraPesquisaSimples1;
    private javax.swing.JPanel jPanelMultas;
    private javax.swing.JScrollPane jScrollPaneMultas;
    // End of variables declaration//GEN-END:variables
}
