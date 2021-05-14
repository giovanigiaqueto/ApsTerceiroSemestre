package model;


public class Cliente {
    
    private int idCliente;
    private String nomeCliente;
    private String CPFCliente;
    private String telefoneCliente;
    private String sexoCliente;
    private String enderecoCliente;
    private String emailCliente;

    public Cliente(int idCliente, String nomeCliente, String CPFCliente, String telefoneCliente, String sexoCliente, String enderecoCliente, String emailCliente) {
            this.idCliente = idCliente;
            this.nomeCliente = nomeCliente;
            this.CPFCliente = CPFCliente;
            this.telefoneCliente = telefoneCliente;
            this.sexoCliente = sexoCliente;
            this.enderecoCliente = enderecoCliente;
            this.emailCliente = emailCliente;
    }
    
    public Cliente() {}
    

    public int getIdCliente() { return idCliente; }

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNomeCliente() { return nomeCliente; }

    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getCPFCliente() { return CPFCliente; }

    public void setCPFCliente(String CPFCliente) { this.CPFCliente = CPFCliente; }

    public String getTelefoneCliente() { return telefoneCliente; }

    public void setTelefoneCliente(String telefoneCliente) { this.telefoneCliente = telefoneCliente; }

    public String getSexoCliente() { return sexoCliente; }

    public void setSexoCliente(String sexoCliente) { this.sexoCliente = sexoCliente; }

    public String getEnderecoCliente() { return enderecoCliente; }

    public void setEnderecoCliente(String enderecoCliente) { this.enderecoCliente = enderecoCliente; }

    public String getEmailCliente() { return emailCliente; }

    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }
}
