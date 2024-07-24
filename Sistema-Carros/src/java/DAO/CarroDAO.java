package DAO;

import Entity.Carro;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CarroDAO {

    public void salvar(Carro obj) {
        try {
            Connection conexao = ConexaoBanco.getConexao();

            PreparedStatement ps = conexao.prepareCall("INSERT INTO carros (modelo, fabricante, cor, ano) VALUES (?,?,?,?)");

            ps.setString(1, obj.getModelo());
            ps.setString(2, obj.getFabricante());
            ps.setString(3, obj.getCor());
            ps.setInt(4, obj.getAno());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Carro salvo com sucesso!!");

        } catch (HeadlessException | SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Carro!!" + erro);
        }
    }
}
