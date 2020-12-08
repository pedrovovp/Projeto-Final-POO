package domain;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPrecoDeCusto() {
        return precoDeCusto;
    }

    public void setPrecoDeCusto(double precoDeCusto) {
        this.precoDeCusto = precoDeCusto;
    }

    public double getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }

    public String getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(String dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", estoque=" + estoque +
                ", precoDeCusto=" + precoDeCusto +
                ", precoDeVenda=" + precoDeVenda +
                ", dataUltimaCompra='" + dataUltimaCompra + '\'' +
                ", fornecedor=" + fornecedor +
                '}';
    }
}
