package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Livro;

public class LivroDAO {
    
    private Connection conecta;
    
    private SimpleDateFormat formataData;

    public LivroDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        formataData = new SimpleDateFormat("yyyy-MM-dd");
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
        String sql = "SELECT * FROM Livro "
                + "WHERE ativo=? "
                + "LIMIT ? OFFSET ?";
        ResultSet resultado;
        List<Livro> livros = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, numItens);
            stmt.setInt(3, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Livro livro = new Livro();
                
                livro.setIdLivro(resultado.getInt("id_livro"));
                livro.setNomeLivro(resultado.getString("nome_livro"));
                livro.setISBNLivro(resultado.getString("isbn_livro"));
                livro.setAutorLivro(resultado.getString("autor_livro"));
                livro.setEditoraLivro(resultado.getString("editora_livro"));
                livro.setEdicaoLivro(resultado.getInt("edicao_livro"));
                livro.setDataLancamentoLivro(resultado.getString("data_lancamento_livro"));
                livro.setNomeLivroCategoria(resultado.getString("nome_livro_categoria"));
                livro.setEstoqueLivro(resultado.getInt("estoque_livro"));
                livro.setLocacaoLivro(resultado.getInt("locacao_livro"));
                livro.setPaginasLivro(resultado.getInt("paginas_livro"));
                livro.setPrecoLivro(resultado.getDouble("preco_livro"));
                livro.setSinopseLivro(resultado.getString("sinopse_livro"));
                
                livros.add(livro);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        String sql = "SELECT * FROM Livro "
                + "WHERE ativo=?";
        ResultSet resultado;
        List<Livro> livros = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Livro livro = new Livro();
                
                livro.setIdLivro(resultado.getInt("id_livro"));
                livro.setNomeLivro(resultado.getString("nome_livro"));
                livro.setISBNLivro(resultado.getString("isbn_livro"));
                livro.setAutorLivro(resultado.getString("autor_livro"));
                livro.setEditoraLivro(resultado.getString("editora_livro"));
                livro.setEdicaoLivro(resultado.getInt("edicao_livro"));
                livro.setDataLancamentoLivro(resultado.getString("data_lancamento_livro"));
                livro.setNomeLivroCategoria(resultado.getString("nome_livro_categoria"));
                livro.setEstoqueLivro(resultado.getInt("estoque_livro"));
                livro.setLocacaoLivro(resultado.getInt("locacao_livro"));
                livro.setPaginasLivro(resultado.getInt("paginas_livro"));
                livro.setPrecoLivro(resultado.getDouble("preco_livro"));
                livro.setSinopseLivro(resultado.getString("sinopse_livro"));
                
                livros.add(livro);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return livros;
    }
    
        /**
     * 
     * Procura o livro que tenha o ISBN passado.
     * 
     * @param isbn o ISBN para procurar o livro
     * @return 
     */
    public Livro procurarPorISBN(String isbn){
        String sql = "SELECT * FROM Livro "
                + "WHERE isbn_livro = ?";
        
        Livro livro = null;
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, isbn);
            ResultSet resultado = stmt.executeQuery();
            
            if(resultado.next()){
                livro = new Livro();
                
                livro.setIdLivro(resultado.getInt("id_livro"));
                livro.setNomeLivro(resultado.getString("nome_livro"));
                livro.setISBNLivro(resultado.getString("isbn_livro"));
                livro.setAutorLivro(resultado.getString("autor_livro"));
                livro.setEditoraLivro(resultado.getString("editora_livro"));
                livro.setEdicaoLivro(resultado.getInt("edicao_livro"));
                livro.setDataLancamentoLivro(resultado.getString("data_lancamento_livro"));
                livro.setNomeLivroCategoria(resultado.getString("nome_livro_categoria"));
                livro.setEstoqueLivro(resultado.getInt("estoque_livro"));
                livro.setLocacaoLivro(resultado.getInt("locacao_livro"));
                livro.setPaginasLivro(resultado.getInt("paginas_livro"));
                livro.setPrecoLivro(resultado.getDouble("preco_livro"));
                livro.setSinopseLivro(resultado.getString("sinopse_livro"));
            }
            
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return livro;
    }
    
    /**
     * Retorna um Livro dado seu id, gera RuntimeError se esse id não existir
     * 
     * @param id o id do livro a ser procurado
     * 
     * @return o livro com o id
     */
    public Livro procurarLivro(int id){
        String sql = "SELECT * FROM Livro WHERE ativo=? AND id_livro=?";
        
        Livro livro = null;
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                livro = new Livro();

                livro.setIdLivro(resultado.getInt("id_livro"));
                livro.setNomeLivro(resultado.getString("nome_livro"));
                livro.setISBNLivro(resultado.getString("isbn_livro"));
                livro.setAutorLivro(resultado.getString("autor_livro"));
                livro.setEditoraLivro(resultado.getString("editora_livro"));
                livro.setEdicaoLivro(resultado.getInt("edicao_livro"));
                livro.setDataLancamentoLivro(resultado.getString("data_lancamento_livro"));
                livro.setNomeLivroCategoria(resultado.getString("nome_livro_categoria"));
                livro.setEstoqueLivro(resultado.getInt("estoque_livro"));
                livro.setLocacaoLivro(resultado.getInt("locacao_livro"));
                livro.setPaginasLivro(resultado.getInt("paginas_livro"));
                livro.setPrecoLivro(resultado.getDouble("preco_livro"));
                livro.setSinopseLivro(resultado.getString("sinopse_livro"));
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return livro;
    }
    
    /**
     * Salva o livro passado pelo parâmetro no banco de dados.
     * 
     * @param livro o livro a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Livro livro){
        String sql = "INSERT INTO Livro(nome_livro, isbn_livro, autor_livro, editora_livro,"
                + "edicao_livro, data_lancamento_livro, nome_livro_categoria, estoque_livro,"
                + "locacao_livro, paginas_livro, preco_livro, sinopse_livro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, livro.getNomeLivro());
            stmt.setString(2, livro.getISBNLivro());
            stmt.setString(3, livro.getAutorLivro());
            stmt.setString(4, livro.getEditoraLivro());
            stmt.setInt(5, livro.getEdicaoLivro());
            stmt.setDate(6, new Date(formataData.parse(livro.getDataLancamentoLivro()).getTime()));
            stmt.setString(7, livro.getNomeLivroCategoria());
            stmt.setInt(8, livro.getEstoqueLivro());
            stmt.setInt(9, livro.getLocacaoLivro());
            stmt.setInt(10, livro.getPaginasLivro());
            stmt.setDouble(11, livro.getPrecoLivro());
            stmt.setString(12, livro.getSinopseLivro());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "UPDATE Livro SET nome_livro=?, isbn_livro=?, autor_livro=?, "
                + "editora_livro=?, edicao_livro=?, data_lancamento_livro=?, "
                + "nome_livro_categoria=?, estoque_livro=?, locacao_livro=?, paginas_livro=?, "
                + "preco_livro=?, sinopse_livro=? "
                + "WHERE id_livro=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, livro.getNomeLivro());
            stmt.setString(2, livro.getISBNLivro());
            stmt.setString(3, livro.getAutorLivro());
            stmt.setString(4, livro.getEditoraLivro());
            stmt.setInt(5, livro.getEdicaoLivro());
            stmt.setDate(6, new Date(formataData.parse(livro.getDataLancamentoLivro()).getTime()));
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
            System.out.println(ex.getMessage());
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o livro passado pelo parâmetro no banco de dados.
     * 
     * @deprecated use {@link #desativar(model.Livro) }
     * @param livro o livro a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    @Deprecated
    public boolean deletar(Livro livro){
        String sql = "DELETE FROM Livro "
                + "WHERE id_livro=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, livro.getIdLivro());
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
     * @param livro o livro a ser desativado
     * @return true, se conseguir desativar, e false se não conseguir
     */
    public boolean desativar(Livro livro){
        String sql = "UPDATE Livro SET ativo=? "
                + "WHERE id_livro=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setInt(2, livro.getIdLivro());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
