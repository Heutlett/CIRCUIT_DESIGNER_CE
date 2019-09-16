package com.circuitdesigner.main;

import com.circuitdesigner.view.Window;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window w = new Window();
		w.setVisible(true);
		calcularAnd();
	}
	
	private static void calcularAnd() {
		
		int n = 3;
		
		int numero = 1;
		
		int [][] tabla = new int[n][(int) Math.pow(2, n)];
		
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
		
		int cantTrue = 0;
		int cantFalse = 0;
		
		for(int i = 0; i <  (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				
				System.out.print(tabla[e][i] + " ");
				
			}
			
			System.out.println();
			
		}
		
	}

}
