/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package widget.listas;

// swing
import java.awt.Color;
import javax.swing.JPanel;

// awt
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// java util
import java.util.List;
import java.util.LinkedList;

// modelos
import model.Livro;

// widget
import widget.dados.JDadosLivro;
import widget.support.IComponenteLivro;

// java lang.reflect
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author giovani
 */
public class JListaLivros extends javax.swing.JPanel {
    
    private ComponentAdapter resizeListener;
    
    // se está observando seleções
    private boolean observarSelecao;
    
    // observador de seleção (MouseClick event handler)
    private MouseAdapter observadorSelecao;
    
    private JPanel livroSelecionado; // livro selecionado 
    private Border bordaSalva;       // borda normal do livro selecionado
    private Border bordaSelecao;     // borda do livro selecionado
    
    public interface ObservadorSelecao {
        public void selecao(IComponenteLivro livro);
    };
    
    private List<ObservadorSelecao> observadoresSelecao;

    /**
     * Creates new form JListaLivros
     */
    public JListaLivros() {
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
        
        // observador de redimensinamento
        resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                conteudoRedimensionado();
            }
        };
        
        // observador de seleção
        observadorSelecao = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                // DEBUG System.out.println("observador executado");
                JPanel panel = (JPanel) bsearchComponente(ev.getPoint());
                // verifica se um livro foi selecionado
                if (panel != null) {
                    // reseta a borda do livro que estava selecionado
                    if (livroSelecionado != null) {
                        livroSelecionado.setBorder(bordaSalva);
                    }
                    // salva a borda do livro que foi selecionado
                    // e troca sua borda autal pela borda de seleção
                    bordaSalva = panel.getBorder();
                    panel.setBorder(bordaSelecao);
                    
                    // salva o exemplar selecionado
                    livroSelecionado = panel;
                    
                    // executa todos os observadores de selecao
                    for (ObservadorSelecao obs : observadoresSelecao) {
                        obs.selecao((IComponenteLivro) panel);
                    }
                }
            }
        };
    }
    
    // inspirado por: https://stackoverflow.com/questions/75175/create-instance-of-generic-type-in-java
    // permite a adição de qualquer JPanel que implemente a interface IComponenteLivro
    public <T extends JPanel & IComponenteLivro>
    void inserirLivros(List<Livro> livros, Class<T> cls) {
        Dimension dim = jPanelLivros.getPreferredSize();
        Constructor<T> constructor;
        try {
            constructor = cls.getDeclaredConstructor(Livro.class);
        } catch(NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        for (Livro p : livros) {
            try {
                T livro = constructor.newInstance(p);
                jPanelLivros.add(livro);
                dim.height += livro.getPreferredSize().height;
                livro.addComponentListener(resizeListener);
            } catch(
                InstantiationException | 
                IllegalAccessException | 
                InvocationTargetException e) { throw new RuntimeException(e); }
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
    
    public IComponenteLivro getLivroSelecionado() {
        return (IComponenteLivro) livroSelecionado;
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
                .addComponent(jScrollPaneLivros, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
