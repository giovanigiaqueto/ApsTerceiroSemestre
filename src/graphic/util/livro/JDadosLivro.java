/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic.util.livro;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Livro;

/**
 *
 * @author giovani
 */
public class JDadosLivro extends javax.swing.JPanel {

    private Livro livro;
    private boolean dropdownEstendido;
    private ActionListener actionListenerDropdown;
    
    /**
     * Creates new form JLivro
     */
    public JDadosLivro() {
        initComponents();
        init();
    }
    
    public JDadosLivro(Livro livro) {
        this();
        this.livro = livro;
        atualizarConteudo();
    }
    
    // pós inicialização dos componentes desse componente
    private void init() {
        // adiciona o mestre dos componentes
        jHeaderLivro.setMestre(this);
        jLivroRetraido.setMestre(this);
        jLivroExtendido.setMestre(this);
        
        // configura se o dropdown está estendido
        if (dropdownEstendido) {
            estenderDropdown();
        } else {
            retrairDropdown();
        }
        
        actionListenerDropdown = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (getDropdownEstendido()) {
                    retrairDropdown();
                } else {
                    estenderDropdown();
                }
            }
        };
        
        jHeaderLivro.addActionListenerDropdown(actionListenerDropdown);
    }
    
    public void atualizarConteudo() {
        jHeaderLivro.atualizarConteudo();
        jLivroExtendido.atualizarConteudo();
        jLivroRetraido.atualizarConteudo();
    }
    
    public void estenderDropdown() {
        CardLayout layout = (CardLayout) jPanelDropdown.getLayout();
        layout.last(jPanelDropdown);
        jPanelDropdown.setPreferredSize(jLivroExtendido.getPreferredSize());
        jPanelDropdown.setMinimumSize(jLivroExtendido.getMinimumSize());
        jPanelDropdown.setMaximumSize(jLivroExtendido.getPreferredSize());
        dropdownEstendido = true;
    }
    
    public void retrairDropdown() {
        CardLayout layout = (CardLayout) jPanelDropdown.getLayout();
        layout.first(jPanelDropdown);
        jPanelDropdown.setPreferredSize(jLivroRetraido.getPreferredSize());
        jPanelDropdown.setMinimumSize(jLivroRetraido.getMinimumSize());
        jPanelDropdown.setMaximumSize(jLivroRetraido.getPreferredSize());
        dropdownEstendido = false;
    }
    
    // ========== setters ==========
    
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    public void setDropdownEstendido(boolean valor) {
        if (valor) {
            this.estenderDropdown();
        } else {
            this.retrairDropdown();
        }
    }
    
    // ========== getters ==========
    
    public Livro getLivro() { return livro; }

    public boolean getDropdownEstendido() { return dropdownEstendido; }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeaderLivro = new graphic.util.livro.JHeaderLivro();
        jPanelDropdown = new javax.swing.JPanel();
        jLivroRetraido = new graphic.util.livro.JLivroRetraido();
        jLivroExtendido = new graphic.util.livro.JLivroEstendido();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setMinimumSize(new java.awt.Dimension(528, 0));

        jPanelDropdown.setLayout(new java.awt.CardLayout());
        jPanelDropdown.add(jLivroRetraido, "dropdownRetraido");
        jPanelDropdown.add(jLivroExtendido, "dropdownExtendido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jHeaderLivro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDropdown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDropdown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private graphic.util.livro.JHeaderLivro jHeaderLivro;
    private graphic.util.livro.JLivroEstendido jLivroExtendido;
    private graphic.util.livro.JLivroRetraido jLivroRetraido;
    private javax.swing.JPanel jPanelDropdown;
    // End of variables declaration//GEN-END:variables
}
