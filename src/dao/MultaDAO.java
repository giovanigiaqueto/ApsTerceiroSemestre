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
        String sql = "SELECT * FROM Multa "
                + "WHERE ativo=? "
                + "LIMIT ? OFFSET ?";
        ResultSet resultado;
        List<Multa> multas = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, numItens);
            stmt.setInt(3, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Multa multa = new Multa();
                
                multa.setIdMulta(resultado.getInt("id_multa"));
                multa.setIdMultaCliente(resultado.getInt("id_multa_cliente"));
                multa.setIdMultaEmprestimo(resultado.getInt("id_multa_emprestimo"));
                multa.setDescricaoMulta(resultado.getString("descricao_multa"));
                multa.setValorMulta(resultado.getDouble("valor_multa"));
                multa.setPagamentoMulta(resultado.getBoolean("pagamento_multa"));
                
                multas.add(multa);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        String sql = "SELECT * FROM Multa "
                + "WHERE ativo=?";
        ResultSet resultado;
        List<Multa> multas = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Multa multa = new Multa();
                
                multa.setIdMulta(resultado.getInt("id_multa"));
                multa.setIdMultaCliente(resultado.getInt("id_multa_cliente"));
                multa.setIdMultaEmprestimo(resultado.getInt("id_multa_emprestimo"));
                multa.setDescricaoMulta(resultado.getString("descricao_multa"));
                multa.setValorMulta(resultado.getDouble("valor_multa"));
                multa.setPagamentoMulta(resultado.getBoolean("pagamento_multa"));
                
                multas.add(multa);
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        String sql = "INSERT INTO Multa(id_multa_cliente, id_multa_emprestimo, "
                + "descricao_multa, valor_multa, pagamento_multa) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, multa.getIdMultaCliente());
            stmt.setInt(2, multa.getIdMultaEmprestimo());
            stmt.setString(3, multa.getDescricaoMulta());
            stmt.setDouble(4, multa.getValorMulta());
            stmt.setBoolean(5, multa.getPagamentoMulta());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        String sql = "UPDATE Multa SET id_multa_cliente=?, id_multa_emprestimo=?, "
                + "descricao_multa=?, valor_multa=?, pagamento_multa=? "
                + "WHERE id_multa=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, multa.getIdMultaCliente());
            stmt.setInt(2, multa.getIdMultaEmprestimo());
            stmt.setString(3, multa.getDescricaoMulta());
            stmt.setDouble(4, multa.getValorMulta());
            stmt.setBoolean(5, multa.getPagamentoMulta());
            stmt.setInt(6, multa.getIdMulta());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Deleta a multa passada pelo parâmetro no banco de dados.
     * 
     * @deprecated use {@link #desativar(model.Multa) }
     * @param multa a multa a ser deletada
     * @return true, se conseguir deletar, e false se não conseguir
     */
    public boolean deletar(Multa multa){
        String sql = "DELETE FROM Multa "
                + "WHERE id_multa=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, multa.getIdMulta());
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
     * @param multa a multa a ser desativada
     * @return true, se conseguir desativar, e false se não conseguir
     */
    public boolean desativar(Multa multa){
        String sql = "UPDATE Multa SET ativo=? "
                + "WHERE id_multa=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setInt(2, multa.getIdMulta());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
