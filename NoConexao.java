public class NoConexao {
    String destino;
    double distancia;
    NoConexao proxima;

    public NoConexao(String destino, double distancia) {
        this.destino = destino;
        this.distancia = distancia;
        this.proxima = null;
    }
}
