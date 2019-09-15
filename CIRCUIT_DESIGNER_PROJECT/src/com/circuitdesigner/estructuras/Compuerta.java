package com.circuitdesigner.estructuras;

import java.util.ArrayList;

public class Compuerta {
	
	protected ArrayList<Compuerta> entradas;
	protected Compuerta salida;
	protected int x;
	protected int y;
	
	public Compuerta(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

}
