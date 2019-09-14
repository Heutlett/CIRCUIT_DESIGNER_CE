package com.circuitdesigner.estructuras;

public class Entrada_Salida extends Compuerta {

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
