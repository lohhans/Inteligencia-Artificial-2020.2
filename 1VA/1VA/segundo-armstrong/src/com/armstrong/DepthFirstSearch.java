// ARMSTRONG LOHÃNS - IA - BCC - UFAPE - 1ª VA - 29/10/2021

package com.armstrong;

public class DepthFirstSearch {
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addVertex('A'); //0
		graph.addVertex('B'); //1
		graph.addVertex('C'); //2
		graph.addVertex('D'); //3
		graph.addVertex('E'); //4
		graph.addVertex('F'); //5
		graph.addVertex('G'); //6
		graph.addVertex('H'); //7
		graph.addVertex('I'); //8
		graph.addVertex('J'); //9
		graph.addVertex('K'); //10

		graph.addEdge(0,1);						//AB
		graph.addEdge(0,4);						//AE
		graph.addEdge(0,7);						//AH
		graph.addEdge(0,8);						//AI

		graph.addEdge(1,0);						//BA
		graph.addEdge(1,2);						//BC

		graph.addEdge(2,1);						//CB
		graph.addEdge(2,3);						//CD
		graph.addEdge(2,4);						//CE
		graph.addEdge(2,5);						//CF

		graph.addEdge(3,2);						//DC
		graph.addEdge(3,5);						//DF
		graph.addEdge(3,6);						//DG

		graph.addEdge(4,0);						//EA
		graph.addEdge(4,2);						//EC
		graph.addEdge(4,7);						//EH
		graph.addEdge(4,9);						//EJ

		graph.addEdge(5,2);						//FC
		graph.addEdge(5,3);						//FD
		graph.addEdge(5,6);						//FG

		graph.addEdge(6,3);						//GD
		graph.addEdge(6,5);						//GF

		graph.addEdge(7,0);						//HA
		graph.addEdge(7,4);						//HE
		graph.addEdge(7,8);						//HI
		graph.addEdge(7,9);						//HJ
		graph.addEdge(7,10);					//HK

		graph.addEdge(8,0);						//IA
		graph.addEdge(8,7);						//IH
		graph.addEdge(8,10);					//IK

		graph.addEdge(9,4);						//JE
		graph.addEdge(9,7);						//JH

		graph.addEdge(10,7);					//KH
		graph.addEdge(10,8);					//KI

		System.out.println("O caminho percorrido para sair do ponto A aou ponto G é: ");

		// Para no Nó 6 == letra G
		graph.depthFirstSearchNode(6);
	}
}