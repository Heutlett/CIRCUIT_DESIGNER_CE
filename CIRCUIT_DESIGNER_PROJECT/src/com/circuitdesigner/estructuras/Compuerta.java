package com.circuitdesigner.estructuras;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Compuerta {
	
	public static enum tipoCompuerta{
		AND, NAND, OR, NOR, NOT, XOR, XNOR,ENTRADA,SALIDA
	};

	private static int cantProposicionesIn = 1;
	private static int cantProposicionesOut = 1;
	private static int cantCompuertas;
	private boolean valorEntrada;
	private String idCompuerta;
	private String idProposicion;
	
	private ArrayList<Compuerta> entradas;
	private Compuerta salida;
	private tipoCompuerta tipo;
	private JLabel labelCompuerta;
	
	//Constructor para entradas y salidas
	public Compuerta(boolean valorEntrada, tipoCompuerta tipo) {
		
		if(tipo == tipoCompuerta.SALIDA) {
			this.idProposicion = "o<" + cantProposicionesOut + ">";
			cantProposicionesOut += 1;
			//System.out.println("cantidad de outs: " + cantProposicionesOut);
		}
		if(tipo ==tipoCompuerta.ENTRADA) {
			this.idProposicion = "i<" + cantProposicionesIn + ">";
			cantProposicionesIn += 1;
			//System.out.println("cantidad de in: " + cantProposicionesIn);
		}
		
		this.valorEntrada = valorEntrada;
		this.tipo = tipo;
		this.labelCompuerta = new JLabel();
		this.labelCompuerta.setText(idProposicion);
		this.labelCompuerta.setSize(40, 40);
		this.labelCompuerta.setVisible(false);
		
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

	public static int getCantProposicionesIn() {
		return cantProposicionesIn;
	}

	public static void setCantProposicionesIn(int cantProposicionesIn) {
		Compuerta.cantProposicionesIn = cantProposicionesIn;
	}

	public static int getCantProposicionesOut() {
		return cantProposicionesOut;
	}

	public static void setCantProposicionesOut(int cantProposicionesOut) {
		Compuerta.cantProposicionesOut = cantProposicionesOut;
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

	

}
