package model;


public class Categoria {
    
    private int idCategoria;
    private String nomeCategoria;
    private String descricaoCategoria;
    
    public Categoria(int idCategoria, String nomeCategoria, String descricaoCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
    }
    
    public Categoria() {}
    
    // ==================== GETTERS ====================
    
    public int getIdCategoria() {
        return idCategoria;
    }
    
    public String getNomeCategoria() {
        return nomeCategoria;
    }
    
    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }
    
    // ==================== SETTERS ====================
    
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
    
    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }
}
