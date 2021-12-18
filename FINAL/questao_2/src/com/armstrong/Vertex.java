/**
 * Algoritmo de Busca em Largura para resolução da 2ª questão da prova Final de IA
 * BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO — UNIVERSIDADE FEDERAL DO AGRESTE DE PERNAMBUCO / INSTÂNCIA 2020.2
 *
 * @author  Armstrong Lohãns de Melo Gomes Quintino
 * @version 1.0
 * @since   2021-12-17
 */

package com.armstrong;

public class Vertex {
	private char label;
	private boolean wasVisited;

	public Vertex(char label){
		this.label = label;
	}

	public char getLabel(){
		return this.label;
	}

	public void setLabel(char label){
		this.label = label;
	}

	public boolean getWasVisited(){
		return this.wasVisited;
	}

	public void setWasVisited(boolean wasVisited){
		this.wasVisited = wasVisited;
	}
}