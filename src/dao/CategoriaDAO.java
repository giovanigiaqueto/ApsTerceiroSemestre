package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
    
    public List<Categoria> listarCategorias(){
        String sql = "SELECT * FROM Categoria";
        ResultSet resultado;
        List<Categoria> categorias = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(resultado.getInt("idCategoria"));
                categoria.setNomeCategoria(resultado.getString("nomeCategoria"));
                categoria.setDescricaoCategoria(resultado.getString("descricaoCategoria"));
                
                categorias.add(categoria);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return categorias;
    }
    
    public void salvar(Categoria categoria){
        String sql = "INSERT INTO Categoria(nomeCategoria, descricaoCategoria)"
                + "VALUES(?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricaoCategoria());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void alterar(Categoria categoria){
        String sql = "UPDATE Categoria SET nomeCategoria=?, descricaoCategoria=?"
                + "WHERE id=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricaoCategoria());
            stmt.setInt(3, categoria.getIdCategoria());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void deletar(Categoria categoria){
        String sql = "DELETE FROM Categoria "
                + "WHERE id=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, categoria.getIdCategoria());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
