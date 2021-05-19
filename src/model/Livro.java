package model;

public class Livro {
    
    private int idLivro;
    private String nomeLivro;
    private String ISBNLivro;
    private String autorLivro;
    private String editoraLivro;
    private int edicaoLivro;
    private String dataLancamentoLivro;
    private String nomeLivroCategoria;
    private int estoqueLivro;
    private int locacaoLivro;
    private int paginasLivro;
    private double precoLivro;
    private String sinopseLivro;

    /**
     * 
     * @param idLivro id do livro
     * @param nomeLivro nome completo do livro
     * @param ISBNLivro código ISBN do livro, incluindo traços
     * @param autorLivro nome completo do autor do livro, ou NULL
     * @param editoraLivro nome da editora que publicou o livro
     * @param edicaoLivro número da edição do livro (começando do 1)
     * @param dataLancamentoLivro data de lançamento da edição do livro
     * @param nomeLivroCategoria nome da categoria
     * @param estoqueLivro quantidade total de livros (estoque + locação)
     * @param locacaoLivro quantidade de livros alocados
     * @param paginasLivro quantidade de páginas do livro
     * @param precoLivro preço do livro em reais
     * @param sinopseLivro sinopse do livro (comprimento máximo: 8*1024 = 8192)
     */
    public Livro(int idLivro, String nomeLivro, String ISBNLivro, String autorLivro,
        String editoraLivro, int edicaoLivro, String dataLancamentoLivro,
        String nomeLivroCategoria, int estoqueLivro, int locacaoLivro, int paginasLivro,
        double precoLivro, String sinopseLivro) {
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.ISBNLivro = ISBNLivro;
        this.autorLivro = autorLivro;
        this.editoraLivro = editoraLivro;
        this.edicaoLivro = edicaoLivro;
        this.dataLancamentoLivro = dataLancamentoLivro;
        this.nomeLivroCategoria = nomeLivroCategoria;
        this.locacaoLivro = locacaoLivro;
        this.estoqueLivro = estoqueLivro;
        this.paginasLivro = paginasLivro;
        this.precoLivro = precoLivro;
        this.sinopseLivro = sinopseLivro;
    }

    public Livro() {}

    @Override
    public String toString() {
        return "Livro{" + "idLivro = " + idLivro
                + "\n\tnomeLivro = " + nomeLivro
                + "\n\tISBNLivro = " + ISBNLivro
                + "\n\tautorLivro = " + autorLivro
                + "\n\teditoraLivro = " + editoraLivro
                + "\n\tedicaoLivro = " + edicaoLivro
                + "\n\tdataLancamentoLivro = " + dataLancamentoLivro
                + "\n\tnomeLivroCategoria = " + nomeLivroCategoria
                + "\n\testoqueLivro = " + estoqueLivro
                + "\n\tlocacaoLivro = " + locacaoLivro
                + "\n\tpaginasLivro = " + paginasLivro
                + "\n\tprecoLivro = " + precoLivro
                + "\n\tsinopseLivro = " + sinopseLivro + '}';
    }
    
    // ==================== GETTERS ====================
    
    public String getISBNLivro() { return ISBNLivro; }
    
    public int getIdLivro() { return idLivro; }
    
    public String getNomeLivro() { return nomeLivro; }
    
    public String getAutorLivro() { return autorLivro; }
    
    public String getEditoraLivro() { return editoraLivro; }
    
    public int getEdicaoLivro() { return edicaoLivro; }
    
    public String getDataLancamentoLivro() { return dataLancamentoLivro; }
    
    public String getNomeLivroCategoria() { return nomeLivroCategoria; }
    
    public int getEstoqueLivro() { return estoqueLivro; }
    
    public int getLocacaoLivro() { return locacaoLivro; }
    
    public int getPaginasLivro() { return paginasLivro; }
    
    public double getPrecoLivro() { return precoLivro; }
    
    public String getSinopseLivro() { return sinopseLivro; }
    
    // ==================== SETTERS ====================

    public void setISBNLivro(String ISBNLivro) { this.ISBNLivro = ISBNLivro; }

    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public void setNomeLivro(String nomeLivro) { this.nomeLivro = nomeLivro; }

    public void setAutorLivro(String autorLivro) { this.autorLivro = autorLivro; }

    public void setEditoraLivro(String editoraLivro) { this.editoraLivro = editoraLivro; }
    
    public void setEdicaoLivro(int edicaoLivro) { this.edicaoLivro = edicaoLivro; }

    public void setDataLancamentoLivro(String dataLancamentoLivro) { this.dataLancamentoLivro = dataLancamentoLivro; }

    public void setNomeLivroCategoria(String nomeLivroCategoria) { this.nomeLivroCategoria = nomeLivroCategoria; }
    
    public void setEstoqueLivro(int estoqueLivro) { this.estoqueLivro = estoqueLivro; }

    public void setLocacaoLivro(int locacaoLivro) { this.locacaoLivro = locacaoLivro; }
    
    public void setPaginasLivro(int paginasLivro) { this.paginasLivro = paginasLivro; }
    
    public void setPrecoLivro(double precoLivro) { this.precoLivro = precoLivro; }
    
    public void setSinopseLivro(String sinopseLivro) { this.sinopseLivro = sinopseLivro; }
}
