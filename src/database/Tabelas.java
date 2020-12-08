package database;

/** Classe quea agrupa os comandos SQL para criar as tabelas*/

public class Tabelas {
    private String localizacao = "CREATE TABLE localizacao(\n" +
            "id_loc int PRIMARY KEY NOT NULL,\n" +
            "estado varchar(80) NOT NULL,\n" +
            "endereco varchar(80) NOT NULL,\n" +
            "bairro varchar(80) NOT NULL,\n" +
            "cidade varchar(80) NOT NULL\n" +
            ");";

    private String fornecedor = "CREATE TABLE fornecedor(\n" +
            "id_forn int PRIMARY KEY,\n" +
            "nome varchar(80) NOT NULL,\n" +
            "telefone varchar(80) NOT NULL,\n" +
            "cnpj varchar(80) NOT NULL);\n";

    private String loja = "CREATE TABLE loja(\n" +
            "id_loja int PRIMARY KEY,\n" +
            "nome varchar(80) NOT NULL,\n" +
            "id_loc int REFERENCES Localizacao(id_loc) ON DELETE CASCADE );";

    private String produto = "CREATE TABLE produto(\n" +
            "id_prod int PRIMARY KEY,\n" +
            "estoque int,\n" +
            "preco_de_custo decimal,\n" +
            "preco_de_venda decimal,\n" +
            "data_ultima_compra varchar(80),\n" +
            "id_loja int REFERENCES loja(id_loja) ON DELETE CASCADE,\n" +
            "id_forn int REFERENCES Fornecedor(id_forn) ON DELETE CASCADE);";

    /** Método que cria a tabela */
    public void criarTabelas() {
        Conexao conn = new Conexao();
        conn.criarTabela(localizacao+fornecedor+loja+produto);
    }
}
