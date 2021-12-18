/**
 * Algoritmo de Busca em Profundidade para resolução da 5ª questão da prova Final de IA
 * BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO — UNIVERSIDADE FEDERAL DO AGRESTE DE PERNAMBUCO / INSTÂNCIA 2020.2
 *
 * @author  Armstrong Lohãns de Melo Gomes Quintino
 * @version 1.0
 * @since   2021-12-17
 */

package com.armstrong;

import java.util.ArrayList;
import java.util.List;

public class Node {

    String name;
    Integer weight;
    List<Node> neighbors;

    public Node(String name) {
        this.name = name;
        neighbors = new ArrayList<>();
    }

    public void addEdge(Node node, Integer weight) {
        neighbors.add(node);
        this.weight = weight;
    }
}
