package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    /**
     * Retorna uma lista de Cliente do banco de dados.
     * 
     * @param numItens a quantidade máxima de tuplas retornadas
     * @param deslocamento serão retornadas as tuplas a partir deste valor
     * (começando do deslocamento + 1)
     * 
     * @return a lista de Cliente
     */
    public List<Cliente> listarClientes(int numItens, int deslocamento){
        String sql = "SELECT * FROM Cliente LIMIT=? OFFSET=?";
        ResultSet resultado;
        List<Cliente> clientes = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, numItens);
            stmt.setInt(2, deslocamento);
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
    
    /**
     * Retorna uma lista de Cliente do banco de dados.
     * 
     * @return a lista de Cliente
     */
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
    
    /**
     * Salva o cliente passado pelo parâmetro no banco de dados.
     * 
     * @param cliente o cliente a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Cliente cliente){
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
            return false;
        }
        
        return true;
    }
    
    /**
     * Altera o cliente passado pelo parâmetro no banco de dados.
     * 
     * @param cliente o cliente a ser salvo
     * @return true, se conseguir alterar, e false se não conseguir
     */
    public boolean alterar(Cliente cliente){
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
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o cliente passado pelo parâmetro no banco de dados.
     * 
     * @param cliente o cliente a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    public boolean deletar(Cliente cliente){
        String sql = "DELETE FROM Cliente"
                + "WHERE idCliente=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, cliente.getIdCliente());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
}
