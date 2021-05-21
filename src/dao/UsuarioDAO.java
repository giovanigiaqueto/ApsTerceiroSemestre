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
        String sql = "SELECT * FROM Usuario "
                + "WHERE ativo=? "
                + "LIMIT ? OFFSET ?";
        ResultSet resultado;
        List<Usuario> usuarios = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, numItens);
            stmt.setInt(3, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNomeUsuario(resultado.getString("nome_usuario"));
                usuario.setCPFUsuario(resultado.getString("cpf_usuario"));
                usuario.setTelefoneUsuario(resultado.getString("telefone_usuario"));
                usuario.setSexoUsuario(resultado.getString("sexo_usuario"));
                usuario.setEnderecoUsuario(resultado.getString("endereco_usuario"));
                usuario.setEmailUsuario(resultado.getString("email_usuario"));
                usuario.setSenhaUsuario(resultado.getString("senha_usuario"));
                
                usuarios.add(usuario);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        String sql = "SELECT * FROM Usuario "
                + "WHERE ativo=?";
        ResultSet resultado;
        List<Usuario> usuarios = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNomeUsuario(resultado.getString("nome_usuario"));
                usuario.setCPFUsuario(resultado.getString("cpf_usuario"));
                usuario.setTelefoneUsuario(resultado.getString("telefone_usuario"));
                usuario.setSexoUsuario(resultado.getString("sexo_usuario"));
                usuario.setEnderecoUsuario(resultado.getString("endereco_usuario"));
                usuario.setEmailUsuario(resultado.getString("email_usuario"));
                usuario.setSenhaUsuario(resultado.getString("senha_usuario"));
                
                usuarios.add(usuario);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return usuarios;
    }
    
    /**
     * Retorna um Usuario dado seu id, gera RuntimeError se esse id não existir
     * 
     * @param id o id do usuário a ser procurado
     * 
     * @return o usuário com o id
     */
    public Usuario procurarUsuario(int id){
        String sql = "SELECT * FROM Usuario "
                + "WHERE ativo=? AND id_usuario=?";
        
        Usuario usuario;
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            ResultSet resultado = stmt.executeQuery();
            
            usuario = new Usuario();
            
            usuario.setIdUsuario(id);
            usuario.setNomeUsuario(resultado.getString("nome_usuario"));
            usuario.setCPFUsuario(resultado.getString("cpf_usuario"));
            usuario.setTelefoneUsuario(resultado.getString("telefone_usuario"));
            usuario.setSexoUsuario(resultado.getString("sexo_usuario"));
            usuario.setEnderecoUsuario(resultado.getString("endereco_usuario"));
            usuario.setEmailUsuario(resultado.getString("email_usuario"));
            usuario.setSenhaUsuario(resultado.getString("senha_usuario"));
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return usuario;
    }
    
    /**
     * Salva o usuario passado pelo parâmetro no banco de dados.
     * 
     * @param usuario o usuario a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Usuario usuario){
        String sql = "INSERT INTO Usuario(nome_usuario, cpf_usuario, telefone_usuario, "
                + "sexo_usuario, endereco_usuario, email_usuario, senha_usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
        
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
            System.out.println(ex.getMessage());
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
        String sql = "UPDATE Usuario SET nome_usuario=?, cpf_usuario=?, telefone_usuario=?, "
                + "sexo_usuario=?, endereco_usuario=?, email_usuario=?, senha_usuario=? "
                + "WHERE id_usuario=?";
        
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
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o usuario passado pelo parâmetro no banco de dados.
     * 
     * @deprecated use {@link #desativar(model.Usuario) }
     * @param usuario o usuario a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    @Deprecated
    public boolean deletar(Usuario usuario){
        String sql = "DELETE FROM Usuario "
                + "WHERE id_usuario=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, usuario.getIdUsuario());
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
     * @param usuario o usuario a ser desativado
     * @return true, se conseguir desativar, e false se não conseguir
     */
    public boolean desativar(Usuario usuario){
        String sql = "UPDATE Usuario SET ativo=? "
                + "WHERE id_usuario=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setInt(2, usuario.getIdUsuario());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
