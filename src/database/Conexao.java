package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe utilizada para executar as ações sobre o banco de dados.
 */
public class Conexao {
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    private Connection conectar() {
        try {
            String usuario = "postgres";
            String senha = "admin";
            String ipDoBanco = "localhost";
            String nomeDoBanco = "loja";
            String stringDeConexao = "jdbc:postgresql://" + ipDoBanco + "/" + nomeDoBanco;
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(stringDeConexao, usuario, senha);
            System.out.println("Conectou no banco de dados.");
        } catch (SQLException ex) {
            System.out.println("Erro: Não conseguiu conectar no BD.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Não encontrou o driver do BD.");
        }

        return conn;
    }

    public void criarTabela(String instrucaoSQL) {
        conn = conn == null ? conectar() : conn;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(instrucaoSQL);
            st.close();
        }catch (Exception e) {
            System.out.println("Erro ao criar tabela");
        }
    }

    public ResultSet executarConsulta(String consulta) {
        conn = conn == null ? conectar() : conn;
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("Não conseguiu executar a consulta\n" + consulta);
            //Caso ocorra algum erro desconecta do banco de dados.
            desconectar();
        }

        return rs;
    }

    public boolean executarDML(String dml) {
        boolean ok = false;

        conn = conn == null ? conectar() : conn;
        try {
            stm = conn.createStatement();
            stm.execute(dml);
            ok = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Nao conseguiu executar o DML\n" + dml);
        } finally {
            desconectar();
        }

        return ok;
    }

    public void desconectar() {
        fecharResultSet(this.rs);
        fecharStatement(this.stm);
        fecharConnection(this.conn);
    }

    public void fecharConnection(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectou do banco de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Nao conseguiu desconectar do BD.");
        }
    }

    public void fecharStatement(Statement stm) {
        try {
            if(stm != null && !stm.isClosed()) {
                stm.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar o procedimento de consulta.");
        }
    }

    public void fecharResultSet(ResultSet resultado) {
        try {
            if(resultado != null && !resultado.isClosed()) {
                resultado.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar o resultado da consulta.");
        }
    }
}