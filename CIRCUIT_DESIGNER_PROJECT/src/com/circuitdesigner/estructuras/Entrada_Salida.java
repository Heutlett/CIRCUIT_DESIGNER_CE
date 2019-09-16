package com.circuitdesigner.estructuras;

import javax.swing.JLabel;

public class Entrada_Salida extends Compuerta{

	private char[] caracteres = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y'};
	private static int cant;
	private boolean valor;
	private tipoCompuerta tipoCompuerta;
	private int id;
	public char caracter;

	public Entrada_Salida(boolean valor, tipoCompuerta tipoCompuerta) {
		super(tipoCompuerta, new JLabel());
		this.tipoCompuerta = tipoCompuerta;
		id = cant;
		this.caracter = caracteres[cant];
		cant++;
		this.valor = valor;
	}
	
	public tipoCompuerta getTipoCompuerta() {
		return tipoCompuerta;
	}

	public void setTipoCompuerta(tipoCompuerta tipoCompuerta) {
		this.tipoCompuerta = tipoCompuerta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getCaracter() {
		return caracter;
	}

	public void setCaracter(char caracter) {
		this.caracter = caracter;
	}

	public char[] getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(char[] caracteres) {
		this.caracteres = caracteres;
	}

	public static int getCant() {
		return cant;
	}

	public static void setCant(int cant) {
		Entrada_Salida.cant = cant;
	}

	public boolean isValor() {
		return valor;
	}

	public void setValor(boolean valor) {
		this.valor = valor;
	}
	
	
	
}
