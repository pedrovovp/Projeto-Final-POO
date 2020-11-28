public class Fornecedor {
    private int id;
    static int contadorFornecedor = 0;
    private String nome;
    private String telefone;
    private String cnpj;

    public Fornecedor(String nome, String telefone, String cnpj) {
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.id = contadorFornecedor;
        contadorFornecedor++;
    }
}
