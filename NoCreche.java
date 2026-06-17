public class NoCreche {
    String nome;
    int indice;
    NoConexao primeiraConexao;
    NoCreche proxima;

    public NoCreche(String nome, int indice) {
        this.nome = nome;
        this.indice = indice;
        this.primeiraConexao = null;
        this.proxima = null;
    }
}
