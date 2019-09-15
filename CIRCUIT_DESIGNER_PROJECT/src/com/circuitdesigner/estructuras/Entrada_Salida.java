package com.circuitdesigner.estructuras;

public class Entrada_Salida extends Compuerta {

	public Entrada_Salida(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	enum Letras {
		  A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z
	} 
	
	private boolean valor;

	public boolean isValor() {
		return valor;
	}

	public void setValor(boolean valor) {
		this.valor = valor;
	}
	
	
}
