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
import model.Emprestimo;

public class EmprestimoDAO {
    
    private Connection conecta;
    
    private SimpleDateFormat formataData;

    public EmprestimoDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        formataData = new SimpleDateFormat("yyyy-MM-dd");
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
        String sql = "SELECT * FROM Emprestimo LIMIT ? OFFSET ?";
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
                emprestimo.setIdEmprestimoExemplar(resultado.getInt("idEmprestimoExemplar"));
                emprestimo.setIdEmprestimoUsuario(resultado.getInt("idEmprestimoUsuario"));
                emprestimo.setDataEmprestimo(resultado.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(resultado.getString("dataDevolucao"));
                
                emprestimos.add(emprestimo);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
                emprestimo.setIdEmprestimoExemplar(resultado.getInt("idEmprestimoExemplar"));
                emprestimo.setIdEmprestimoUsuario(resultado.getInt("idEmprestimoUsuario"));
                emprestimo.setDataEmprestimo(resultado.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(resultado.getString("dataDevolucao"));
                
                emprestimos.add(emprestimo);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
                + "idEmprestimoUsuario, dataEmprestimo, dataDevolucao) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, emprestimo.getIdEmprestimoCliente());
            stmt.setInt(2, emprestimo.getIdEmprestimoExemplar());
            stmt.setInt(3, emprestimo.getIdEmprestimoUsuario());
            stmt.setDate(4, new Date(formataData.parse(emprestimo.getDataEmprestimo()).getTime()));
            stmt.setDate(5, new Date(formataData.parse(emprestimo.getDataDevolucao()).getTime()));
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                + "dataDevolucao=? "
                + "WHERE idEmprestimo=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, emprestimo.getIdEmprestimoCliente());
            stmt.setInt(2, emprestimo.getIdEmprestimoExemplar());
            stmt.setInt(3, emprestimo.getIdEmprestimoUsuario());
            stmt.setDate(4, new Date(formataData.parse(emprestimo.getDataEmprestimo()).getTime()));
            stmt.setDate(5, new Date(formataData.parse(emprestimo.getDataDevolucao()).getTime()));
            stmt.setInt(6, emprestimo.getIdEmprestimo());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "DELETE FROM Emprestimo "
                + "WHERE idEmprestimo=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, emprestimo.getIdEmprestimo());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
