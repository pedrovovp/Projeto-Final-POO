package database.dao;

import database.Conexao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
    public int contarTuplas(String tabela) {

        Conexao conexao = new Conexao();

        int quantidade = 0;
        try {
            String contar = "SELECT COUNT(*) AS total FROM " + tabela;
            ResultSet rs = conexao.executarConsulta(contar);
            rs.next();
            quantidade = rs.getInt("total");
        } catch (SQLException ex) {
            System.out.println("Nao conseguiu contar os dados.");
        } finally {
            conexao.desconectar();
        }

        return quantidade;
    }
}
