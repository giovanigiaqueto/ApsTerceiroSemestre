package widget.listas;

// swing
import javax.swing.JPanel;

// awt
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

// java util
import java.util.List;
import java.util.Iterator;

// dao
import dao.CategoriaDAO;

// modelos
import model.Categoria;

// widget
import widget.dados.JDadosCategoria;

public class JListaCategorias extends javax.swing.JPanel implements IListaDados {
    
    private JPanel _jPanelParCategoriaRef;
    
    /**
     * Creates new form JListaLivros
     */
    public JListaCategorias() {
        initComponents();
        init();
    }
    
    private void init() {
        Dimension dim = jPanelLivros.getPreferredSize();
        dim.height = 10;
        jPanelLivros.setPreferredSize(dim);
        _jPanelParCategoriaRef = null;
    }
    
    public void inserirCategorias(List<Categoria> categorias) {
        Dimension dim = jPanelLivros.getPreferredSize();
        
        int w_gap = 20;
        int h_gap = 5;
        
        // itera de dois em dois se possível e dispõe as categorias lado a lado
        // (categorias sem par são mostradas na esquerda com um espaço em branco na direita)
        Iterator<Categoria> iter = categorias.iterator();
        JPanel panel = null;
        if (iter.hasNext()) {
            if (this._jPanelParCategoriaRef != null) {
                this._jPanelParCategoriaRef.add(new JDadosCategoria(iter.next()));
                this._jPanelParCategoriaRef = null;
            }
        } else {
            // nada adicionado, retorna para evitar revalidação desnecessaria do swing
            return;
        }
        while (iter.hasNext()) {
            panel = new JPanel();
            panel.setLayout(new FlowLayout());
            
            // cria um JPanel com duas categorias se possível, ou uma se tiver no fim do loop
            JDadosCategoria cat0 = new JDadosCategoria(iter.next());
            panel.add(cat0, 0);
            Dimension minDim  = cat0.getMinimumSize();
            Dimension prefDim = cat0.getPreferredSize();
            if (iter.hasNext()) {
                JDadosCategoria cat1 = new JDadosCategoria(iter.next());
                panel.add(cat1, 1);
                
                Dimension tmpMinDim = cat1.getMinimumSize();
                Dimension tmpPrefDim = cat1.getPreferredSize();
                
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
            jPanelLivros.add(panel);
        }
        // atualiza a referencia ao ultimo panel
        this._jPanelParCategoriaRef = panel;
        
        jPanelLivros.setPreferredSize(dim);
        jPanelLivros.revalidate();
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
    
    // ==================== implements IListaDados ====================
    
    /**
     * carrega as categorias do banco na lista se ela estiver vazia e
     * retorna se o conteúdo foi alterado
     * 
     * @return true se o conteúdo da lista for alterado, do contrario false
     */
    @Override
    public boolean carregar() {
        // previne duplicação de dados
        if (jScrollPaneLivros.getComponentCount() > 0) return false;
        
        // carrega os dados
        CategoriaDAO dao = new CategoriaDAO();
        inserirCategorias(dao.listarCategorias());
        return true;
    }
    
    /**
     * carrega mais categorias do banco de dados, inserindo-os na lista
     * 
     * @param contagem número de categorias para carregar
     */
    @Override
    public void carregar(int contagem) {
        CategoriaDAO dao = new CategoriaDAO();
        inserirCategorias(dao.listarCategorias(this.comprimento(), contagem));
    }
    
    /**
     * remove todos as categorias da lista, tornando-a vazia
     */
    @Override
    public void esvaziar() {
        jPanelLivros.removeAll();
        jPanelLivros.setPreferredSize(
            new Dimension(jPanelLivros.getPreferredSize().width, 10)
        );
        _jPanelParCategoriaRef = null;
    }
    
    /**
     * retorna o comprimento da lista em número de categorias
     * 
     * @return o comprimento da lista
     */
    @Override
    public int comprimento() {
        int cnt = jPanelLivros.getComponentCount() * 2;
        return (_jPanelParCategoriaRef != null ? cnt-1:cnt);
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

        setPreferredSize(new java.awt.Dimension(600, 600));

        jScrollPaneLivros.setPreferredSize(new java.awt.Dimension(576, 533));

        jPanelLivros.setPreferredSize(new java.awt.Dimension(573, 530));
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
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
