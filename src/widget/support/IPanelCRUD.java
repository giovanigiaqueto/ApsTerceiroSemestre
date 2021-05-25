package widget.support;


public interface IPanelCRUD {
    
    /**
     * retorna se o JPanel deve ser mostrado em sua própia janela
     * 
     * @return se deve ser mostrado como popup ou não
     */
    public boolean mostrarComoPopup();
    
    /**
     * retorna o título do JPanel que deve ser mostrado quando ele for carregado
     * 
     * @return título CRUD
     */
    public String getTituloCRUD();
}
