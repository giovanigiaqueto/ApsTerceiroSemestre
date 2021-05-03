package model;


public class Multa {
    private int idMulta;
    private int idMultaCliente;
    private int idEmprestimoMulta;
    private String descricaoMulta;
    private double valorMulta;

    public Multa(int idMulta, int idMultaCliente, int idEmprestimoMulta, String descricaoMulta, double valorMulta) {
        this.idMulta = idMulta;
        this.idMultaCliente = idMultaCliente;
        this.idMultaCliente = idEmprestimoMulta;
        this.descricaoMulta = descricaoMulta;
        this.valorMulta = valorMulta;
    }

    public Multa() {}

    public int getIdMulta() { return idMulta; }

    public void setIdMulta(int idMulta) { this.idMulta = idMulta; }

    public int getIdMultaCliente() { return idMultaCliente; }

    public void setIdMultaCliente(int idMultaCliente) { this.idMultaCliente = idMultaCliente; }

    public String getDescricaoMulta() { return descricaoMulta; }

    public void setDescricaoMulta(String descricaoMulta) { this.descricaoMulta = descricaoMulta; }

    public double getValorMulta() { return valorMulta; }

    public void setValorMulta(double valorMulta) { this.valorMulta = valorMulta; }

    public int getIdEmprestimoMulta() { return idEmprestimoMulta; }

    public void setIdEmprestimoMulta(int idEmprestimoMulta) { this.idEmprestimoMulta = idEmprestimoMulta; }
}
