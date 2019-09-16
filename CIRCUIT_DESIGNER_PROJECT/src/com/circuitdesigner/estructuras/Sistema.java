package com.circuitdesigner.estructuras;

import java.util.ArrayList;

public class Sistema {
	
	private ArrayList<Compuerta> listaCompuertas;
	
	public Sistema() {
		
		listaCompuertas = new ArrayList<Compuerta>();
		
	}
	
	public void addCompuertas(Compuerta c) {
		listaCompuertas.add(c);
	}
	
	private static void imprimirTabla(int n, int [][]tablaFinal) {
		
		for(int  i =0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				System.out.print(tablaFinal[i][e] + " ");
			}
			System.out.println();
		}
		
	}
	
	private static int[][] generarTabla(int n) {
		
		int numero = 1;
		int [][] tabla = new int[n][(int) Math.pow(2, n)];
		int [][] tablaFinal = new int[(int) Math.pow(2, n)][n];
		
		for(int i = 0; i < n; i++) {
			
			int contador = 0;
			
			for(int e = 0; e < (int) Math.pow(2, n); e++) {
				
				int trueOrFalse = (int) Math.pow(2, n-i)/2;
				
				if(contador == trueOrFalse) {
					if(numero == 1) {
						numero = 0;
					}else {
						numero = 1;
					}
					contador = 0;
				}
				contador++;
				tabla[i][e] = numero;
			}
		}
		
		for(int i = 0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				tablaFinal[i][e] = tabla[e][i];
			}
			
		}

		return tablaFinal;
	}

}
