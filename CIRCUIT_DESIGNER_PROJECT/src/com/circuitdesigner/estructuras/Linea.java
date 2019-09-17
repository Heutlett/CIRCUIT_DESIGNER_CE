package com.circuitdesigner.estructuras;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Linea {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Compuerta cola;
	private Compuerta pico;
	private Color color;
	private JLabel label;
	
	public Linea(int x1, int y1, int x2, int y2, Color color, JLabel label, Compuerta cola, Compuerta pico) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.label = label;
		this.label.setForeground(Color.red);
		this.label.setBounds(x1,y1-10,30,30);
		this.color = color;
		this.cola = cola;
		this.pico = pico;
	}

	
	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
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

	public Compuerta getCola() {
		return cola;
	}

	public void setCola(Compuerta cola) {
		this.cola = cola;
	}

	public Compuerta getPico() {
		return pico;
	}

	public void setPico(Compuerta pico) {
		this.pico = pico;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	

}
