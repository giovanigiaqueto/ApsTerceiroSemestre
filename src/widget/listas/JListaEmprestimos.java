package widget.listas;

// swing
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

// awt
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

// dao
import dao.EmprestimoDAO;

// modelos
import model.Emprestimo;

// widget
import widget.dados.JDadosEmprestimo;


public class JListaEmprestimos extends javax.swing.JPanel implements IListaDados {
    
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
        Dimension dim = jPanelLivros.getPreferredSize();
        dim.height = 0;
        jPanelLivros.setPreferredSize(dim);
        
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
        Dimension dim = jPanelLivros.getPreferredSize();
        
        for (Emprestimo p : emprestimos) {
            JDadosEmprestimo emprestimo = new JDadosEmprestimo(p);
            jPanelLivros.add(emprestimo);
            dim.height += emprestimo.getPreferredSize().height;    
        }
        jPanelLivros.setPreferredSize(dim);
        jPanelLivros.revalidate();
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
        int len = jPanelLivros.getComponentCount();
        int idx = 0;
        do {
            Component comp = jPanelLivros.getComponent(idx);
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
        for (Component comp : jPanelLivros.getComponents()) {
            Dimension tmp = comp.getPreferredSize();
            dim.height += tmp.height;
            dim.width = (tmp.width > dim.width ? tmp.width:dim.width);
        }
        int delta = dim.height - jPanelLivros.getPreferredSize().height;
        if (Math.abs(delta) > 0) {
            jPanelLivros.setPreferredSize(dim);
            jPanelLivros.revalidate();
        }
    }
    
    public JDadosEmprestimo getEmprestimoSelecionado() {
        return emprestimoSelecionado;
    }
    
    public void setObservarSelecao(boolean observarSelecao) {
        if (this.observarSelecao == observarSelecao) return;
        
        if (observarSelecao) {
            jPanelLivros.addMouseListener(observadorSelecao);
        } else {
            jPanelLivros.removeMouseListener(observadorSelecao);
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
        if (jScrollPaneLivros.getComponentCount() > 0) return false;
        
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
        jPanelLivros.setPreferredSize(
            new Dimension(jPanelLivros.getPreferredSize().width, 0)
        );
    }
    
    /**
     * retorna o comprimento da lista em número de empréstimos
     * 
     * @return o comprimento da lista
     */
    @Override
    public int comprimento() {
        return jPanelLivros.getComponentCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneLivros = new javax.swing.JScrollPane();
        jPanelLivros = new javax.swing.JPanel();
        jBarraPesquisaSimples1 = new widget.JBarraPesquisaSimples();

        setPreferredSize(new java.awt.Dimension(602, 600));

        jScrollPaneLivros.setPreferredSize(new java.awt.Dimension(542, 533));

        jPanelLivros.setPreferredSize(new java.awt.Dimension(520, 520));
        jPanelLivros.setLayout(new javax.swing.BoxLayout(jPanelLivros, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneLivros.setViewportView(jPanelLivros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addComponent(jScrollPaneLivros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneLivros, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.JBarraPesquisaSimples jBarraPesquisaSimples1;
    private javax.swing.JPanel jPanelLivros;
    private javax.swing.JScrollPane jScrollPaneLivros;
    // End of variables declaration//GEN-END:variables
}
