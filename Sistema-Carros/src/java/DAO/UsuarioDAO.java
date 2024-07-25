package DAO;

import Entity.Usuario;
import Util.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements CrudDAO<Usuario> {

    @Override
    public void salvar(Usuario entidade) throws ErroSistema {
        try {
            Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement ps;

            if (entidade.getId() == null) {
                ps = conexao.prepareStatement("INSERT INTO usuario(login, senha) VALUES (?,?)");
            } else {
                ps = conexao.prepareStatement("UPDATE usuario SET login = ?, senha = ? WHERE id = ?");
                ps.setInt(5, entidade.getId());
            }

            ps.setString(1, entidade.getLogin());
            ps.setString(2, entidade.getSenha());

            ps.execute();
            ConexaoBanco.fecharConexao();

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar usuario", ex);
        }
    }

    @Override
    public void deletar(Usuario entidade) throws ErroSistema {
        try {
            Connection conexao = ConexaoBanco.getConexao();

            PreparedStatement ps = conexao.prepareStatement("DELETE FROM carros WHERE id = ?");

            ps.setInt(1, entidade.getId());

            ps.execute();

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao excluir usuario", ex);
        }
    }

    @Override
    public List<Usuario> buscar() throws ErroSistema {
        try {
            Connection conexao = ConexaoBanco.getConexao();

            String sql = "SELECT * FROM usuario";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet resultado = ps.executeQuery();

            List<Usuario> lista = new ArrayList<>();

            while (resultado.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(resultado.getInt("id"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));

                lista.add(usuario);
            }
            ConexaoBanco.fecharConexao();
            return lista;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao listar usuarios", ex);
        }
    }

}
