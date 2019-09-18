package com.circuitdesigner.estructuras;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Linea {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Gate cola;
	private Gate pico;
	private Color color;
	private JLabel labelBorrarInicio;
	private JLabel labelBorrarFinal;
	
	public Linea(int x1, int y1, int x2, int y2, Color color, JLabel label1, JLabel label2, Gate cola, Gate pico) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.labelBorrarInicio = label1;
		this.labelBorrarInicio.setForeground(Color.red);
		this.labelBorrarInicio.setBounds(x1,y1-10,30,30);
		this.labelBorrarFinal = label2;
		this.labelBorrarFinal.setForeground(Color.red);
		this.labelBorrarFinal.setBounds(x2,y2-10,30,30);
		this.color = color;
		this.cola = cola;
		this.pico = pico;
	}

	
	public JLabel getLabelBorrarInicio() {
		return labelBorrarInicio;
	}


	public void setLabelBorrarInicio(JLabel labelBorrarInicio) {
		this.labelBorrarInicio = labelBorrarInicio;
	}


	public JLabel getLabelBorrarFinal() {
		return labelBorrarFinal;
	}


	public void setLabelBorrarFinal(JLabel labelBorrarFinal) {
		this.labelBorrarFinal = labelBorrarFinal;
	}


	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public Gate getCola() {
		return cola;
	}

	public void setCola(Gate cola) {
		this.cola = cola;
	}

	public Gate getPico() {
		return pico;
	}

	public void setPico(Gate pico) {
		this.pico = pico;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	

}
