package widget.listas;


public interface IListaDados {
    
    /**
     * carrega os dados do banco na lista se a lista estiver vazia e
     * retorna se o conteúdo da lista foi alterado
     * 
     * @return true se o conteúdo da lista for alterado, do contrario false
     */
    public boolean carregar();
    
    /**
     * carrega mais itens do banco de dados, inserindo-os na lista
     * 
     * @param contagem número de items para carregar
     */
    public void carregar(int contagem);
    
    /**
     * esvazia a lista, tornando-a vazia
     */
    public void esvaziar();
    
    /**
     * retorna o comprimento da lista em número de items
     * 
     * @return o comprimento da lista
     */
    public int comprimento();
    
}
