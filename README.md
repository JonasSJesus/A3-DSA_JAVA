# Grafo de Creches — Documentação

## Visão Geral

Este programa representa um conjunto de **creches** e as **distâncias** entre
elas usando um **grafo**. Cada creche é um vértice e cada caminho entre duas
creches é uma aresta com um valor de distância (em km).

O objetivo é permitir consultas simples, como:

- quantas conexões cada creche possui;
- quais creches estão ligadas a uma creche específica;
- qual a distância entre duas creches;
- adicionar uma nova conexão durante a execução.

As conexões são lidas de um arquivo de texto (`creches.txt`).

---

## Estruturas Utilizadas

O trabalho usa **duas estruturas ao mesmo tempo**, como pedido.

### 1. Matriz de Adjacência

É uma tabela quadrada `int[MAX_CRECHES][MAX_CRECHES]` onde:

- `0` = **não** existe conexão;
- `1` = **existe** conexão.

Cada creche recebe um número (índice). Se a creche 0 está ligada à creche 2,
então `matriz[0][2] = 1` e `matriz[2][0] = 1` (o grafo é não direcionado).

A matriz é ótima para responder **rapidamente** se duas creches estão ligadas
e para **contar** as conexões de uma creche (basta contar os `1` de uma linha).

```
        Joana  Amaro  Ana  Pequeno  Tia
Joana     0      1     1      0      1
Amaro     1      0     1      1      0
Ana       1      1     0      1      1
Pequeno   0      1     1      0      1
Tia       1      0     1      1      0
```

### 2. Lista Simplesmente Encadeada

As creches ficam guardadas em uma **lista encadeada** (`NoCreche`), onde cada
nó aponta para o próximo. Cada creche tem **a sua própria lista encadeada de
conexões** (`NoConexao`), e cada conexão guarda o **nome do destino** e a
**distância**.

```
NoCreche: [Joana] -> [Amaro] -> [Ana] -> [Pequeno] -> [Tia] -> null
                |
                v  (lista de conexões da Joana)
            [Amaro, 2.9] -> [Ana, 5.7] -> [Tia, 7.5] -> null
```

A lista guarda a informação que a matriz não guarda: a **distância** de cada
conexão. Tudo é criado manualmente, sem `ArrayList`, `List`, `Map` etc.

---

## Funcionamento

### Como o TXT é carregado

O arquivo segue o formato `origem;destino;distancia`, uma conexão por linha:

```
JoanaTimoteo;AmaroCavalcante;2.9
JoanaTimoteo;AnaReinaldo;5.7
```

A classe `LeitorArquivo` lê linha por linha, separa os três campos com
`split(";")` e chama `adicionarConexao(origem, destino, distancia)`.

### Como as conexões são armazenadas

Ao adicionar uma conexão, o programa:

1. **cadastra** as creches (se ainda não existirem) na lista encadeada,
   atribuindo um índice a cada uma;
2. marca `1` na **matriz de adjacência** nos dois sentidos;
3. insere a **distância** na lista de conexões das duas creches.

Se a conexão já existir, apenas a distância é atualizada.

### Como as consultas funcionam

- **Quantidade de conexões:** percorre a linha da creche na matriz e conta
  os valores iguais a `1`.
- **Listar conexões:** percorre a lista de conexões da creche, copia os dados
  para vetores e ordena por distância crescente com **Selection Sort**.
- **Distância entre duas creches:** percorre a lista da creche de origem
  procurando o destino; se achar, mostra a distância, senão avisa que não há
  conexão direta.
- **Adicionar conexão:** reaproveita o mesmo `adicionarConexao` usado na
  leitura do arquivo, atualizando matriz e listas.


---

## Como Executar

```
javac *.java
java Main
```

O programa carrega `creches.txt` automaticamente e abre o menu de texto.
