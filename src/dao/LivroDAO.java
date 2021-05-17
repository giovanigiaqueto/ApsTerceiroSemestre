package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Livro;

public class LivroDAO {
    
    private Connection conecta;

    public LivroDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Retorna uma lista de Livro do banco de dados.
     * 
     * @param numItens a quantidade máxima de tuplas retornadas
     * @param deslocamento serão retornadas as tuplas a partir deste valor
     * (começando do deslocamento + 1)
     * 
     * @return a lista de Livro
     */
    public List<Livro> listarLivros(int numItens, int deslocamento){
        String sql = "SELECT * FROM Livro LIMIT=? OFFSET=?";
        ResultSet resultado;
        List<Livro> livros = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, numItens);
            stmt.setInt(2, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Livro livro = new Livro();
                
                livro.setIdLivro(resultado.getInt("idLivro"));
                livro.setNomeLivro(resultado.getString("nomeLivro"));
                livro.setISBNLivro(resultado.getString("ISBNLivro"));
                livro.setAutorLivro(resultado.getString("autorLivro"));
                livro.setEditoraLivro(resultado.getString("editoraLivro"));
                livro.setEdicaoLivro(resultado.getInt("edicaoLivro"));
                livro.setDataLancamentoLivro(resultado.getString("dataLancamentoLivro"));
                livro.setNomeLivroCategoria(resultado.getString("nomeLivroCategoria"));
                livro.setEstoqueLivro(resultado.getInt("estoqueLivro"));
                livro.setLocacaoLivro(resultado.getInt("locacaoLivro"));
                livro.setPaginasLivro(resultado.getInt("paginasLivro"));
                livro.setPrecoLivro(resultado.getDouble("precoLivro"));
                livro.setSinopseLivro(resultado.getString("sinopseLivro"));
                
                livros.add(livro);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return livros;
    }
    
    /**
     * Retorna uma lista de Livro do banco de dados.
     * 
     * @return a lista de Livro
     */
    public List<Livro> listarLivros(){
        String sql = "SELECT * FROM Livro";
        ResultSet resultado;
        List<Livro> livros = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Livro livro = new Livro();
                
                livro.setIdLivro(resultado.getInt("idLivro"));
                livro.setNomeLivro(resultado.getString("nomeLivro"));
                livro.setISBNLivro(resultado.getString("ISBNLivro"));
                livro.setAutorLivro(resultado.getString("autorLivro"));
                livro.setEditoraLivro(resultado.getString("editoraLivro"));
                livro.setEdicaoLivro(resultado.getInt("edicaoLivro"));
                livro.setDataLancamentoLivro(resultado.getString("dataLancamentoLivro"));
                livro.setNomeLivroCategoria(resultado.getString("nomeLivroCategoria"));
                livro.setEstoqueLivro(resultado.getInt("estoqueLivro"));
                livro.setLocacaoLivro(resultado.getInt("locacaoLivro"));
                livro.setPaginasLivro(resultado.getInt("paginasLivro"));
                livro.setPrecoLivro(resultado.getDouble("precoLivro"));
                livro.setSinopseLivro(resultado.getString("sinopseLivro"));
                
                livros.add(livro);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return livros;
    }
    
    /**
     * Salva o livro passado pelo parâmetro no banco de dados.
     * 
     * @param livro o livro a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Livro livro){
        String sql = "INSERT INTO Livro(nomeLivro, ISBNLivro, autorLivro, editoraLivro,"
                + "edicaoLivro, dataLancamentoLivro, nomeLivroCategoria, estoqueLivro,"
                + "locacaoLivro, paginasLivro, precoLivro, sinopseLivro)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, livro.getNomeLivro());
            stmt.setString(2, livro.getISBNLivro());
            stmt.setString(3, livro.getAutorLivro());
            stmt.setString(4, livro.getEditoraLivro());
            stmt.setInt(5, livro.getEdicaoLivro());
            stmt.setString(6, livro.getDataLancamentoLivro());
            stmt.setString(7, livro.getNomeLivroCategoria());
            stmt.setInt(8, livro.getEstoqueLivro());
            stmt.setInt(9, livro.getLocacaoLivro());
            stmt.setInt(10, livro.getPaginasLivro());
            stmt.setDouble(11, livro.getPrecoLivro());
            stmt.setString(12, livro.getSinopseLivro());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Altera o livro passado pelo parâmetro no banco de dados.
     * 
     * @param livro o livro a ser alterado
     * @return true, se conseguir alterar, e false se não conseguir
     */
    public boolean alterar(Livro livro){
        String sql = "UPDATE Livro SET nomeLivro=?, ISBNLivro=?, autorLivro=?, "
                + "editoraLivro=?, edicaoLivro=?, dataLancamentoLivro=?, "
                + "nomeLivroCategoria=?, estoqueLivro=?, locacaoLivro=?, paginasLivro=?, "
                + "precoLivro=?, sinopseLivro=?"
                + "WHERE idLivro=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, livro.getNomeLivro());
            stmt.setString(2, livro.getISBNLivro());
            stmt.setString(3, livro.getAutorLivro());
            stmt.setString(4, livro.getEditoraLivro());
            stmt.setInt(5, livro.getEdicaoLivro());
            stmt.setString(6, livro.getDataLancamentoLivro());
            stmt.setString(7, livro.getNomeLivroCategoria());
            stmt.setInt(8, livro.getEstoqueLivro());
            stmt.setInt(9, livro.getLocacaoLivro());
            stmt.setInt(10, livro.getPaginasLivro());
            stmt.setDouble(11, livro.getPrecoLivro());
            stmt.setString(12, livro.getSinopseLivro());
            stmt.setInt(13, livro.getIdLivro());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o livro passado pelo parâmetro no banco de dados.
     * 
     * @param livro o livro a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    public boolean deletar(Livro livro){
        String sql = "DELETE FROM Livro"
                + "WHERE idLivro=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, livro.getIdLivro());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
}
