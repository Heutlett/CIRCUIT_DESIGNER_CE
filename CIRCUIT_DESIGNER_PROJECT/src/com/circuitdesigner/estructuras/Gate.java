package com.circuitdesigner.estructuras;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Gate {
	
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
	private ArrayList<Gate> entradas;
	private Gate salida;
	private tipoCompuerta tipo;
	private JLabel labelCompuerta;
	private JLabel labelId;
	private String idCompuertaPadre;
	
	//Constructor para entradas y salidas
	public Gate(int valorEntrada, tipoCompuerta tipo, String idCompuertaPadre) {
		
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
		this.idCompuertaPadre = idCompuertaPadre;
		
	}
	
	public Gate(tipoCompuerta tipo, JLabel labelCompuerta) {
		
		this.idCompuerta = "C" + this.cantCompuertas;
		this.labelId = new JLabel(this.idCompuerta);
		this.labelId.setForeground(Color.blue);
		this.labelId.setSize(40,20);
		this.labelId.setVisible(false);
		cantCompuertas++;
		this.tipo = tipo;
		this.labelCompuerta = labelCompuerta;
		entradas = new ArrayList<Gate>();
		if(tipo != tipoCompuerta.NOT) {
			entradas.add(new Gate(1, tipoCompuerta.ENTRADA,idCompuertaPadre));
			entradas.add(new Gate(1,tipoCompuerta.ENTRADA,idCompuertaPadre));
		}else {
			entradas.add(new Gate(1,tipoCompuerta.ENTRADA,idCompuertaPadre));
		}
		salida = new Gate(1, tipoCompuerta.SALIDA,idCompuertaPadre);
		this.labelCompuerta.setName(idCompuerta);
	}
	
	public Gate buscarEntrada(String nombreEntrada) {
		Gate c = null;
		for(int j = 0; j < getEntradas().size(); j++) {
			if(getEntradas().get(j).getLabelCompuerta().getName().equals(nombreEntrada)) {
				return getEntradas().get(j);
			}
		}
		return c;
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	public ArrayList<Gate> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Gate> entradas) {
		this.entradas = entradas;
	}

	public Gate getSalida() {
		return salida;
	}

	public void setSalida(Gate salida) {
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
		Gate.cantProposicionesIn = cantProposicionesIn;
	}

	public static int getCantProposicionesOut() {
		return cantProposicionesOut;
	}

	public static void setCantProposicionesOut(int cantProposicionesOut) {
		Gate.cantProposicionesOut = cantProposicionesOut;
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
		Gate.cantCompuertas = cantCompuertas;
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

	public static void imprimirDatosCompuertas(Gate newCompuerta) {
		System.out.println();
		System.out.println("Mostrando los datos de la compuerta: " + newCompuerta.getIdCompuerta());
		for(int i = 0; i < newCompuerta.getEntradas().size(); i++) {
			if(newCompuerta.getEntradas().get(i).getTipo() == Gate.tipoCompuerta.ENTRADA || newCompuerta.getEntradas().get(i).getTipo() == Gate.tipoCompuerta.SALIDA) {
				System.out.println("Entrada " + i + ": " + newCompuerta.getEntradas().get(i).getIdProposicion());
			}else {
				System.out.println("Entrada " + i + ": " + newCompuerta.getEntradas().get(i).getIdCompuerta());
			}
			
		}
		if(newCompuerta.getSalida().getTipo() == Gate.tipoCompuerta.ENTRADA || newCompuerta.getSalida().getTipo() == Gate.tipoCompuerta.SALIDA) {
			
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
