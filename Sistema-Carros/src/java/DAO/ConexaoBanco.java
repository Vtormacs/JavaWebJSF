package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBanco {

    private static Connection conexao;
    private static final String url = "jdbc:mysql://localhost/sistema-carro";
    private static final String user = "root";
    private static final String senha = "12345";

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(url, user, senha);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexao;
    }
    
     public static void fecharConexao(){
         if(conexao != null){
             try {
                 conexao.close();
                 conexao = null;
             } catch (SQLException ex) {
                 Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
             }         
         }
     }

}
