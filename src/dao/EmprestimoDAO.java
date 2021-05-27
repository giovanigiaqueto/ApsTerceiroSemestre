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
        String sql = "SELECT * FROM Emprestimo "
                + "WHERE ativo=? "
                + "LIMIT ? OFFSET ?";
        ResultSet resultado;
        List<Emprestimo> emprestimos = new ArrayList<>(numItens);
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, numItens);
            stmt.setInt(3, deslocamento);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Emprestimo emprestimo = new Emprestimo();
                
                emprestimo.setIdEmprestimo(resultado.getInt("id_emprestimo"));
                emprestimo.setIdEmprestimoCliente(resultado.getInt("id_emprestimo_cliente"));
                emprestimo.setIdEmprestimoExemplar(resultado.getInt("id_emprestimo_exemplar"));
                emprestimo.setIdEmprestimoUsuario(resultado.getInt("id_emprestimo_usuario"));
                emprestimo.setDataEmprestimo(resultado.getString("data_emprestimo"));
                emprestimo.setDataDevolucao(resultado.getString("data_devolucao"));
                
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
        String sql = "SELECT * FROM Emprestimo "
                + "WHERE ativo=?";
        ResultSet resultado;
        List<Emprestimo> emprestimos = new LinkedList<>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            resultado = stmt.executeQuery();
            
            while(resultado.next()){
                Emprestimo emprestimo = new Emprestimo();
                
                emprestimo.setIdEmprestimo(resultado.getInt("id_emprestimo"));
                emprestimo.setIdEmprestimoCliente(resultado.getInt("id_emprestimo_cliente"));
                emprestimo.setIdEmprestimoExemplar(resultado.getInt("id_emprestimo_exemplar"));
                emprestimo.setIdEmprestimoUsuario(resultado.getInt("id_emprestimo_usuario"));
                emprestimo.setDataEmprestimo(resultado.getString("data_emprestimo"));
                emprestimo.setDataDevolucao(resultado.getString("data_devolucao"));
                
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
     * Retorna um Emprestimo dado seu id, gera RuntimeError se esse id não existir
     * 
     * @param id o id do emprestimo a ser procurado
     * 
     * @return o exemplar com o id
     */
    public Emprestimo procurarEmprestimo(int id){
        String sql = "SELECT * FROM Emprestimo "
                + "WHERE ativo=? AND id_emprestimo=?";
        
        Emprestimo emprestimo = null;
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                emprestimo = new Emprestimo();
            
                emprestimo.setIdEmprestimo(resultado.getInt("id_emprestimo"));
                emprestimo.setIdEmprestimoCliente(resultado.getInt("id_emprestimo_cliente"));
                emprestimo.setIdEmprestimoExemplar(resultado.getInt("id_emprestimo_exemplar"));
                emprestimo.setIdEmprestimoUsuario(resultado.getInt("id_emprestimo_usuario"));
                emprestimo.setDataEmprestimo(resultado.getString("data_emprestimo"));
                emprestimo.setDataDevolucao(resultado.getString("data_devolucao"));
            }
            
            resultado.close();
            stmt.close();
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        
        return emprestimo;
    }
    
    
    /**
     * Salva o emprestimo passado pelo parâmetro no banco de dados.
     * 
     * @param emprestimo o emprestimo a ser salvo
     * @return true, se conseguir salvar, e false se não conseguir
     */
    public boolean salvar(Emprestimo emprestimo){
        String sql = "INSERT INTO Emprestimo(id_emprestimo_cliente, id_emprestimo_exemplar, "
                + "id_emprestimo_usuario, data_emprestimo, data_devolucao) "
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
        String sql = "UPDATE Emprestimo SET id_emprestimo_cliente=?, "
                + "id_emprestimo_exemplar=?, id_emprestimo_usuario=?, data_emprestimo=?, "
                + "data_devolucao=? "
                + "WHERE id_emprestimo=?";
        
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
     * @deprecated use {@link #desativar(model.Emprestimo) }
     * @param emprestimo o emprestimo a ser deletado
     * @return true, se conseguir deletar, e false se não conseguir
     */
    @Deprecated
    public boolean deletar(Emprestimo emprestimo){
        String sql = "DELETE FROM Emprestimo "
                + "WHERE id_emprestimo=?";
        
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
    
    /**
     * Coloca o campo ativo como falso
     * 
     * @param emprestimo o emprestimo a ser desativado
     * @return true, se conseguir desativar, e false se não conseguir
     */
    public boolean desativar(Emprestimo emprestimo){
        String sql = "UPDATE Emprestimo SET ativo=? "
                + "WHERE id_emprestimo=?";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setInt(2, emprestimo.getIdEmprestimo());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
