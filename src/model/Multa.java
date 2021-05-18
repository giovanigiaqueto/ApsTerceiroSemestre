package model;


public class Multa {
    private int idMulta;
    private int idMultaCliente;
    private int idMultaEmprestimo;
    private String descricaoMulta;
    private double valorMulta;
    private boolean pagamentoMulta;

    public Multa(int idMulta, int idMultaCliente, int idMultaEmprestimo, 
        String descricaoMulta, double valorMulta, boolean pagamentoMulta) {
        this.idMulta = idMulta;
        this.idMultaCliente = idMultaCliente;
        this.idMultaEmprestimo = idMultaEmprestimo;
        this.descricaoMulta = descricaoMulta;
        this.valorMulta = valorMulta;
        this.pagamentoMulta = pagamentoMulta;
    }

    public Multa() {}

    @Override
    public String toString() {
        return "Multa{" + "idMulta = " + idMulta
                + "\n\tidMultaCliente = " + idMultaCliente
                + "\n\tidMultaEmprestimo = " + idMultaEmprestimo
                + "\n\tdescricaoMulta= " + descricaoMulta
                + "\n\tvalorMulta = " + valorMulta
                + "\n\tpagamentoMulta = " + pagamentoMulta + '}';
    }

    public int getIdMulta() { return idMulta; }

    public void setIdMulta(int idMulta) { this.idMulta = idMulta; }

    public int getIdMultaCliente() { return idMultaCliente; }

    public void setIdMultaCliente(int idMultaCliente) { this.idMultaCliente = idMultaCliente; }

    public String getDescricaoMulta() { return descricaoMulta; }

    public void setDescricaoMulta(String descricaoMulta) { this.descricaoMulta = descricaoMulta; }

    public double getValorMulta() { return valorMulta; }

    public void setValorMulta(double valorMulta) { this.valorMulta = valorMulta; }

    public int getIdMultaEmprestimo() { return idMultaEmprestimo; }

    public void setIdMultaEmprestimo(int idMultaEmprestimo) { this.idMultaEmprestimo = idMultaEmprestimo; }
    
    public boolean getPagamentoMulta() { return pagamentoMulta; }
    
    public void setPagamentoMulta(boolean pagamentoMulta) { this.pagamentoMulta = pagamentoMulta; }
}
