package com.circuitdesigner.estructuras;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Compuerta {
	
	public static enum tipoCompuerta{
		AND, NAND, OR, NOR, NOT, XOR, XNOR, ENTRADA, SALIDA
	};

	private static int cantProposicionesIn = 1;
	private static int cantProposicionesOut = 1;
	private static int cantCompuertas;
	private int valorEntrada;
	private String idCompuerta ="";
	private String idProposicion ="";
	private boolean bloqueada = false;
	private ArrayList<Compuerta> entradas;
	private Compuerta salida;
	private tipoCompuerta tipo;
	private JLabel labelCompuerta;
	private JLabel labelId;
	
	//Constructor para entradas y salidas
	public Compuerta(int valorEntrada, tipoCompuerta tipo) {
		
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
		this.labelCompuerta.setSize(40, 20);
		this.labelCompuerta.setVisible(false);
		this.labelCompuerta.setName(idProposicion);
		
	}
	
	public Compuerta(tipoCompuerta tipo, JLabel labelCompuerta) {
		
		
		this.idCompuerta = "C" + this.cantCompuertas;
		this.labelId = new JLabel(this.idCompuerta);
		this.labelId.setSize(40,20);
		this.labelId.setVisible(false);
		cantCompuertas++;
		this.tipo = tipo;
		this.labelCompuerta = labelCompuerta;
		entradas = new ArrayList<Compuerta>();
		if(tipo != tipoCompuerta.NOT) {
			entradas.add(new Compuerta(1, tipoCompuerta.ENTRADA));
			entradas.add(new Compuerta(1,tipoCompuerta.ENTRADA));
		}else {
			entradas.add(new Compuerta(1,tipoCompuerta.ENTRADA));
		}
		salida = new Compuerta(1, tipoCompuerta.SALIDA);
		this.labelCompuerta.setName(idCompuerta);
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
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

	public int getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(int valorEntrada) {
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

	public JLabel getLabelId() {
		return labelId;
	}

	public void setLabelId(JLabel labelId) {
		this.labelId = labelId;
	}

	public static void imprimirDatosCompuertas(Compuerta newCompuerta) {
		System.out.println();
		System.out.println("Mostrando los datos de la compuerta: " + newCompuerta.getIdCompuerta());
		for(int i = 0; i < newCompuerta.getEntradas().size(); i++) {
			if(newCompuerta.getEntradas().get(i).getTipo() == Compuerta.tipoCompuerta.ENTRADA || newCompuerta.getEntradas().get(i).getTipo() == Compuerta.tipoCompuerta.SALIDA) {
				System.out.println("Entrada " + i + ": " + newCompuerta.getEntradas().get(i).getIdProposicion());
			}else {
				System.out.println("Entrada " + i + ": " + newCompuerta.getEntradas().get(i).getIdCompuerta());
			}
			
		}
		if(newCompuerta.getSalida().getTipo() == Compuerta.tipoCompuerta.ENTRADA || newCompuerta.getSalida().getTipo() == Compuerta.tipoCompuerta.SALIDA) {
			
			System.out.println("Salida: " + newCompuerta.getSalida().getIdProposicion());
			
		}else {
			System.out.println("Salida: " + newCompuerta.getSalida().getIdCompuerta());
		}
		System.out.println();
	}
	
	public void ubicarEntradasYSalidas() {
		
		int y = this.getLabelCompuerta().getY() - 20;
		int x = this.getLabelCompuerta().getX();
		
		this.getSalida().getLabelCompuerta().setLocation(x+90,y+20);
		this.getLabelId().setLocation(x+40, y-20);
		for(int i = 0; i < this.getEntradas().size(); i++) {
			
			this.getEntradas().get(i).getLabelCompuerta().setLocation(x,y);
			y += 30;
			
		}
		
	}
	
	

}
