public class Localizacao {
    private int id;
    static int contadorLocalizacao = 0;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;

    public Localizacao(String endereco, String bairro, String cidade, String estado) {
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.id = contadorLocalizacao;
        contadorLocalizacao++;
    }
}
