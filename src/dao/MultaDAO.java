package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Multa;

public class MultaDAO {
    
    private Connection conecta;

    public MultaDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Retorna uma lista de Multa do banco de dados.
     * 
     * @param numItens a quantidade máxima de tuplas retornadas
     * @param deslocamento serão retornadas as tuplas a partir deste valor
     * (começando do deslocamento + 1)
     * 
     * @return a lista de Multa
     */
    public List<Multa> listarMultas(int numItens, int deslocamento){
        String sql = "SELECT * FROM Multa LIMIT=? OFFSET=?";
        ResultSet resultado;
        List<Multa> multas = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, numItens);
            stmt.setInt(2, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Multa multa = new Multa();
                
                multa.setIdMulta(resultado.getInt("idMulta"));
                multa.setIdMultaCliente(resultado.getInt("idMultaCliente"));
                multa.setIdMultaEmprestimo(resultado.getInt("idMultaEmprestimo"));
                multa.setDescricaoMulta(resultado.getString("descricaoMulta"));
                multa.setValorMulta(resultado.getDouble("valorMulta"));
                
                multas.add(multa);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return multas;
    }
    
    /**
     * Retorna uma lista de Multa do banco de dados.
     * 
     * @return a lista de Multa
     */
    public List<Multa> listarMultas(){
        String sql = "SELECT * FROM Multa";
        ResultSet resultado;
        List<Multa> multas = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Multa multa = new Multa();
                
                multa.setIdMulta(resultado.getInt("idMulta"));
                multa.setIdMultaCliente(resultado.getInt("idMultaCliente"));
                multa.setIdMultaEmprestimo(resultado.getInt("idMultaEmprestimo"));
                multa.setDescricaoMulta(resultado.getString("descricaoMulta"));
                multa.setValorMulta(resultado.getDouble("valorMulta"));
                
                multas.add(multa);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return multas;
    }
    
    /**
     * Salva a multa passada pelo parâmetro no banco de dados.
     * 
     * @param multa a multa a ser salva
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Multa multa){
        String sql = "INSERT INTO Multa(idMultaCliente, idMultaEmprestimo, "
                + "descricaoMulta, valorMulta)"
                + "VALUES(?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, multa.getIdMultaCliente());
            stmt.setInt(2, multa.getIdMultaEmprestimo());
            stmt.setString(3, multa.getDescricaoMulta());
            stmt.setDouble(4, multa.getValorMulta());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Altera a multa passada pelo parâmetro no banco de dados.
     * 
     * @param multa a multa a ser alterada
     * @return true, se conseguir alterar, e false se não conseguir
     */
    public boolean alterar(Multa multa){
        String sql = "UPDATE Multa SET idMultaCliente=?, idMultaEmprestimo=?, "
                + "descricaoMulta=?, valorMulta=?"
                + "WHERE idMulta=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, multa.getIdMultaCliente());
            stmt.setInt(2, multa.getIdMultaEmprestimo());
            stmt.setString(3, multa.getDescricaoMulta());
            stmt.setDouble(4, multa.getValorMulta());
            stmt.setInt(5, multa.getIdMulta());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta a multa passada pelo parâmetro no banco de dados.
     * 
     * @param multa a multa a ser deletada
     * @return true, se conseguir deletar, e false se não conseguir
     */
    public boolean deletar(Multa multa){
        String sql = "DELETE FROM Multa"
                + "WHERE idMulta=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, multa.getIdMulta());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
    
}
