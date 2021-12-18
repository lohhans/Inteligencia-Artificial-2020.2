/**
 * Algoritmo de Busca A* para resolução da 3ª questão da prova Final de IA
 * BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO — UNIVERSIDADE FEDERAL DO AGRESTE DE PERNAMBUCO / INSTÂNCIA 2020.2
 *
 * @author  Armstrong Lohãns de Melo Gomes Quintino
 * @version 1.0
 * @since   2021-12-17
 */

package com.armstrong;

import static com.armstrong.Node.aStar;
import static com.armstrong.Node.printPath;

public class Main {

    public static void main(String[] args) {
        Node head = new Node(3);
        head.g = 0;

        Node n1 = new Node(2);
        Node n2 = new Node(2);
        Node n3 = new Node(2);

        head.addBranch(1, n1);
        head.addBranch(5, n2);
        head.addBranch(2, n3);
        n3.addBranch(1, n2);

        Node n4 = new Node(1);
        Node n5 = new Node(1);
        Node target = new Node(0);

        n1.addBranch(7, n4);
        n2.addBranch(4, n5);
        n3.addBranch(6, n4);

        n4.addBranch(3, target);
        n5.addBranch(1, n4);
        n5.addBranch(3, target);

        Node res = aStar(head, target);

        System.out.print("Busca A* - Caminho obtido pro Grafo: ");
        printPath(res);
    }
}
