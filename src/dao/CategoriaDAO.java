package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categoria;

public class CategoriaDAO {
    
    private Connection conecta;

    public CategoriaDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Retorna uma lista de Categoria do banco de dados.
     * 
     * @param numItens a quantidade máxima de tuplas retornadas
     * @param deslocamento serão retornadas as tuplas a partir deste valor
     * (começando do deslocamento + 1)
     * 
     * @return a lista de Categoria
     */
    public List<Categoria> listarCategorias(int numItens, int deslocamento){
        String sql = "SELECT * FROM Categoria "
                + "WHERE ativo=? "
                + "LIMIT ? OFFSET ?";
        ResultSet resultado;
        List<Categoria> categorias = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, numItens);
            stmt.setInt(3, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(resultado.getInt("id_categoria"));
                categoria.setNomeCategoria(resultado.getString("nome_categoria"));
                categoria.setDescricaoCategoria(resultado.getString("descricao_categoria"));
                
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return categorias;
    }
    
    /**
     * Retorna uma lista de Categoria do banco de dados.
     * 
     * @return a lista de Categoria
     */
    public List<Categoria> listarCategorias(){
        String sql = "SELECT * FROM Categoria "
                + "WHERE ativo=?";
        ResultSet resultado;
        List<Categoria> categorias = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(resultado.getInt("id_categoria"));
                categoria.setNomeCategoria(resultado.getString("nome_categoria"));
                categoria.setDescricaoCategoria(resultado.getString("descricao_categoria"));
                
                categorias.add(categoria);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return categorias;
    }
    
    /**
     * Salva a categoria passada pelo parâmetro no banco de dados.
     * 
     * @param categoria a categoria a ser salva
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Categoria categoria){
        String sql = "INSERT INTO Categoria(nome_categoria, descricao_categoria) "
                + "VALUES (?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricaoCategoria());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Altera a categoria passada pelo parâmetro no banco de dados.
     * 
     * @param categoria a categoria a ser alterada
     * @return true, se conseguir alterar, e false se não conseguir
     */
    public boolean alterar(Categoria categoria){
        String sql = "UPDATE Categoria SET nome_categoria=?, descricao_categoria=? "
                + "WHERE id_categoria=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricaoCategoria());
            stmt.setInt(3, categoria.getIdCategoria());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta a categoria passada pelo parâmetro no banco de dados.
     * 
     * @deprecated use {@link #desativar(model.Categoria) }
     * @param categoria a categoria a ser deletada
     * @return true, se conseguir deletar, e false se não conseguir
     */
    @Deprecated
    public boolean deletar(Categoria categoria){
        String sql = "DELETE FROM Categoria "
                + "WHERE id_categoria=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, categoria.getIdCategoria());
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
     * @param categoria a categoria a ser desativada
     * @return true, se conseguir desativar, e false se não conseguir
     */
    public boolean desativar(Categoria categoria){
        String sql = "UPDATE Categoria SET ativo=? "
                + "WHERE id_categoria=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setInt(2, categoria.getIdCategoria());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
