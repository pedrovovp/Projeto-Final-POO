package database.dao;

import database.Conexao;
import database.OperationException;
import domain.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe utilizada para executar as operações no banco de dados,
 * que envolvem o Fornecedor.
 */
public class FornecedorDAOImpl implements InterfaceDAO<Fornecedor> {
    public void incluir(Fornecedor fornecedor) {
        String incluir = "INSERT INTO FORNECEDOR VALUES (" + fornecedor.getId() + ", '"
                + fornecedor.getNome() + "', '"
                + fornecedor.getTelefone() + "', '"
                + fornecedor.getCnpj() + "');";

        Conexao conexao = new Conexao();
        conexao.executarDML(incluir);
    }

    public Fornecedor consultar(int id) throws OperationException {
        Conexao conexao = new Conexao();
        Fornecedor fornecedor = null;
        try {
            String consulta = "SELECT * FROM FORNECEDOR WHERE id_forn = '" + id + "'";
            ResultSet rs = conexao.executarConsulta(consulta);

            if(rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String cnpj = rs.getString("cnpj");
                fornecedor = new Fornecedor(nome, telefone, cnpj);
                fornecedor.setId(id);
            }
        } catch (SQLException ex) {
            throw new OperationException("Fornecedor", "Consultar", ex);
        } finally {
            conexao.desconectar();
        }

        return fornecedor;
    }

    public ArrayList<Fornecedor> listar() throws OperationException {
        Conexao conexao = new Conexao();
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM FORNECEDOR";
            ResultSet rs = conexao.executarConsulta(consulta);

            while(rs.next()) {
                int id = rs.getInt("id_forn");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String cnpj = rs.getString("cnpj");
                Fornecedor fornecedor = new Fornecedor(nome, telefone, cnpj);
                fornecedor.setId(id);
                fornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            throw new OperationException("Fornecedor", "Listar", ex);
        } finally {
            conexao.desconectar();
        }

        return fornecedores;
    }

    public void alterar(Fornecedor fornecedor) {
        String update = "UPDATE FORNECEDOR SET nome = '" + fornecedor.getNome() +
                "', telefone = '" + fornecedor.getTelefone() +
                "', cnpj = '" + fornecedor.getCnpj() +
                "' WHERE id_forn = '" +
                fornecedor.getId() + "'";
        Conexao conexao = new Conexao();
        conexao.executarDML(update);
    }

    public void excluir(int id) {
        String delete = "DELETE FROM FORNECEDOR WHERE id_forn='" + id + "'";

        Conexao conexao = new Conexao();
        conexao.executarDML(delete);
    }


}