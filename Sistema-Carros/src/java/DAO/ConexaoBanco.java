package DAO;

import Util.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static Connection conexao;
    private static final String url = "jdbc:mysql://localhost/sistema-carro";
    private static final String user = "root";
    private static final String senha = "12345";

    public static Connection getConexao() throws ErroSistema {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(url, user, senha);
            } catch (ClassNotFoundException ex) {
                throw new ErroSistema("Não foi possivel conectar ao banco de dados", ex);
            } catch (SQLException ex) {
                throw new ErroSistema("O driver do banco não foi encontrado", ex);
            }
        }
        return conexao;
    }

    public static void fecharConexao() throws ErroSistema {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Erro ao fechar a conexão com banco", ex);
            }
        }
    }

}
