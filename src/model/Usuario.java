package model;


public class Usuario {

    private int idUsuario;
    private String nomeUsuario;
    private String CPFUsuario;
    private String telefoneUsuario;
    private String sexoUsuario;
    private String enderecoUsuario;
    private String emailUsuario;
    private String senhaUsuario;

    public Usuario(int idUsuario, String nomeUsuario, String CPFUsuario, String telefoneUsuario, String sexoUsuario, String enderecoUsuario, String emailUsuario, String senhaUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.CPFUsuario = CPFUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.sexoUsuario = sexoUsuario;
        this.enderecoUsuario = enderecoUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public Usuario() {}


    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNomeUsuario() { return nomeUsuario; }

    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getCPFUsuario() { return CPFUsuario; }

    public void setCPFUsuario(String CPFUsuario) { this.CPFUsuario = CPFUsuario; }

    public String getTelefoneUsuario() { return telefoneUsuario; }

    public void setTelefoneUsuario(String telefoneUsuario) { this.telefoneUsuario = telefoneUsuario; }

    public String getSexoUsuario() { return sexoUsuario; }

    public void setSexoUsuario(String sexoUsuario) { this.sexoUsuario = sexoUsuario; }

    public String getEnderecoUsuario() { return enderecoUsuario; }

    public void setEnderecoUsuario(String enderecoUsuario) { this.enderecoUsuario = enderecoUsuario; }

    public String getEmailUsuario() { return emailUsuario; }

    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    public String getSenhaUsuario() { return senhaUsuario; }

    public void setSenhaUsuario(String senhaUsuario) { this.senhaUsuario = senhaUsuario; }
}
