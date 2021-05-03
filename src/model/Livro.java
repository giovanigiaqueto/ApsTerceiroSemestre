package model;

public class Livro {
    
    private int idLivro;
    private String nomeLivro;
    private String ISBNLivro;
    private String autorLivro;
    private int edicaoLivro;
    private String dataLancamentoLivro;
    private int idLivroCategoria;
    private int estoqueLivro;

    public Livro(int idLivro, String nomeLivro, String ISBNLivro, String autorLivro, int edicaoLivro, String dataLancamentoLivro, int idLivroCategoria, int estoqueLivro) {
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.ISBNLivro = ISBNLivro;
        this.autorLivro = autorLivro;
        this.edicaoLivro = edicaoLivro;
        this.dataLancamentoLivro = dataLancamentoLivro;
        this.idLivroCategoria = idLivroCategoria;
        this.estoqueLivro = estoqueLivro;
    }

    public Livro() {}


    public String getISBNLivro() { return ISBNLivro; }

    public void setISBNLivro(String ISBNLivro) { this.ISBNLivro = ISBNLivro; }

    public int getIdLivro() { return idLivro; }

    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public String getNomeLivro() { return nomeLivro; }

    public void setNomeLivro(String nomeLivro) { this.nomeLivro = nomeLivro; }

    public String getAutorLivro() { return autorLivro; }

    public void setAutorLivro(String autorLivro) { this.autorLivro = autorLivro; }

    public int getEdicaoLivro() { return edicaoLivro; }

    public void setEdicaoLivro(int edicaoLivro) { this.edicaoLivro = edicaoLivro; }

    public String getDataLancamentoLivro() { return dataLancamentoLivro; }

    public void setDataLancamentoLivro(String dataLancamentoLivro) { this.dataLancamentoLivro = dataLancamentoLivro; }

    public int getIdLivroCategoria() { return idLivroCategoria; }

    public void setIdLivroCategoria(int idLivroCategoria) { this.idLivroCategoria = idLivroCategoria; }

    public int getEstoqueLivro() { return estoqueLivro; }

    public void setEstoqueLivro(int estoqueLivro) { this.estoqueLivro = estoqueLivro; }
}
