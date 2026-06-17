public class Grafo {

    static final int MAX_CRECHES = 100;

    int[][] matrizAdjacencia = new int[MAX_CRECHES][MAX_CRECHES];
    NoCreche primeiraCreche = null;
    int totalCreches = 0;

    NoCreche buscarCreche(String nome) {
        NoCreche atual = primeiraCreche;
        while (atual != null) {
            if (atual.nome.equals(nome)) {
                return atual;
            }
            atual = atual.proxima;
        }
        return null;
    }

    NoCreche cadastrarCreche(String nome) {
        NoCreche existente = buscarCreche(nome);
        if (existente != null) {
            return existente;
        }

        if (totalCreches >= MAX_CRECHES) {
            System.out.println("Limite de creches atingido. Creche ignorada: " + nome);
            return null;
        }

        NoCreche nova = new NoCreche(nome, totalCreches);
        totalCreches++;

        if (primeiraCreche == null) {
            primeiraCreche = nova;
        } else {
            NoCreche atual = primeiraCreche;
            while (atual.proxima != null) {
                atual = atual.proxima;
            }
            atual.proxima = nova;
        }
        return nova;
    }

    void adicionarConexao(String origem, String destino, double distancia) {
        NoCreche c1 = cadastrarCreche(origem);
        NoCreche c2 = cadastrarCreche(destino);
        if (c1 == null || c2 == null) {
            return;
        }

        matrizAdjacencia[c1.indice][c2.indice] = 1;
        matrizAdjacencia[c2.indice][c1.indice] = 1;

        inserirConexao(c1, destino, distancia);
        inserirConexao(c2, origem, distancia);
    }

    void inserirConexao(NoCreche creche, String destino, double distancia) {
        NoConexao atual = creche.primeiraConexao;
        while (atual != null) {
            if (atual.destino.equals(destino)) {
                atual.distancia = distancia;
                return;
            }
            atual = atual.proxima;
        }

        NoConexao nova = new NoConexao(destino, distancia);
        nova.proxima = creche.primeiraConexao;
        creche.primeiraConexao = nova;
    }

    void mostrarQuantidadeConexoes() {
        if (primeiraCreche == null) {
            System.out.println("Nenhuma creche cadastrada.");
            return;
        }

        NoCreche atual = primeiraCreche;
        while (atual != null) {
            int quantidade = 0;
            for (int j = 0; j < totalCreches; j++) {
                if (matrizAdjacencia[atual.indice][j] == 1) {
                    quantidade++;
                }
            }
            System.out.println(atual.nome + ": " + quantidade + " conexao(oes)");
            atual = atual.proxima;
        }
    }

    void listarConexoes(String nome) {
        NoCreche creche = buscarCreche(nome);
        if (creche == null) {
            System.out.println("Creche nao encontrada: " + nome);
            return;
        }

        int quantidade = 0;
        NoConexao atual = creche.primeiraConexao;
        while (atual != null) {
            quantidade++;
            atual = atual.proxima;
        }

        if (quantidade == 0) {
            System.out.println("A creche " + nome + " nao possui conexoes.");
            return;
        }

        String[] nomes = new String[quantidade];
        double[] distancias = new double[quantidade];

        atual = creche.primeiraConexao;
        int i = 0;
        while (atual != null) {
            nomes[i] = atual.destino;
            distancias[i] = atual.distancia;
            i++;
            atual = atual.proxima;
        }

        for (int a = 0; a < quantidade - 1; a++) {
            int menor = a;
            for (int b = a + 1; b < quantidade; b++) {
                if (distancias[b] < distancias[menor]) {
                    menor = b;
                }
            }
            if (menor != a) {
                double tmpDist = distancias[a];
                distancias[a] = distancias[menor];
                distancias[menor] = tmpDist;

                String tmpNome = nomes[a];
                nomes[a] = nomes[menor];
                nomes[menor] = tmpNome;
            }
        }

        System.out.println("Conexoes da creche " + nome + ":");
        for (int k = 0; k < quantidade; k++) {
            System.out.println("  " + nomes[k] + " - " + distancias[k] + " km");
        }
    }

    void consultarDistancia(String origem, String destino) {
        NoCreche creche = buscarCreche(origem);
        if (creche == null) {
            System.out.println("Creche de origem nao encontrada: " + origem);
            return;
        }

        NoConexao atual = creche.primeiraConexao;
        while (atual != null) {
            if (atual.destino.equals(destino)) {
                System.out.println("Distancia entre " + origem + " e " + destino
                        + ": " + atual.distancia + " km");
                return;
            }
            atual = atual.proxima;
        }

        System.out.println("Nao ha conexao direta entre " + origem + " e " + destino + ".");
    }
}
