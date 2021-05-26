package widget.listas;

// swing
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

// awt
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;

// awt.event
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// java.util
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

// dao
import dao.EmprestimoDAO;

// modelos
import model.Emprestimo;

// widget
import widget.dados.JDadosEmprestimo;

// suporte
import widget.support.IPanelCRUD;


public class JListaEmprestimos extends javax.swing.JPanel implements IListaDados, IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Lista de Empréstimos"; }
    
    // número (máximo) de empréstimos por linha
    private static final int EMPRESTIMOS_POR_LINHA = 2;
    
    // ultimo panel, que pode conter menos de EMPRESTIMOS_POR_LINHA empréstimos
    private JPanel _jPanelLinhaEmprestimoRef;
    
    // se está observando seleções
    private boolean observarSelecao;
    
    // observador de seleção (MouseClick event handler)
    private MouseAdapter observadorSelecao;
    
    private JDadosEmprestimo emprestimoSelecionado; // livro selecionado 
    private Border           bordaSalva;            // borda normal do livro selecionado
    private Border           bordaSelecao;          // borda do livro selecionado
    
    public interface ObservadorSelecao {
        public void selecao(JDadosEmprestimo emprestimo);
    };
    
    private List<ObservadorSelecao> observadoresSelecao;

    /**
     * Creates new form JListaLivros
     */
    public JListaEmprestimos() {
        initComponents();
        init();
    }
    
    private void init() {
        Dimension dim = jPanelEmprestimos.getPreferredSize();
        dim.height = 0;
        jPanelEmprestimos.setPreferredSize(dim);
        
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
                    if (emprestimoSelecionado != null) {
                        emprestimoSelecionado.setBorder(bordaSalva);
                    }
                    // salva a borda do livro que foi selecionado
                    // e troca sua borda autal pela borda de seleção
                    bordaSalva = panel.getBorder();
                    panel.setBorder(bordaSelecao);
                    
                    // salva o exemplar selecionado
                    emprestimoSelecionado = (JDadosEmprestimo) panel;
                    
                    // executa todos os observadores de selecao
                    for (ObservadorSelecao obs : observadoresSelecao) {
                        obs.selecao(emprestimoSelecionado);
                    }
                }
            }
        };
    }
    /**
     * insere empréstimos na lista, exibindo-os gráficamente
     * 
     * @param emprestimos os empréstimos a serem inseridos
     */
    public void inserirEmprestimos(List<Emprestimo> emprestimos) {
        Dimension dim = jPanelEmprestimos.getPreferredSize();
        
        int w_gap = 10;
        int h_gap = 10;
        
        // itera EMPRESTIMOS_POR_LINHA por vez se possível e dispõe eles lado a lado
        // (os componentes são mostrados de forma centralizada)
        Iterator<Emprestimo> iter = emprestimos.iterator();
        JPanel panel = null;
        if (iter.hasNext()) {
            if (this._jPanelLinhaEmprestimoRef != null) {
                int i = 0;
                do {
                    this._jPanelLinhaEmprestimoRef.add(new JDadosEmprestimo(iter.next()), i);
                } while ((--i < EMPRESTIMOS_POR_LINHA) && iter.hasNext());
                this._jPanelLinhaEmprestimoRef = null;
            }
        } else {
            // nada adicionado, retorna para evitar revalidação desnecessaria do swing
            return;
        }
        while (iter.hasNext()) {
            panel = new JPanel();
            panel.setLayout(new FlowLayout());
            
            // cria um JPanel com EMPRESTIMOS_POR_LINHA emprestimos se possível,
            // ou no mínimo um se tiver no fim do loop
            JDadosEmprestimo emp0 = new JDadosEmprestimo(iter.next());
            panel.add(emp0, 0);
            Dimension minDim  = emp0.getMinimumSize();
            Dimension prefDim = emp0.getPreferredSize();
            for (int i = 1; i < EMPRESTIMOS_POR_LINHA && iter.hasNext(); ++i) {
                JDadosEmprestimo emp1 = new JDadosEmprestimo(iter.next());
                panel.add(emp1, i);
                
                Dimension tmpMinDim = emp1.getMinimumSize();
                Dimension tmpPrefDim = emp1.getPreferredSize();
                
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
            jPanelEmprestimos.add(panel);
        }
        // atualiza a referencia ao ultimo panel
        this._jPanelLinhaEmprestimoRef = panel;
        
        jPanelEmprestimos.setPreferredSize(dim);
        jPanelEmprestimos.revalidate();
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
        int len = jPanelEmprestimos.getComponentCount();
        int idx = 0;
        do {
            Component comp = jPanelEmprestimos.getComponent(idx);
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
                // componente encontrado por procura binaria, teste de colisao
                if (pt.x >= pos.x && pt.x < pos.x + dim.width) {
                    return comp;
                }
                return null;
            }
        } while (len > 0);
        return null;
    }
    
    // força o JPanel dentro do JScrollPane a ter o tamanho necessário
    // para comportar todos os componentes, mesmo que haja redimensionamento
    public void conteudoRedimensionado() {
        Dimension dim = new Dimension(0, 0);
        for (Component comp : jPanelEmprestimos.getComponents()) {
            Dimension tmp = comp.getPreferredSize();
            dim.height += tmp.height;
            dim.width = (tmp.width > dim.width ? tmp.width:dim.width);
        }
        int delta = dim.height - jPanelEmprestimos.getPreferredSize().height;
        if (Math.abs(delta) > 0) {
            jPanelEmprestimos.setPreferredSize(dim);
            jPanelEmprestimos.revalidate();
        }
    }
    
    public JDadosEmprestimo getEmprestimoSelecionado() {
        return emprestimoSelecionado;
    }
    
    public void setObservarSelecao(boolean observarSelecao) {
        if (this.observarSelecao == observarSelecao) return;
        
        if (observarSelecao) {
            jPanelEmprestimos.addMouseListener(observadorSelecao);
        } else {
            jPanelEmprestimos.removeMouseListener(observadorSelecao);
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
    
    // ==================== implements JListaDados ====================
    
    /**
     * carrega os empréstimos do banco na lista se ela estiver vazia e
     * retorna se o conteúdo foi alterado
     * 
     * @return true se o conteúdo da lista for alterado, do contrario false
     */
    @Override
    public boolean carregar() {
        // previne duplicação de dados
        if (jPanelEmprestimos.getComponentCount() > 0) return false;
        
        // carrega os dados
        EmprestimoDAO dao = new EmprestimoDAO();
        inserirEmprestimos(dao.listarEmprestimos());
        return true;
    }
    
    /**
     * carrega mais empréstimos do banco de dados, inserindo-os na lista
     * 
     * @param contagem número de empréstimos para carregar
     */
    @Override
    public void carregar(int contagem) {
        EmprestimoDAO dao = new EmprestimoDAO();
        inserirEmprestimos(dao.listarEmprestimos(this.comprimento(), contagem));
    }
    
    /**
     * remove todos os empréstimos da lista, tornando-a vazia
     */
    @Override
    public void esvaziar() {
        jPanelEmprestimos.setPreferredSize(
            new Dimension(jPanelEmprestimos.getPreferredSize().width, 0)
        );
    }
    
    /**
     * retorna o comprimento da lista em número de empréstimos
     * 
     * @return o comprimento da lista
     */
    @Override
    public int comprimento() {
        return jPanelEmprestimos.getComponentCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneEmprestimos = new javax.swing.JScrollPane();
        jPanelEmprestimos = new javax.swing.JPanel();
        jBarraPesquisaSimples1 = new widget.JBarraPesquisaSimples();

        setPreferredSize(new java.awt.Dimension(602, 600));

        jScrollPaneEmprestimos.setPreferredSize(new java.awt.Dimension(542, 533));

        jPanelEmprestimos.setPreferredSize(new java.awt.Dimension(520, 520));
        jPanelEmprestimos.setLayout(new javax.swing.BoxLayout(jPanelEmprestimos, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneEmprestimos.setViewportView(jPanelEmprestimos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addComponent(jScrollPaneEmprestimos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneEmprestimos, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.JBarraPesquisaSimples jBarraPesquisaSimples1;
    private javax.swing.JPanel jPanelEmprestimos;
    private javax.swing.JScrollPane jScrollPaneEmprestimos;
    // End of variables declaration//GEN-END:variables
}
