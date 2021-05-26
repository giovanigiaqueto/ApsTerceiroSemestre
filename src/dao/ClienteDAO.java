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
        String sql = "SELECT * FROM Cliente "
                + "WHERE ativo=? "
                + "LIMIT ? OFFSET ?";
        ResultSet resultado;
        List<Cliente> clientes = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, numItens);
            stmt.setInt(3, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(resultado.getInt("id_cliente"));
                cliente.setNomeCliente(resultado.getString("nome_cliente"));
                cliente.setCPFCliente(resultado.getString("cpf_cliente"));
                cliente.setTelefoneCliente(resultado.getString("telefone_cliente"));
                cliente.setSexoCliente(resultado.getString("sexo_cliente"));
                cliente.setEnderecoCliente(resultado.getString("endereco_cliente"));
                cliente.setEmailCliente(resultado.getString("email_cliente"));
                
                clientes.add(cliente);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        String sql = "SELECT * FROM Cliente "
                + "WHERE ativo=?";
        ResultSet resultado;
        List<Cliente> clientes = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(resultado.getInt("id_cliente"));
                cliente.setNomeCliente(resultado.getString("nome_cliente"));
                cliente.setCPFCliente(resultado.getString("cpf_cliente"));
                cliente.setTelefoneCliente(resultado.getString("telefone_cliente"));
                cliente.setSexoCliente(resultado.getString("sexo_cliente"));
                cliente.setEnderecoCliente(resultado.getString("endereco_cliente"));
                cliente.setEmailCliente(resultado.getString("email_cliente"));
                
                clientes.add(cliente);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return clientes;
    }
    
    /**
     * Retorna um Cliente dado seu id, gera RuntimeError se esse id não existir
     * 
     * @param id o id do cliente a ser procurado
     * 
     * @return o cliente com o id
     */
    public Cliente procurarCliente(int id){
        String sql = "SELECT * FROM Cliente "
                + "WHERE ativo=? AND id_cliente=?";
        
        Cliente cliente = null;
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                cliente = new Cliente();
            
                cliente.setIdCliente(id);
                cliente.setNomeCliente(resultado.getString("nome_cliente"));
                cliente.setCPFCliente(resultado.getString("cpf_cliente"));
                cliente.setTelefoneCliente(resultado.getString("telefone_cliente"));
                cliente.setSexoCliente(resultado.getString("sexo_cliente"));
                cliente.setEnderecoCliente(resultado.getString("endereco_cliente"));
                cliente.setEmailCliente(resultado.getString("email_cliente"));
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return cliente;
    }
    
    /**
     * Salva o cliente passado pelo parâmetro no banco de dados.
     * 
     * @param cliente o cliente a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Cliente cliente){
        String sql = "INSERT INTO Cliente(nome_cliente, cpf_cliente, "
                + "telefone_cliente, sexo_cliente, endereco_cliente, email_cliente)"
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
            System.out.println(ex.getMessage());
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
        String sql = "UPDATE Cliente SET nome_cliente=?, cpf_cliente=?, "
                + "telefone_cliente=?, sexo_cliente=?, endereco_cliente=?, email_cliente=?"
                + "WHERE id_cliente=?";
        
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
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o cliente passado pelo parâmetro no banco de dados.
     * 
     * @deprecated use {@link #desativar(model.Cliente) }
     * @param cliente o cliente a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    @Deprecated
    public boolean deletar(Cliente cliente){
        String sql = "DELETE FROM Cliente "
                + "WHERE id_cliente=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, cliente.getIdCliente());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Coloca o campo ativo como falso
     * 
     * @param cliente o cliente a ser desativado
     * @return true, se conseguir desativar, e false se não conseguir
     */
    public boolean desativar(Cliente cliente){
        String sql = "UPDATE Cliente SET ativo=? "
                + "WHERE id_cliente=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setInt(2, cliente.getIdCliente());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
