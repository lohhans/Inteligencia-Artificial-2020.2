/**
 * Algoritmo de Busca A* para resolução da 3ª questão da prova Final de IA
 * BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO — UNIVERSIDADE FEDERAL DO AGRESTE DE PERNAMBUCO / INSTÂNCIA 2020.2
 *
 * @author  Armstrong Lohãns de Melo Gomes Quintino
 * @version 1.0
 * @since   2021-12-17
 */

package com.armstrong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
    // Id para fins de legibilidade de resultados
    private static int idCounter = 0;
    public int id;

    // Pai no caminho
    public Node parent = null;

    public List<Edge> neighbors;

    // Funções de avaliação
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;

    // Heurística codificada
    public double h;

    Node(double h){
        this.h = h;
        this.id = idCounter++;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {
        Edge(int weight, Node node){
            this.weight = weight;
            this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    // Calcula a Heuristica pro nó especificado
    public double calculateHeuristic(Node target){
        return this.h;
    }

    // Código A*
    public static Node aStar(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristic(target);
        openList.add(start);

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    // Printa o Caminho da saída do A*
    public static void printPath(Node target){
        Node n = target;

        if(n==null)
            return;

        List<Integer> ids = new ArrayList<>();

        while(n.parent != null){
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for(int id : ids){
            System.out.print(id + " ");
        }
        System.out.println("");
    }
}