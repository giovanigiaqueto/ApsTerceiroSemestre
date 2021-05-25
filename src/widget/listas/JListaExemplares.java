package widget.listas;

// java util
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

// java awt
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

// java swing
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JPanel;

// dao
import dao.ExemplarDAO;

// modelos
import model.Exemplar;

// widget.dados
import widget.dados.JDadosExemplar;

public class JListaExemplares extends javax.swing.JPanel implements IListaDados {
    
    private static final int EXEMPLARES_POR_LINHA = 2;
    
    private JPanel _jPanelLinhaExemplarRef;
    
    // se está observando seleções
    private boolean observarSelecao;
    
    // observador de seleção (MouseClick event handler)
    private MouseAdapter observadorSelecao;
    
    private JDadosExemplar exemplarSelecionado; // exemplar selecionado 
    private Border         bordaSalva;          // borda normal do exemplar selecionado
    private Border         bordaSelecao;        // borda do exemplar selecionado
    
    public interface ObservadorSelecao {
        public void selecao(JDadosExemplar exemplar);
    };
    
    private List<ObservadorSelecao> observadoresSelecao;
    
    /**
     * Creates new form JListaLivros
     */
    public JListaExemplares() {
        initComponents();
        init();
    }
    
    private void init() {
        Dimension dim = jPanelExemplares.getPreferredSize();
        dim.height = 0;
        jPanelExemplares.setPreferredSize(dim);
        
        bordaSelecao = BorderFactory.createLineBorder(Color.orange);
        
        observadoresSelecao = new LinkedList<ObservadorSelecao>();
        
        observadorSelecao = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                // DEBUG System.out.println("observador executado");
                JDadosExemplar exemplar = (JDadosExemplar) bsearchComponente(ev.getPoint());
                
                // verifica se um exemplar foi selecionado
                if (exemplar != null) {
                    __innerSelecionar(exemplar);
                }
            }
        };
    }
    
    private void __innerSelecionar(JDadosExemplar exemplar) {
        // reseta a borda do exemplar que estava selecionado
        if (exemplarSelecionado != null) {
            exemplarSelecionado.setBorder(bordaSalva);
        }
        // salva a borda do exemplar que foi selecionado
        // e troca sua borda autal pela borda de seleção
        bordaSalva = exemplar.getBorder();
        exemplar.setBorder(bordaSelecao);

        // salva o exemplar selecionado
        exemplarSelecionado = exemplar;

        for (ObservadorSelecao obs : observadoresSelecao) {
            obs.selecao(exemplarSelecionado);
        }
    }
    
    public void inserirExemplares(List<Exemplar> exemplares) {
        Dimension dim = jPanelExemplares.getPreferredSize();
        
        int w_gap = 20;
        int h_gap = 5;
        
        // itera de dois em dois se possível e dispõe as categorias lado a lado
        // (categorias sem par são mostradas na esquerda com um espaço em branco na direita)
        Iterator<Exemplar> iter = exemplares.iterator();
        JPanel panel = null;
        if (iter.hasNext()) {
            if (this._jPanelLinhaExemplarRef != null) {
                int i = 0;
                do {
                    this._jPanelLinhaExemplarRef.add(new JDadosExemplar(iter.next()), i);
                } while ((--i < EXEMPLARES_POR_LINHA) && iter.hasNext());
                this._jPanelLinhaExemplarRef = null;
            }
        } else {
            // nada adicionado, retorna para evitar revalidação desnecessaria do swing
            return;
        }
        while (iter.hasNext()) {
            panel = new JPanel();
            panel.setLayout(new FlowLayout());
            
            // cria um JPanel com duas categorias se possível, ou uma se tiver no fim do loop
            JDadosExemplar exm0 = new JDadosExemplar(iter.next());
            panel.add(exm0, 0);
            Dimension minDim  = exm0.getMinimumSize();
            Dimension prefDim = exm0.getPreferredSize();
            for (int i = 1; i < EXEMPLARES_POR_LINHA && iter.hasNext(); ++i) {
                JDadosExemplar exm1 = new JDadosExemplar(iter.next());
                panel.add(exm1, i);
                
                Dimension tmpMinDim = exm1.getMinimumSize();
                Dimension tmpPrefDim = exm1.getPreferredSize();
                
                minDim.width  += tmpMinDim.width + w_gap;
                prefDim.width += tmpPrefDim.width + w_gap;
                
                minDim.height  =
                    tmpMinDim.height > minDim.height ?
                        tmpMinDim.height : minDim.height;
                prefDim.height = 
                    tmpPrefDim.height > prefDim.height ?
                        tmpPrefDim.height : prefDim.height;
            }
            
            // atualiza as dimensões
            dim.height += prefDim.height + h_gap;
            dim.width = (prefDim.width > dim.width ? prefDim.width : dim.width);
            dim.width = (minDim.width > dim.width ? minDim.width : dim.width);
            
            // configura o tamanho do panel
            panel.setMinimumSize(minDim);
            panel.setPreferredSize(prefDim);
            
            // adiciona o panel
            jPanelExemplares.add(panel);
        }
        // atualiza a referencia ao ultimo panel
        this._jPanelLinhaExemplarRef = panel;
        
        jPanelExemplares.setPreferredSize(dim);
        jPanelExemplares.revalidate();
    }
    
    /**
     * retorna o exemplar no indice dado, pode gerar uma exceção
     * 
     * @param indice o indice do exmplar
     * @return o exemplar no dado indice
     */
    public JDadosExemplar getExemplar(int indice) {
        JPanel panel = (JPanel) jPanelExemplares.getComponent(indice / EXEMPLARES_POR_LINHA);
        return (JDadosExemplar) panel.getComponent(indice % EXEMPLARES_POR_LINHA);
    }
    
    /**
     * procura o componente na localização dada, retorna null caso não exista
     * 
     * implementação: procura binaria da linha, seguido de procura de colisão
     * 
     * @param pt localização relativa a jPanelLivros
     * @return o exmplar na localização
     */
    private Component bsearchComponente(Point pt) {
        int len = jPanelExemplares.getComponentCount();
        int idx = 0;
        do {
            Component comp = jPanelExemplares.getComponent(idx);
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
    
    public void setObservarSelecao(boolean observarSelecao) {
        if (this.observarSelecao == observarSelecao) return;
        
        if (observarSelecao) {
            jPanelExemplares.addMouseListener(observadorSelecao);
        } else {
            jPanelExemplares.removeMouseListener(observadorSelecao);
        }
        this.observarSelecao = observarSelecao;
    }
    
    public boolean getObservarSelecao() {
        return this.observarSelecao;
    }
    
    public JDadosExemplar getExemplarSelecionado() {
        return this.exemplarSelecionado;
    }
    
    public void addObservadorSelecao(ObservadorSelecao obs) {
        if (observadoresSelecao != null) observadoresSelecao.add(obs);
    }
    public void removeObservadorSelecao(ObservadorSelecao obs) {
        if (observadoresSelecao != null) observadoresSelecao.remove(obs);
    }

    /**
     * seleciona o exemplar pelo id do seu exemplar
     * 
     * @param id_exemplar o id do exemplar
     */
    public void selecionar(int id_exemplar) {
        for (Component c1 : jPanelExemplares.getComponents()) {
            for (Component c2 : ((JPanel) c1).getComponents()) {
                JDadosExemplar ex = (JDadosExemplar) c2;
                if (ex.getExemplar().getIdExemplar() == id_exemplar) {
                    __innerSelecionar(ex);
                    return;
                }
            }
        }
    }
    
    // ==================== implements JListaDados ====================
    
    /**
     * carrega os exemplares do banco na lista se ela estiver vazia e
     * retorna se o conteúdo foi alterado
     * 
     * @return true se o conteúdo da lista for alterado, do contrario false
     */
    @Override
    public boolean carregar() {
        // previne duplicação de dados
        if (jScrollPaneExemplares.getComponentCount() > 0) return false;
        
        // carrega os dados
        ExemplarDAO dao = new ExemplarDAO();
        inserirExemplares(dao.listarExemplares());
        return true;
    }
    
    /**
     * carrega mais exemplares do banco de dados, inserindo-os na lista
     * 
     * @param contagem número de items para carregar
     */
    @Override
    public void carregar(int contagem) {
        ExemplarDAO dao = new ExemplarDAO();
        inserirExemplares(dao.listarExemplares(this.comprimento(), contagem));
    }
    
    /**
     * remove todos os exemplares da lista, tornando-a vazia
     */
    @Override
    public void esvaziar() {
        jPanelExemplares.removeAll();
        
        _jPanelLinhaExemplarRef = null;
        exemplarSelecionado = null;
        bordaSalva = null;
        
        // reseta o comprimento do conteudo dentro do ScrollPane
        jPanelExemplares.setPreferredSize(
            new Dimension(jPanelExemplares.getPreferredSize().width, 0)
        );
    }
    
    /**
     * retorna o comprimento da lista em número de exemplares
     * 
     * @return o comprimento da lista
     */
    @Override
    public int comprimento() {
        int cnt = jPanelExemplares.getComponentCount() * EXEMPLARES_POR_LINHA;
        if (_jPanelLinhaExemplarRef != null) {
            cnt += _jPanelLinhaExemplarRef.getComponentCount() - EXEMPLARES_POR_LINHA;
        }
        return cnt;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneExemplares = new javax.swing.JScrollPane();
        jPanelExemplares = new javax.swing.JPanel();
        jBarraPesquisaSimples1 = new widget.JBarraPesquisaSimples();

        setPreferredSize(new java.awt.Dimension(602, 600));

        jScrollPaneExemplares.setPreferredSize(new java.awt.Dimension(542, 533));

        jPanelExemplares.setPreferredSize(new java.awt.Dimension(520, 520));
        jPanelExemplares.setLayout(new javax.swing.BoxLayout(jPanelExemplares, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneExemplares.setViewportView(jPanelExemplares);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneExemplares, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneExemplares, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.JBarraPesquisaSimples jBarraPesquisaSimples1;
    private javax.swing.JPanel jPanelExemplares;
    private javax.swing.JScrollPane jScrollPaneExemplares;
    // End of variables declaration//GEN-END:variables
}
