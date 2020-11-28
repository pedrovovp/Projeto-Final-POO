public class Produto {
    private int id;
    static int contadorProduto = 0;
    private int estoque;
    private double precoDeCusto;
    private double precoDeVenda;
    private String dataUltimaCompra;
    private Fornecedor fornecedor;

    public Produto(int estoque, double precoDeCusto, double precoDeVenda, String dataUltimaCompra, Fornecedor fornecedor) {
        this.estoque = estoque;
        this.precoDeCusto = precoDeCusto;
        this.precoDeVenda = precoDeVenda;
        this.dataUltimaCompra = dataUltimaCompra;
        this.fornecedor = fornecedor;
        this.id = contadorProduto;
        contadorProduto++;
    }
}
