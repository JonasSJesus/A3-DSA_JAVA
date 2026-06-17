import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {

    static void carregar(Grafo grafo, String caminho) {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(caminho));
            String linha;

            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) {
                    continue;
                }

                String[] partes = linha.split(";");
                if (partes.length != 3) {
                    System.out.println("Linha ignorada (formato invalido): " + linha);
                    continue;
                }

                String origem = partes[0].trim();
                String destino = partes[1].trim();

                try {
                    double distancia = Double.parseDouble(partes[2].trim().replace(",", "."));
                    grafo.adicionarConexao(origem, destino, distancia);
                } catch (NumberFormatException e) {
                    System.out.println("Distancia invalida na linha: " + linha);
                }
            }

            leitor.close();
            System.out.println("Arquivo carregado: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
