package com.circuitdesigner.estructuras;

import java.util.ArrayList;

public class Sistema {
	
	private static final Sistema INSTANCE = new Sistema();
	
	private ArrayList<Compuerta> listaCompuertas;
	private ArrayList<Linea> listaLineas;
	

	private Sistema() {
		
		listaCompuertas = new ArrayList<Compuerta>();
		listaLineas = new ArrayList<Linea>();
		
	}
	
    public static Sistema getInstance() {
        return INSTANCE;
    }
    
	public void addCompuertas(Compuerta c) {
		listaCompuertas.add(c);
	}
	
	public static void imprimirTabla(int n, int [][]tablaFinal) {
		
		for(int  i =0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				System.out.print(tablaFinal[i][e] + " ");
			}
			System.out.println();
		}
		
	}
	
	public static int[][] generarTabla(int n) {
		
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
	
	public ArrayList<Compuerta> getListaCompuertas() {
		return listaCompuertas;
	}

	public void setListaCompuertas(ArrayList<Compuerta> listaCompuertas) {
		this.listaCompuertas = listaCompuertas;
	}

	public ArrayList<Linea> getListaLineas() {
		return listaLineas;
	}

	public void setListaLineas(ArrayList<Linea> listaLineas) {
		this.listaLineas = listaLineas;
	}


}
