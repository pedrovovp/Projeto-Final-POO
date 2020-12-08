import database.OperationException;
import database.dao.FornecedorDAOImpl;
import domain.Fornecedor;
import org.junit.Test;
import org.junit.Assert;

/** Classe destinada a testes */
public class Testes {

    /** Método que testa inclusão e consulta na tabela Fornecedor */
    @Test
    public void testInclusaoConsultaForn() throws OperationException {
        Fornecedor fornecedor = new Fornecedor("Teste", "981000000", "123546789");
        fornecedor.setId(0);
        FornecedorDAOImpl fornecedorDAO = new FornecedorDAOImpl();
        fornecedorDAO.excluir(0);
        fornecedorDAO.incluir(fornecedor);
        Fornecedor fornecedor1 = fornecedorDAO.consultar(0);
        Assert.assertEquals(fornecedor.getNome(), fornecedor1.getNome());
    }

    /** Método que testa inclusão e alteracao na tabela Fornecedor */
    @Test
    public void testInclusaoAlteracaoForn() throws OperationException {
        Fornecedor fornecedor = new Fornecedor("Nome", "981000000", "123546789");
        fornecedor.setId(0);
        FornecedorDAOImpl fornecedorDAO = new FornecedorDAOImpl();
        fornecedorDAO.excluir(0);
        fornecedorDAO.incluir(fornecedor);
        fornecedor.setNome("Outro Nome");
        fornecedorDAO.alterar(fornecedor);
        Fornecedor fornecedor1 = fornecedorDAO.consultar(0);
        Assert.assertEquals("Outro Nome", fornecedor1.getNome());
    }
}
