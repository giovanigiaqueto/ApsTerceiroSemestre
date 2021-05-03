package model;

public class Emprestimo {
    
    private int idEmprestimo;
    private int idEmprestimoCliente;
    private int idEmprestimoUsuario;
    private int idEmprestimoLivro;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo() {}

    public Emprestimo(int idEmprestimo, int idEmprestimoCliente, int idEmprestimoUsuario, int idEmprestimoLivro, String dataEmprestimo, String dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idEmprestimoCliente = idEmprestimoCliente;
        this.idEmprestimoUsuario = idEmprestimoUsuario;
        this.idEmprestimoLivro = idEmprestimoLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) { this.idEmprestimo = idEmprestimo; }

    public int getIdEmprestimoCliente() { return idEmprestimoCliente; }

    public void setIdEmprestimoCliente(int idEmprestimoCliente) { this.idEmprestimoCliente = idEmprestimoCliente; }

    public int getIdEmprestimoLivro() { return idEmprestimoLivro; }

    public void setIdEmprestimoLivro(int idEmprestimoLivro) { this.idEmprestimoLivro = idEmprestimoLivro; }

    public String getDataEmprestimo() { return dataEmprestimo; }

    public void setDataEmprestimo(String dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public String getDataDevolucao() { return dataDevolucao; }

    public void setDataDevolucao(String dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public int getIdEmprestimoUsuario() { return idEmprestimoUsuario; }

    public void setIdEmprestimoUsuario(int idEmprestimoUsuario) { this.idEmprestimoUsuario = idEmprestimoUsuario; }
}
