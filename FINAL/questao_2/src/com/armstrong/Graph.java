/**
 * Algoritmo de Busca em Largura para resolução da 2ª questão da prova Final de IA
 * BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO — UNIVERSIDADE FEDERAL DO AGRESTE DE PERNAMBUCO / INSTÂNCIA 2020.2
 *
 * @author  Armstrong Lohãns de Melo Gomes Quintino
 * @version 1.0
 * @since   2021-12-17
 */

package com.armstrong;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {
	private final int MAX_VERTEX = 11;
	private Vertex[] vertexs;
	private Stack<Integer> stack;
	private Queue<Integer> queue;

	private int[][] adjacencyMatrix;
	private int currentAmountOfVerxtes;

	public Graph(){
		vertexs = new Vertex[MAX_VERTEX];
		stack = new Stack<>();										//pilha usada na busca em profundidade
		queue = new LinkedList<>();									//fila usada na busca em largura
		adjacencyMatrix = new int[MAX_VERTEX][MAX_VERTEX];
		currentAmountOfVerxtes = 0;

		//inicialização da matriz de adjacências com 0s
		for(int i=0; i<adjacencyMatrix.length; i++){
			for(int j=0; j<adjacencyMatrix[i].length; j++){
				adjacencyMatrix[i][j] = 0;
			}
		}
	}

	public void addVertex(char label){
//		System.out.println("antes -> " + currentAmountOfVerxtes);
		vertexs[currentAmountOfVerxtes++] = new Vertex(label);
//		System.out.println("dps -> " + currentAmountOfVerxtes);
	}

	public void addEdge(int start, int end){
		adjacencyMatrix[start][end] = 1;
		adjacencyMatrix[end][start] = 1;
	}
    
    public void addOrientedEdge(int start, int end){
        adjacencyMatrix[start][end] = 1;
    }

	public void displayVertex(int vertexIndex){
		System.out.print(vertexs[vertexIndex].getLabel());
	}

	public void depthFirstSearch(){									//método que executa a busca em profundidade
		vertexs[0].setWasVisited(true);								//inicia pelo ponto de partida e já marca ele como visitado
		this.displayVertex(0);										//exibe o nó
		stack.push(0);												//empilha o nó
		
		while (!stack.empty()) {									//enquanto houverem nós na pilha a busca não terminou
			int vertexIndex = this.getAdjUnvisitedVertex(stack.peek());		//recupera o próximo nó adjacente não visitado
			if(vertexIndex==-1){											//não existe ? Remove um nó da pilha
				stack.pop();
			} else {														//existe
				vertexs[vertexIndex].setWasVisited(true);					//executa os passos da regra 1: visite, marque como visitado e empilhe
				this.displayVertex(vertexIndex);
				stack.push(vertexIndex);
			}
		}

		System.out.println("\n\n" + vertexs.length);

		for (int i=0; i<currentAmountOfVerxtes; i++) {						//Se chegou aqui é porque não haviam mais nós na pilha
			vertexs[i].setWasVisited(false);								//Reseta todos os nós novamente para que a busca possa ser executada depois
		}
	}

	public void depthFirstSearchNode(int node){									//método que executa a busca em profundidade
		vertexs[0].setWasVisited(true);								//inicia pelo ponto de partida e já marca ele como visitado
		this.displayVertex(0);										//exibe o nó
		stack.push(0);												//empilha o nó

		int nodeVIndex = 0; // Nó para parada

		while (!stack.empty() && nodeVIndex != node) {									//enquanto houverem nós na pilha a busca não terminou
			int vertexIndex = this.getAdjUnvisitedVertex(stack.peek());		//recupera o próximo nó adjacente não visitado
			nodeVIndex = vertexIndex; // Nó atualizado para parada
			if(vertexIndex==-1){											//não existe ? Remove um nó da pilha
				stack.pop();
			} else {														//existe
				vertexs[vertexIndex].setWasVisited(true);					//executa os passos da regra 1: visite, marque como visitado e empilhe
				this.displayVertex(vertexIndex);
				stack.push(vertexIndex);
			}
		}

		System.out.println("\n\n" + vertexs.length);

		for (int i=0; i<currentAmountOfVerxtes; i++) {						//Se chegou aqui é porque não haviam mais nós na pilha
			vertexs[i].setWasVisited(false);								//Reseta todos os nós novamente para que a busca possa ser executada depois
		}
	}

	private int getAdjUnvisitedVertex(int vertexIndex) {									//método que recupera o próximo nó adjacente não visitado
		for (int i=0; i<currentAmountOfVerxtes; i++) {										//para a quantidade atual de nós
			if (adjacencyMatrix[vertexIndex][i] == 1 && !vertexs[i].getWasVisited()) {		//utiliza a matriz de adjacências para determinar se para um nó existem nós adjacentes a ele
				return i;																	//caso exista, retorna a posição deste nó no array de nós
			}
		}
		return -1;																			//caso não exista, retorna -1
	}

	public void breadthFirstSearch(){									//método que executa a busca em largura
		vertexs[0].setWasVisited(true);									//inicia pelo ponto de partida e já marca ele como visitado  - Regra 1
		this.displayVertex(0);											//exibe o nó
		queue.add(0);													//adiciona ele na fila  - Regra 1
		int adjacentVertex;

		while(!queue.isEmpty()){																//enquanto houverem nós na fila é porque não chegamos ao fim
			int currentVertex = queue.remove();													//removemos o primeiro item da fila para analisar suas adjacências - Regra 2
			while ((adjacentVertex=this.getAdjUnvisitedVertex(currentVertex)) != -1) {			//para o nó recém removido da fila ele possui adjacências não visitadas ?
				vertexs[adjacentVertex].setWasVisited(true);									//para todas as adjacências não visitadas é mudado o flag de visitação - Regra 1
				this.displayVertex(adjacentVertex);												
				queue.add(adjacentVertex);														//enfileirado  - Regra 1
			}
		}

		for (int i=0; i<currentAmountOfVerxtes; i++) {											//Se chegou a esse ponto, a fila está vazia - Regra 3
			vertexs[i].setWasVisited(false);													//Reseta todos os nós novamente para que a busca possa ser executada depois
		}
	}

	public void breadthFirstSearchNode(int node){									//método que executa a busca em largura
		vertexs[0].setWasVisited(true);									//inicia pelo ponto de partida e já marca ele como visitado  - Regra 1
		this.displayVertex(0);											//exibe o nó
		queue.add(0);													//adiciona ele na fila  - Regra 1
		int adjacentVertex;

		int nodeVIndex = 0; // Nó para parada

		while(!queue.isEmpty() && nodeVIndex != node){																//enquanto houverem nós na fila é porque não chegamos ao fim
			int currentVertex = queue.remove();													//removemos o primeiro item da fila para analisar suas adjacências - Regra 2
			nodeVIndex = currentVertex; // Nó atualizado para parada

			while ((adjacentVertex=this.getAdjUnvisitedVertex(currentVertex)) != -1) {			//para o nó recém removido da fila ele possui adjacências não visitadas ?
				vertexs[adjacentVertex].setWasVisited(true);									//para todas as adjacências não visitadas é mudado o flag de visitação - Regra 1
				this.displayVertex(adjacentVertex);
				queue.add(adjacentVertex);														//enfileirado  - Regra 1
			}
		}

		for (int i=0; i<currentAmountOfVerxtes; i++) {											//Se chegou a esse ponto, a fila está vazia - Regra 3
			vertexs[i].setWasVisited(false);													//Reseta todos os nós novamente para que a busca possa ser executada depois
		}
	}

	public void minimumSpanningTree(){
		vertexs[0].setWasVisited(true);															//Inicia pelo ponto de partida e já marca ele como visitado
		stack.push(0);																			//Empilha o nó

		while (!stack.empty()) {																//Enquanto a pilha não estiver vazia, analisamos os nós que estão nela
			int currentVertex = stack.peek();													//Recupero o nó do topo da pilha, não desempilho
			int nextVertex = this.getAdjUnvisitedVertex(currentVertex);							//Recupera o próximo nó adjacente não visitado
			if (nextVertex == -1) {																//Não existem nós adjacentes
				stack.pop();																	//Desempilho o nó do topo
			} else {
				vertexs[nextVertex].setWasVisited(true);										//Marco o nó adjacente como visitado
				stack.push(nextVertex);															//Empilho o nó adjacente
				this.displayVertex(currentVertex);												//Exibo o nó atual
				this.displayVertex(nextVertex);													//Exibo o nó adjacente ao atual
				System.out.print(" ");															//Espaço dado para melhorar a visualização das arestas
			}
		}

		for (int i=0; i<currentAmountOfVerxtes; i++) {											//Se chegou aqui é porque não haviam mais nós na pilha
			vertexs[i].setWasVisited(false);													//Reseta todos os nós novamente para que a árvore geradora mínima possa ser gerada novamente
		}
	}

	public void warshallAlgorithm(){													//Implementação do algoritmo de Warshall
		for (int i=0; i<adjacencyMatrix.length; i++) {									//Percorro todas as linhas
			for (int j=0; j<adjacencyMatrix[i].length; j++) {							//Percorro todas as colunas daquela linha
				if(adjacencyMatrix[i][j] == 1){											//Existe uma aresta ali ?
					for (int k=0; k<adjacencyMatrix[i].length; k++) {					//Percorro completamente a coluna do nó do grafo, exemplo: se estou na linha A, percorro completamente a coluna A.
						if(adjacencyMatrix[k][i]==1){									//Nesta coluna existe alguma aresta que me leve à ela ?
							adjacencyMatrix[k][j]=1;									//Determino que se existe A -> B e B -> C, logo A -> C.
						}
					}
				}
			}
		}	

		this.displayResultsWarshallAlgorithm();
	}

	private void displayResultsWarshallAlgorithm(){										//Exibe os caminhos possíveis em um grafo orientado após 
		for (int i=0; i<adjacencyMatrix.length; i++) {									//a execução do algoritmo de Warshall
			for (int j=0; j<adjacencyMatrix[i].length; j++) {
				if(adjacencyMatrix[i][j]==1){
					this.displayVertex(i);
					System.out.print(" -> ");
					this.displayVertex(j);
					System.out.println();
				}				
			}
		}		
	}

	public void checkPossibleWay(int start, int end){									//Verifica se um caminho é possível utilizando a matriz de adjacência. Este método
		System.out.print("E possivel um caminho de ");									//funciona da forma correta somente após a execução do algoritmo de Warshall.
		this.displayVertex(start);
		System.out.print(" para ");
		this.displayVertex(end);
		System.out.print(": ");
		
		String isPossible = adjacencyMatrix[start][end] == 1 ? "True" : "False";
		System.out.print(isPossible);
		System.out.println();
	}
}