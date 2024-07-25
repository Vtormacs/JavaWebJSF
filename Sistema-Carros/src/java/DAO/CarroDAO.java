package DAO;

import Entity.Carro;
import Util.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO implements CrudDAO<Carro> {

    @Override
    public void salvar(Carro obj) throws ErroSistema {
        try {
            Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement ps;

            if (obj.getId() == null) {
                ps = conexao.prepareStatement("INSERT INTO carros (modelo, fabricante, cor, ano) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("UPDATE carros SET modelo = ?, fabricante = ?, cor = ?, ano = ? WHERE id = ?");
                ps.setInt(5, obj.getId());
            }

            ps.setString(1, obj.getModelo());
            ps.setString(2, obj.getFabricante());
            ps.setString(3, obj.getCor());
            ps.setDate(4, new Date(obj.getAno().getTime()));

            ps.execute();
            ConexaoBanco.fecharConexao();

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar carro", ex);
        }
    }

    @Override
    public void deletar(Carro carro) throws ErroSistema {
        try {
            Connection conexao = ConexaoBanco.getConexao();

            PreparedStatement ps = conexao.prepareStatement("DELETE FROM carros WHERE id = ?");

            ps.setInt(1, carro.getId());

            ps.execute();

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao excluir carro", ex);
        }
    }

    @Override
    public List<Carro> buscar() throws ErroSistema {
        try {
            Connection conexao = ConexaoBanco.getConexao();

            String sql = "SELECT * FROM carros";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet resultado = ps.executeQuery();

            List<Carro> lista = new ArrayList<>();

            while (resultado.next()) {
                Carro obj = new Carro();

                obj.setId(resultado.getInt("id"));
                obj.setModelo(resultado.getString("modelo"));
                obj.setFabricante(resultado.getString("fabricante"));
                obj.setCor(resultado.getString("cor"));
                obj.setAno(resultado.getDate("ano"));

                lista.add(obj);
            }
            ConexaoBanco.fecharConexao();
            return lista;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao listar carros", ex);
        }
    }
}
