import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Carrega as conexões a partir do arquivo TXT.
        LeitorArquivo.carregar(grafo, "creches.txt");

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println();
            System.out.println("===== MENU =====");
            System.out.println("1 - Mostrar quantidade de conexoes");
            System.out.println("2 - Listar conexoes de uma creche");
            System.out.println("3 - Consultar distancia entre duas creches");
            System.out.println("4 - Adicionar conexao");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opcao: ");

            String entrada = scanner.nextLine().trim();
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    grafo.mostrarQuantidadeConexoes();
                    break;

                case 2:
                    System.out.print("Nome da creche: ");
                    String nome = scanner.nextLine().trim();
                    grafo.listarConexoes(nome);
                    break;

                case 3:
                    System.out.print("Creche de origem: ");
                    String origem = scanner.nextLine().trim();
                    System.out.print("Creche de destino: ");
                    String destino = scanner.nextLine().trim();
                    grafo.consultarDistancia(origem, destino);
                    break;

                case 4:
                    System.out.print("Creche de origem: ");
                    String o = scanner.nextLine().trim();
                    System.out.print("Creche de destino: ");
                    String d = scanner.nextLine().trim();
                    System.out.print("Distancia (km): ");
                    String textoDist = scanner.nextLine().trim().replace(",", ".");

                    try {
                        double distancia = Double.parseDouble(textoDist);
                        grafo.adicionarConexao(o, d, distancia);
                        System.out.println("Conexao adicionada com sucesso!");
                    } catch (NumberFormatException e) {
                        System.out.println("Distancia invalida.");
                    }
                    break;

                case 5:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}
