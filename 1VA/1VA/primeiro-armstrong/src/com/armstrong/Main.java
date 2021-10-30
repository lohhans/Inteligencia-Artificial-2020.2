// ARMSTRONG LOHÃNS - IA - BCC - UFAPE - 1ª VA - 29/10/2021

package com.armstrong;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("1 - Faça um programa em Java ou JavaScript que dadas as coordenadas \n" +
                "de dois pontos no plano cartesiano calcula a distância entre esses dois pontos.\n");


        System.out.println("Qual a coordenada X do primeiro ponto?");
        Scanner keyboard = new Scanner(System.in);
        int x1 = keyboard.nextInt();

        System.out.println("Qual a coordenada Y do primeiro ponto?");
        Scanner keyboard1 = new Scanner(System.in);
        int y1 = keyboard1.nextInt();

        System.out.println("\n");

        System.out.println("Qual a coordenada X do segundo ponto?");
        Scanner keyboard2 = new Scanner(System.in);
        int x2 = keyboard2.nextInt();

        System.out.println("Qual a coordenada Y do segundo ponto?");
        Scanner keyboard3 = new Scanner(System.in);
        int y2 = keyboard3.nextInt();

        // Fórmula de distância entre dois pontos
        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

        System.out.println("\n");

        System.out.println("A distância entre os dois pontos é " + distance);

    }
}
