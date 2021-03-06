package model;


public class Exemplar {
    
    private int idExemplar;
    private int idExemplarLivro;
    private boolean estaAlocado;
    private String dataObtencao;
    
    /**
     * 
     * @param idExemplar id do exemplar
     * @param idExemplarLivro id do livro que o exemplar pertence
     * @param estaAlocado estado de alocação do livro
     * @param dataObtencao data de obtenção / compra do exemplar
     */
    public Exemplar(int idExemplar, int idExemplarLivro,
        boolean estaAlocado, String dataObtencao) {
        this.idExemplar = idExemplar;
        this.idExemplarLivro = idExemplarLivro;
        this.estaAlocado = estaAlocado;
        this.dataObtencao = dataObtencao;
    }
    
    public Exemplar() {}

    @Override
    public String toString() {
        return "Exemplar{" + "idExemplar = " + idExemplar
                + "\n\tidExemplarLivro = " + idExemplarLivro
                + "\n\testaAlocado = " + estaAlocado
                + "\n\tdataObtencao = " + dataObtencao + '}';
    }
    
    // ==================== GETTERS ====================
    
    public int getIdExemplar() { 
        return idExemplar; 
    }
    
    public int getIdExemplarLivro() { 
        return idExemplarLivro; 
    }
    
    public boolean getEstaAlocado() { 
        return estaAlocado; 
    }
    
    public String getDataObtencao() { 
        return dataObtencao; 
    }
    
    // ==================== SETTERS ====================
    
    public void setIdExemplar(int idExemplar) { 
        this.idExemplar = idExemplar;
    }
    
    public void setIdExemplarLivro(int idExemplarLivro) {
        this.idExemplarLivro = idExemplarLivro;
    }
    
    public void setEstaAlocado(boolean estaAlocado) { 
        this.estaAlocado = estaAlocado;
    }
    
    public void setDataObtencao(String dataObtencao) { 
        this.dataObtencao = dataObtencao;
    }
}
