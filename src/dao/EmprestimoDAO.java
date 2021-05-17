package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Emprestimo;

public class EmprestimoDAO {
    
    private Connection conecta;

    public EmprestimoDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Retorna uma lista de Emprestimo do banco de dados.
     * 
     * @param numItens a quantidade máxima de tuplas retornadas
     * @param deslocamento serão retornadas as tuplas a partir deste valor
     * (começando do deslocamento + 1)
     * 
     * @return a lista de Emprestimo
     */
    public List<Emprestimo> listarEmprestimos(int numItens, int deslocamento){
        String sql = "SELECT * FROM Emprestimo LIMIT=? OFFSET=?";
        ResultSet resultado;
        List<Emprestimo> emprestimos = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, numItens);
            stmt.setInt(2, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Emprestimo emprestimo = new Emprestimo();
                
                emprestimo.setIdEmprestimo(resultado.getInt("idEmprestimo"));
                emprestimo.setIdEmprestimoCliente(resultado.getInt("idEmprestimoCliente"));
                emprestimo.setIdEmprestimoLivro(resultado.getInt("idEmprestimoExemplar"));
                emprestimo.setIdEmprestimoUsuario(resultado.getInt("idEmprestimoUsuario"));
                emprestimo.setDataEmprestimo(resultado.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(resultado.getString("dataDevolucao"));
                
                emprestimos.add(emprestimo);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return emprestimos;
    }
    
    /**
     * Retorna uma lista de Emprestimo do banco de dados.
     * 
     * @return a lista de Emprestimo
     */
    public List<Emprestimo> listarEmprestimos(){
        String sql = "SELECT * FROM Emprestimo";
        ResultSet resultado;
        List<Emprestimo> emprestimos = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Emprestimo emprestimo = new Emprestimo();
                
                emprestimo.setIdEmprestimo(resultado.getInt("idEmprestimo"));
                emprestimo.setIdEmprestimoCliente(resultado.getInt("idEmprestimoCliente"));
                emprestimo.setIdEmprestimoLivro(resultado.getInt("idEmprestimoExemplar"));
                emprestimo.setIdEmprestimoUsuario(resultado.getInt("idEmprestimoUsuario"));
                emprestimo.setDataEmprestimo(resultado.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(resultado.getString("dataDevolucao"));
                
                emprestimos.add(emprestimo);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return emprestimos;
    }
    
    /**
     * Salva o emprestimo passado pelo parâmetro no banco de dados.
     * 
     * @param emprestimo o emprestimo a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Emprestimo emprestimo){
        String sql = "INSERT INTO Emprestimo(idEmprestimoCliente, idEmprestimoExemplar, "
                + "idEmprestimoUsuario, dataEmprestimo, dataDevolucao)"
                + "VALUES(?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, emprestimo.getIdEmprestimoCliente());
            stmt.setInt(2, emprestimo.getIdEmprestimoLivro());
            stmt.setInt(3, emprestimo.getIdEmprestimoUsuario());
            stmt.setString(4, emprestimo.getDataEmprestimo());
            stmt.setString(5, emprestimo.getDataDevolucao());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex){
            return false;
        }
        
        return true;
    }
    
    /**
     * Alterar o emprestimo passado pelo parâmetro no banco de dados.
     * 
     * @param emprestimo o emprestimo a ser alterado
     * @return true, se conseguir alterar, e false se não conseguir
     */
    public boolean alterar(Emprestimo emprestimo){
        String sql = "UPDATE Emprestimo SET idEmprestimoCliente=?, "
                + "idEmprestimoExemplar=?, idEmprestimoUsuario=?, dataEmprestimo=?, "
                + "dataDevolucao=?"
                + "WHERE idEmprestimo=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, emprestimo.getIdEmprestimoCliente());
            stmt.setInt(2, emprestimo.getIdEmprestimoLivro());
            stmt.setInt(3, emprestimo.getIdEmprestimoUsuario());
            stmt.setString(4, emprestimo.getDataEmprestimo());
            stmt.setString(5, emprestimo.getDataDevolucao());
            stmt.setInt(6, emprestimo.getIdEmprestimo());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o emprestimo passado pelo parâmetro no banco de dados.
     * 
     * @param emprestimo o emprestimo a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    public boolean deletar(Emprestimo emprestimo){
        String sql = "DELETE FROM Emprestimo"
                + "WHERE id=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, emprestimo.getIdEmprestimo());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
}
