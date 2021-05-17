package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public void salvar(Multa multa){
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
            throw new RuntimeException(ex);
        }
    }
    
    public void alterar(Multa multa){
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
            throw new RuntimeException(ex);
        }
    }
    
    public void deletar(Multa multa){
        String sql = "DELETE FROM Multa"
                + "WHERE idMulta=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, multa.getIdMulta());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
