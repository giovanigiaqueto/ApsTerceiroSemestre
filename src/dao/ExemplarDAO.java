package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Exemplar;

public class ExemplarDAO {
    
    private Connection conecta;

    public ExemplarDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<Exemplar> listarExemplares(){
        String sql = "SELECT * FROM Exemplar";
        ResultSet resultado;
        List<Exemplar> exemplares = new LinkedList<>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Exemplar exemplar = new Exemplar();
                
                exemplar.setIdExemplar(resultado.getInt("idExemplar"));
                exemplar.setIdExemplarLivro(resultado.getInt("idExemplarLivro"));
                exemplar.setEstaAlocado(resultado.getBoolean("estaAlocado"));
                exemplar.setDataObtencao(resultado.getString("dataObtencao"));
                
                exemplares.add(exemplar);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        
        return exemplares;
    }
    
    public void salvar(Exemplar exemplar){
        String sql = "INSERT INTO Exemplar(idExemplarLivro, estaAlocado, dataObtencao)"
                + "VALUES(?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, exemplar.getIdExemplarLivro());
            stmt.setBoolean(2, exemplar.getEstaAlocado());
            stmt.setString(3, exemplar.getDataObtencao());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void alterar(Exemplar exemplar){
        String sql = "UPDATE Exemplar SET idExemplarLivro=?, estaAlocado=?, "
                + "dataObtencao=?"
                + "WHERE idExemplar=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, exemplar.getIdExemplarLivro());
            stmt.setBoolean(2, exemplar.getEstaAlocado());
            stmt.setString(3, exemplar.getDataObtencao());
            stmt.setInt(4, exemplar.getIdExemplar());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void deletar(Exemplar exemplar){
        String sql = "DELETE FROM Exemplar"
                + "WHERE id=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, exemplar.getIdExemplar());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
