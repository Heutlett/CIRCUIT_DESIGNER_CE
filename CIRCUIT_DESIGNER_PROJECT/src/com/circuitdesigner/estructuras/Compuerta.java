package com.circuitdesigner.estructuras;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Compuerta {
	
	private char[] caracteres = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y'};
	
	public static enum tipoCompuerta{
		AND, NAND, OR, NOR, NOT, XOR, XNOR,ENTRADA,SALIDA
	};

	private static int cantProposiciones;
	private static int cantCompuertas;
	private boolean valorEntrada;
	private String idCompuerta;
	private String idProposicion;
	private char caracterProposicion;
	
	private ArrayList<Compuerta> entradas;
	private Compuerta salida;
	private tipoCompuerta tipo;
	private JLabel labelCompuerta;
	
	//Constructor para entradas y salidas
	public Compuerta(boolean valorEntrada, tipoCompuerta tipo) {
		
		this.caracterProposicion = caracteres[cantProposiciones];
		this.idProposicion = "P" + this.cantProposiciones;
		cantProposiciones++;
		this.valorEntrada = valorEntrada;
		this.tipo = tipo;
		this.labelCompuerta = new JLabel();
		this.labelCompuerta.setText(""+caracterProposicion);
		this.labelCompuerta.setSize(20, 20);
		
	}
	
	public Compuerta(tipoCompuerta tipo, JLabel labelCompuerta) {
		
		this.idCompuerta = "C" + this.cantCompuertas;
		cantCompuertas++;
		this.tipo = tipo;
		this.labelCompuerta = labelCompuerta;
		entradas = new ArrayList<Compuerta>();
		if(tipo != tipoCompuerta.NOT) {
			entradas.add(new Compuerta(true, tipoCompuerta.ENTRADA));
			entradas.add(new Compuerta(true,tipoCompuerta.ENTRADA));
		}else {
			entradas.add(new Compuerta(true,tipoCompuerta.ENTRADA));
		}
		salida = new Compuerta(true, tipoCompuerta.SALIDA);
	}

	public ArrayList<Compuerta> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Compuerta> entradas) {
		this.entradas = entradas;
	}

	public Compuerta getSalida() {
		return salida;
	}

	public void setSalida(Compuerta salida) {
		this.salida = salida;
	}

	public tipoCompuerta getTipo() {
		return tipo;
	}

	public void setTipo(tipoCompuerta tipo) {
		this.tipo = tipo;
	}

	public JLabel getLabelCompuerta() {
		return labelCompuerta;
	}

	public void setLabelCompuerta(JLabel labelCompuerta) {
		this.labelCompuerta = labelCompuerta;
	}

	public char[] getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(char[] caracteres) {
		this.caracteres = caracteres;
	}

	public static int getCantProposiciones() {
		return cantProposiciones;
	}

	public static void setCantProposiciones(int cantProposiciones) {
		Compuerta.cantProposiciones = cantProposiciones;
	}

	public boolean isValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(boolean valorEntrada) {
		this.valorEntrada = valorEntrada;
	}


	public static int getCantCompuertas() {
		return cantCompuertas;
	}

	public static void setCantCompuertas(int cantCompuertas) {
		Compuerta.cantCompuertas = cantCompuertas;
	}

	public String getIdCompuerta() {
		return idCompuerta;
	}

	public void setIdCompuerta(String idCompuerta) {
		this.idCompuerta = idCompuerta;
	}

	public String getIdProposicion() {
		return idProposicion;
	}

	public void setIdProposicion(String idProposicion) {
		this.idProposicion = idProposicion;
	}

	public char getCaracterProposicion() {
		return caracterProposicion;
	}

	public void setCaracterProposicion(char caracterProposicion) {
		this.caracterProposicion = caracterProposicion;
	}
	

}
