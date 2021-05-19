package model;

public class Emprestimo {
    
    private int idEmprestimo;
    private int idEmprestimoCliente;
    private int idEmprestimoUsuario;
    private int idEmprestimoExemplar;
    private String dataEmprestimo;
    private String dataDevolucao;
    
    /**
     * 
     * @param idEmprestimo id do emprestimo
     * @param idEmprestimoCliente id do cliente que pego o exemplar emprestado
     * @param idEmprestimoUsuario id do usuário que realizou o emprestimo
     * @param idEmprestimoExemplar id do exemplar que foi emprestado
     * @param dataEmprestimo data em que o emprestimo foi realizado
     * @param dataDevolucao data em que o exemplar deve ser devolvido sem que haja multa
     */
    public Emprestimo(int idEmprestimo, int idEmprestimoCliente, int idEmprestimoUsuario, 
        int idEmprestimoExemplar, String dataEmprestimo, String dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idEmprestimoCliente = idEmprestimoCliente;
        this.idEmprestimoUsuario = idEmprestimoUsuario;
        this.idEmprestimoExemplar = idEmprestimoExemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
    
    public Emprestimo() {}

    @Override
    public String toString() {
        return "Emprestimo{" + "idEmprestimo = " + idEmprestimo
                + "\n\tidEmprestimoCliente = " + idEmprestimoCliente
                + "\n\tidEmprestimoUsuario = " + idEmprestimoUsuario
                + "\n\tidEmprestimoExemplar = " + idEmprestimoExemplar
                + "\n\tdataEmprestimo = " + dataEmprestimo
                + "\n\tdataDevolucao = " + dataDevolucao + '}';
    }

    public int getIdEmprestimo() { return idEmprestimo; }

    public void setIdEmprestimo(int idEmprestimo) { this.idEmprestimo = idEmprestimo; }

    public int getIdEmprestimoCliente() { return idEmprestimoCliente; }

    public void setIdEmprestimoCliente(int idEmprestimoCliente) { this.idEmprestimoCliente = idEmprestimoCliente; }

    public int getIdEmprestimoExemplar() { return idEmprestimoExemplar; }

    public void setIdEmprestimoExemplar(int idEmprestimoExemplar) { this.idEmprestimoExemplar = idEmprestimoExemplar; }

    public String getDataEmprestimo() { return dataEmprestimo; }

    public void setDataEmprestimo(String dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public String getDataDevolucao() { return dataDevolucao; }

    public void setDataDevolucao(String dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public int getIdEmprestimoUsuario() { return idEmprestimoUsuario; }

    public void setIdEmprestimoUsuario(int idEmprestimoUsuario) { this.idEmprestimoUsuario = idEmprestimoUsuario; }
}
