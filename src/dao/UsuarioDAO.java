package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO {
    
    private Connection conecta;

    public UsuarioDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Retorna uma lista de Usuario do banco de dados.
     * 
     * @param numItens a quantidade máxima de tuplas retornadas
     * @param deslocamento serão retornadas as tuplas a partir deste valor
     * (começando do deslocamento + 1)
     * 
     * @return a lista de Usuario
     */
    public List<Usuario> listarUsuarios(int numItens, int deslocamento){
        String sql = "SELECT * FROM Usuario LIMIT=? OFFSET=?";
        ResultSet resultado;
        List<Usuario> usuarios = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, numItens);
            stmt.setInt(2, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNomeUsuario(resultado.getString("nomeUsuario"));
                usuario.setCPFUsuario(resultado.getString("CPFUsuario"));
                usuario.setTelefoneUsuario(resultado.getString("telefoneUsuario"));
                usuario.setSexoUsuario(resultado.getString("sexoUsuario"));
                usuario.setEnderecoUsuario(resultado.getString("enderecoUsuario"));
                usuario.setEmailUsuario(resultado.getString("emailUsuario"));
                usuario.setSenhaUsuario(resultado.getString("senhaUsuario"));
                
                usuarios.add(usuario);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return usuarios;
    }
    
    /**
     * Retorna uma lista de Usuario do banco de dados.
     * 
     * @return a lista de Usuario
     */
    public List<Usuario> listarUsuarios(){
        String sql = "SELECT * FROM Usuario";
        ResultSet resultado;
        List<Usuario> usuarios = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNomeUsuario(resultado.getString("nomeUsuario"));
                usuario.setCPFUsuario(resultado.getString("CPFUsuario"));
                usuario.setTelefoneUsuario(resultado.getString("telefoneUsuario"));
                usuario.setSexoUsuario(resultado.getString("sexoUsuario"));
                usuario.setEnderecoUsuario(resultado.getString("enderecoUsuario"));
                usuario.setEmailUsuario(resultado.getString("emailUsuario"));
                usuario.setSenhaUsuario(resultado.getString("senhaUsuario"));
                
                usuarios.add(usuario);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return usuarios;
    }
    
    /**
     * Salva o usuario passado pelo parâmetro no banco de dados.
     * 
     * @param usuario o usuario a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Usuario usuario){
        String sql = "INSERT INTO Usuario(nomeUsuario, CPFUsuario, telefoneUsuario, "
                + "sexoUsuario, enderecoUsuario, emailUsuario, senhaUsuario)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getCPFUsuario());
            stmt.setString(3, usuario.getTelefoneUsuario());
            stmt.setString(4, usuario.getSexoUsuario());
            stmt.setString(5, usuario.getEnderecoUsuario());
            stmt.setString(6, usuario.getEmailUsuario());
            stmt.setString(7, usuario.getSenhaUsuario());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Altera o usuario passado pelo parâmetro no banco de dados.
     * 
     * @param usuario o usuario a ser alterado
     * @return true, se conseguir alterar, e false se não conseguir
     */
    public boolean alterar(Usuario usuario){
        String sql = "UPDATE Usuario SET nomeUsuario=?, CPFUsuario=?, telefoneUsuario=?,"
                + "sexoUsuario=?, enderecoUsuario=?, emailUsuario=?, senhaUsuario=?"
                + "WHERE idUsuario=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getCPFUsuario());
            stmt.setString(3, usuario.getTelefoneUsuario());
            stmt.setString(4, usuario.getSexoUsuario());
            stmt.setString(5, usuario.getEnderecoUsuario());
            stmt.setString(6, usuario.getEmailUsuario());
            stmt.setString(7, usuario.getSenhaUsuario());
            stmt.setInt(8, usuario.getIdUsuario());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o usuario passado pelo parâmetro no banco de dados.
     * 
     * @param usuario o usuario a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    public boolean deletar(Usuario usuario){
        String sql = "DELETE FROM Usuario"
                + "WHERE idUsuario=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
}
