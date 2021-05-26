package widget.listas;

// swing
import javax.swing.JPanel;
// import javax.swing.BorderFactory;

// awt
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

// java util
import java.util.List;
import java.util.Iterator;

// dao
import dao.CategoriaDAO;
import java.awt.Color;

// modelos
import model.Categoria;

// widget
import widget.dados.JDadosCategoria;

// suporte
import widget.support.IPanelCRUD;

public class JListaCategorias extends javax.swing.JPanel implements IListaDados, IPanelCRUD {
    
    @Override
    public boolean mostrarComoPopup() { return false; }
    
    @Override
    public String getTituloCRUD() { return "Lista de Categorias"; }
    
    private JPanel _jPanelParCategoriaRef;
    
    /**
     * Creates new form JListaLivros
     */
    public JListaCategorias() {
        initComponents();
        init();
    }
    
    private void init() {
        Dimension dim = jPanelCategorias.getPreferredSize();
        dim.height = 10;
        jPanelCategorias.setPreferredSize(dim);
        _jPanelParCategoriaRef = null;
    }
    
    public void inserirCategorias(List<Categoria> categorias) {
        Dimension dim = jPanelCategorias.getPreferredSize();
        
        int w_gap = 20;
        int h_gap = 10;
        
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
            // /*DEBUG*/ panel.setBorder(BorderFactory.createLineBorder(Color.orange));
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
            
            prefDim.height += h_gap;
            
            // atualiza as dimensões
            dim.height += prefDim.height;
            dim.width = (prefDim.width > dim.width ? prefDim.width : dim.width);
            dim.width = (minDim.width > dim.width ? minDim.width : dim.width);
            
            // configura o tamanho do panel
            panel.setMinimumSize(minDim);
            panel.setPreferredSize(prefDim);
            panel.setMaximumSize(prefDim);
            
            // adiciona o panel
            jPanelCategorias.add(panel);
        }
        // atualiza a referencia ao ultimo panel
        this._jPanelParCategoriaRef = panel;
        
        jPanelCategorias.setPreferredSize(dim);
        jPanelCategorias.revalidate();
    }
    
    // força o JPanel dentro do JScrollPane a ter o tamanho necessário
    // para comportar todos os componentes, mesmo que haja redimensionamento
    public void conteudoRedimensionado() {
        Dimension dim = new Dimension(0, 0);
        for (Component comp : jPanelCategorias.getComponents()) {
            Dimension tmp = comp.getPreferredSize();
            dim.height += tmp.height;
            dim.width = (tmp.width > dim.width ? tmp.width:dim.width);
        }
        int delta = dim.height - jPanelCategorias.getPreferredSize().height;
        if (Math.abs(delta) > 0) {
            jPanelCategorias.setPreferredSize(dim);
            jPanelCategorias.revalidate();
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
        if (jPanelCategorias.getComponentCount() > 0) return false;
        
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
        jPanelCategorias.removeAll();
        jPanelCategorias.setPreferredSize(
            new Dimension(jPanelCategorias.getPreferredSize().width, 10)
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
        int cnt = jPanelCategorias.getComponentCount() * 2;
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

        jScrollPaneCategorias = new javax.swing.JScrollPane();
        jPanelCategorias = new javax.swing.JPanel();
        jBarraPesquisaSimples1 = new widget.JBarraPesquisaSimples();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jScrollPaneCategorias.setMaximumSize(new java.awt.Dimension(576, 32767));
        jScrollPaneCategorias.setMinimumSize(new java.awt.Dimension(576, 22));
        jScrollPaneCategorias.setPreferredSize(new java.awt.Dimension(576, 533));

        jPanelCategorias.setMinimumSize(new java.awt.Dimension(650, 0));
        jPanelCategorias.setPreferredSize(new java.awt.Dimension(650, 530));
        jPanelCategorias.setLayout(new javax.swing.BoxLayout(jPanelCategorias, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneCategorias.setViewportView(jPanelCategorias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPaneCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBarraPesquisaSimples1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.JBarraPesquisaSimples jBarraPesquisaSimples1;
    private javax.swing.JPanel jPanelCategorias;
    private javax.swing.JScrollPane jScrollPaneCategorias;
    // End of variables declaration//GEN-END:variables
}
