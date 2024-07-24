package DAO;

import Entity.Carro;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CarroDAO {

    public void salvar(Carro obj) {
        try {
            Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement ps;

            if (obj.getId() == null) {
                ps = conexao.prepareStatement("INSERT INTO carros (modelo, fabricante, cor, ano) VALUES (?,?,?,?)");
            }else{
                ps = conexao.prepareStatement("UPDATE carros SET modelo = ?, fabricante = ?, cor = ?, ano = ? WHERE id = ?");
                ps.setInt(5, obj.getId());
            }

            ps.setString(1, obj.getModelo());
            ps.setString(2, obj.getFabricante());
            ps.setString(3, obj.getCor());
            ps.setDate(4, new Date(obj.getAno().getTime()));

            ps.execute();
            ConexaoBanco.fecharConexao();

            JOptionPane.showMessageDialog(null, "Carro salvo com sucesso!!");

        } catch (HeadlessException | SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Carro!!" + erro);
        }
    }

    public List<Carro> listar() {
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
            JOptionPane.showMessageDialog(null, "Lista de Carros Listada!!");
            ConexaoBanco.fecharConexao();
            return lista;
        } catch (HeadlessException | SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Listar os Carros!!" + erro);
        }
        return null;
    }
}
