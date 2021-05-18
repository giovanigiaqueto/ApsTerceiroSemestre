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
import model.Exemplar;

public class ExemplarDAO {
    
    private Connection conecta;
    
    private SimpleDateFormat formataData;

    public ExemplarDAO() {
        try {
            this.conecta = CriarConexao.getConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        formataData = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    /**
     * Retorna uma lista de Exemplar do banco de dados.
     * 
     * @param numItens a quantidade máxima de tuplas retornadas
     * @param deslocamento serão retornadas as tuplas a partir deste valor
     * (começando do deslocamento + 1)
     * 
     * @return a lista de Exemplar
     */
    public List<Exemplar> listarExemplares(int numItens, int deslocamento){
        String sql = "SELECT * FROM Exemplar LIMIT ? OFFSET ?";
        ResultSet resultado;
        List<Exemplar> exemplares = new ArrayList<>(numItens);
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, numItens);
            stmt.setInt(2, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Exemplar exemplar = new Exemplar();
                
                exemplar.setIdExemplar(resultado.getInt("id_exemplar"));
                exemplar.setIdExemplarLivro(resultado.getInt("id_exemplarLivro"));
                exemplar.setEstaAlocado(resultado.getBoolean("esta_alocado"));
                exemplar.setDataObtencao(resultado.getString("data_obtencao"));
                
                exemplares.add(exemplar);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return exemplares;
    }
    
    /**
     * Retorna uma lista de Exemplar do banco de dados.
     * 
     * @return a lista de Exemplar
     */
    public List<Exemplar> listarExemplares(){
        String sql = "SELECT * FROM Exemplar";
        ResultSet resultado;
        List<Exemplar> exemplares = new LinkedList<>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Exemplar exemplar = new Exemplar();
                
                exemplar.setIdExemplar(resultado.getInt("id_exemplar"));
                exemplar.setIdExemplarLivro(resultado.getInt("id_exemplarLivro"));
                exemplar.setEstaAlocado(resultado.getBoolean("esta_alocado"));
                exemplar.setDataObtencao(resultado.getString("data_obtencao"));
                
                exemplares.add(exemplar);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return exemplares;
    }
    
    /**
     * Salva o exemplar passado pelo parâmetro no banco de dados.
     * 
     * @param exemplar o exemplar a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Exemplar exemplar){
        String sql = "INSERT INTO Exemplar(id_exemplarLivro, esta_alocado, data_obtencao) "
                + "VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, exemplar.getIdExemplarLivro());
            stmt.setBoolean(2, exemplar.getEstaAlocado());
            stmt.setDate(3, new Date(formataData.parse(exemplar.getDataObtencao()).getTime()));
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(ExemplarDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    /**
     * Altera o exemplar passado pelo parâmetro no banco de dados.
     * 
     * @param exemplar o exemplar a ser alterado
     * @return true, se conseguir alterar, e false se não conseguir
     */
    public boolean alterar(Exemplar exemplar){
        String sql = "UPDATE Exemplar SET id_exemplarLivro=?, esta_alocado=?, "
                + "data_obtencao=? "
                + "WHERE id_exemplar=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, exemplar.getIdExemplarLivro());
            stmt.setBoolean(2, exemplar.getEstaAlocado());
            stmt.setDate(3, new Date(formataData.parse(exemplar.getDataObtencao()).getTime()));
            stmt.setInt(4, exemplar.getIdExemplar());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(ExemplarDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta o exemplar passado pelo parâmetro no banco de dados.
     * 
     * @param exemplar o exemplar a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    public boolean deletar(Exemplar exemplar){
        String sql = "DELETE FROM Exemplar "
                + "WHERE id_exemplar=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, exemplar.getIdExemplar());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
