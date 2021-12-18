/**
 * Algoritmo de Busca em Profundidade para resolução da 5ª questão da prova Final de IA
 * BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO — UNIVERSIDADE FEDERAL DO AGRESTE DE PERNAMBUCO / INSTÂNCIA 2020.2
 *
 * @author  Armstrong Lohãns de Melo Gomes Quintino
 * @version 1.0
 * @since   2021-12-17
 */

package com.armstrong;

import java.util.*;

class Main {
    public static void main(String[] args) {

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");
        Node j = new Node("J");
        Node k = new Node("K");
        Node l = new Node("L");
        Node m = new Node("M");
        Node n = new Node("N");
        Node o = new Node("O");
        Node p = new Node("P");
        Node q = new Node("Q");
        Node r = new Node("R");

        a.addEdge(b,73); b.addEdge(a,73);
        a.addEdge(c,64); c.addEdge(a,64);
        a.addEdge(d,89); d.addEdge(a,89);
        a.addEdge(e,104); e.addEdge(a,104);
        
        b.addEdge(k,83); k.addEdge(b,83);
        
        c.addEdge(i, 64); i.addEdge(c,64);

        d.addEdge(n, 89); n.addEdge(d,89);

        e.addEdge(j, 40); j.addEdge(e,40);

        f.addEdge(i, 31); i.addEdge(f,31);

        f.addEdge(n, 84); n.addEdge(f,84);

        g.addEdge(j, 35); j.addEdge(g, 35);
        g.addEdge(q, 113); q.addEdge(g, 113);

        h.addEdge(k, 35); k.addEdge(h, 35);
        h.addEdge(l, 36); l.addEdge(h, 36);

        i.addEdge(l, 28); l.addEdge(i, 28);
        i.addEdge(m, 20); m.addEdge(i, 20);

        j.addEdge(n, 53); n.addEdge(j, 53);
        j.addEdge(q, 80); q.addEdge(j, 80);

        l.addEdge(p, 63); p.addEdge(l, 63);

        m.addEdge(o, 50); o.addEdge(m, 50);

        o.addEdge(p, 41); p.addEdge(o, 41);
        o.addEdge(r, 72); r.addEdge(o, 72);

        p.addEdge(r, 65); r.addEdge(p, 65);

        q.addEdge(r, 65); r.addEdge(q, 65);

        System.out.println("** Busca em Profundidade de forma Iterativa no Grafo **\n");
        dfsIterative(a);
        System.out.println();

        // System.out.println("** Busca em Profundidade de forma Recursiva no Grafo **\n");
        // dfsRecursive(a, new HashSet<String>());

    }

    public static void dfsIterative(Node startNode) {
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        Stack<Node> searchTreeIterative = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (!visited.contains(currentNode.name)) {
//                System.out.println("Nó: " + currentNode.name + " | Peso: " + (currentNode.weight == null ? "Não avaliado" : currentNode.weight));
                visited.add(currentNode.name);
                searchTreeIterative.push(currentNode);
            }

            for (Node n : currentNode.neighbors) {
                if (!visited.contains(n.name)) {
                    stack.push(n);
                }
            }
        }

        System.out.println("5-A) Árvore de busca que é produzida: ");
        System.out.println("R: A Árvore de busca produzida pelo algoritmo para fazer uma Busca em Profundidade no Grafo inteiro: ");

        for(Node node : searchTreeIterative){
            System.out.println("Nó: " + node.name + " | Peso: " + (node.weight == null ? "Não avaliado" : node.weight));
        }
        System.out.println();

        System.out.println("5-B) Defina a ordem em que os nós serão expandidos: ");
        System.out.println("R: Ordem em que os Nós são expandidos do Ponto A até o Ponto R: ");

        Integer counterB = 0;
        for(Node node : searchTreeIterative){
            counterB++;
            if(node.name.equalsIgnoreCase("R")){
                System.out.println(counterB + "º Nó: " + node.name + " | Nó objetivo");
                break;
            } else {
                System.out.println(counterB + "º Nó: " + node.name + " | Peso: " + (node.weight == null ? "Não avaliado" : node.weight));
            }
        }
        System.out.println();

        System.out.println("5-C) Defina a rota que será tomada e o custo total: ");
        System.out.print("R: A rota do Ponto A até o Ponto R: ");

        Integer custoC = 0;
        List<Integer> valoresPercorridos = new ArrayList<>();

        for(Node node : searchTreeIterative){
            if(node.name.equalsIgnoreCase("R")){
                System.out.print(node.name);
                break;
            } else {
                System.out.print(node.name + " (peso: " + (node.weight == null ? "Não avaliado" : node.weight) + ")");
                custoC += node.weight;
                valoresPercorridos.add(node.weight);
                System.out.print(" -> ");
            }
        }
        System.out.println();

        System.out.print("R: O custo do Ponto A até o Ponto R: ");
        Integer counterC = 0;
        for(Integer numero : valoresPercorridos){
            counterC++;

            if(counterC != valoresPercorridos.size()){
                System.out.print(numero + " + ");
            } else {
                System.out.print(numero);
            }

        }
        System.out.print(" = " + custoC);

        System.out.println();
    }

    public static void dfsRecursive(Node startNode, HashSet<String> visited) {
        System.out.println("Nó: " + startNode.name + " | Peso: " + (startNode.weight == null ? "Não avaliado" : startNode.weight));
        visited.add(startNode.name);

        for (Node n : startNode.neighbors) {
            if (!visited.contains(n.name)) {
                dfsRecursive(n, visited);
            }
        }
    }
}
