package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Cliente;

public class ClienteDAO {
    
    private Connection conecta;

    public ClienteDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<Cliente> listarClientes(){
        String sql = "SELECT * FROM Cliente";
        ResultSet resultado;
        List<Cliente> clientes = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(resultado.getInt("idCliente"));
                cliente.setNomeCliente(resultado.getString("nomeCliente"));
                cliente.setCPFCliente(resultado.getString("CPFCliente"));
                cliente.setTelefoneCliente(resultado.getString("telefoneCliente"));
                cliente.setSexoCliente(resultado.getString("sexoCliente"));
                cliente.setEnderecoCliente(resultado.getString("enderecoCliente"));
                cliente.setEmailCliente(resultado.getString("emailCliente"));
                
                clientes.add(cliente);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return clientes;
    }
    
    public void salvar(Cliente cliente){
        String sql = "INSERT INTO Cliente(nomeCliente, CPFCliente, "
                + "telefoneCliente, sexoCliente, enderecoCliente, emailCliente)"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCPFCliente());
            stmt.setString(3, cliente.getTelefoneCliente());
            stmt.setString(4, cliente.getSexoCliente());
            stmt.setString(5, cliente.getEnderecoCliente());
            stmt.setString(6, cliente.getEmailCliente());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void alterar(Cliente cliente){
        String sql = "UPDATE Cliente SET nomeCliente=?, CPFCliente=?, "
                + "telefoneCliente=?, sexoCliente=?, enderecoCliente=?, emailCliente=?"
                + "WHERE id=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCPFCliente());
            stmt.setString(3, cliente.getTelefoneCliente());
            stmt.setString(4, cliente.getSexoCliente());
            stmt.setString(5, cliente.getEnderecoCliente());
            stmt.setString(6, cliente.getEmailCliente());
            stmt.setInt(7, cliente.getIdCliente());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void deletar(Cliente cliente){
        String sql = "DELETE FROM Cliente"
                + "WHERE idCliente=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, cliente.getIdCliente());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
