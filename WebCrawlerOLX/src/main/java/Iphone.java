public class Iphone {
    private String nome, endereco, url, preco;

    public Iphone() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getUrl() {
        return url;
    }

    public String getPreco() {
        return preco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
