package com.circuitdesigner.structures;

import java.awt.Color;
import javax.swing.JLabel;
/**
 * 
 * @author Adrian Araya Ramirez
 * 
 * @version 1.8
 *
 */
public class Line {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Gate tail;
	private Gate peak;
	private Color color;
	private JLabel tailDeleteLabel;
	private JLabel peakDeleteLabel;
	
	/**
	 * 
	 * Constructor de lineas
	 * 
	 * @param int
	 * @param int
	 * @param int
	 * @param int
	 * @param Color
	 * @param JLabel
	 * @param JLabel
	 * @param Gate
	 * @param Gate
	 */
	public Line(int x1, int y1, int x2, int y2, Color color, JLabel tailDeleteLabel, JLabel peakDeleteLabel, Gate tail, Gate peak) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.tailDeleteLabel = tailDeleteLabel;
		this.tailDeleteLabel.setForeground(Color.red);
		this.tailDeleteLabel.setBounds(x1,y1-10,30,30);
		this.peakDeleteLabel = peakDeleteLabel;
		this.peakDeleteLabel.setForeground(Color.red);
		this.peakDeleteLabel.setBounds(x2,y2-10,30,30);
		this.color = color;
		this.tail = tail;
		this.peak = peak;
	}

	/**
	 * 
	 * Devuelve el JLabel que se utiliza para borrar las lineas.
	 * 
	 * @return JLabel
	 */
	public JLabel getTailDeleteLabel() {
		return tailDeleteLabel;
	}

	/**
	 * 
	 * Asigna el JLabel que se utiliza para borrar las lineas.
	 * 
	 * @param JLabel
	 */
	public void setTailDeleteLabel(JLabel tailDeleteLabel) {
		this.tailDeleteLabel = tailDeleteLabel;
	}

	/**
	 * 
	 * Devuelve el JLabel que se utiliza para borrar las lineas.
	 * 
	 * @return JLabel
	 */
	public JLabel getPeakDeleteLabel() {
		return peakDeleteLabel;
	}

	/**
	 * 
	 * Asigna el JLabel que se utiliza para borrar las lineas.
	 * 
	 * @param JLabel
	 */
	public void setPeakDeleteLabel(JLabel peakDeleteLabel) {
		this.peakDeleteLabel = peakDeleteLabel;
	}

	/**
	 * 
	 * Devuelve la coordenada x1 de la linea.
	 * 
	 * @return int
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * 
	 * Asigna la coordenada x1 de la linea.
	 * 
	 * @param int
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}

	/**
	 * 
	 * Devuelve la coordenada y1 de la linea.
	 * 
	 * @return int
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * 
	 * Asigna la coordenada y1 de la linea.
	 * 
	 * @param int
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}

	/**
	 * 
	 * Devuelve la coordenada x2 de la linea.
	 * 
	 * @return int
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * 
	 * Asigna la coordenada x2 de la linea.
	 * 
	 * @param int
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * 
	 * Devuelve la coordenada y2 de la linea.
	 * 
	 * @return int
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * 
	 * Asigna la coordenada y2 de la linea.
	 * 
	 * @param int
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}

	/**
	 * 
	 * Devuelve la compuerta que tiene en la cola la linea.
	 * 
	 * @return Gate
	 */
	public Gate getTail() {
		return tail;
	}

	/**
	 * 
	 * Asigna la compuerta que tiene en la cola la linea.
	 * 
	 * @param Gate
	 */
	public void setTail(Gate tail) {
		this.tail = tail;
	}

	/**
	 * 
	 * Devuelve la compuerta que se encuentra en el pico de la linea.
	 * 
	 * @return Gate
	 */
	public Gate getPeak() {
		return peak;
	}

	/**
	 * 
	 * Asigna la compuerta que se encuentra en el pico de la linea.
	 * 
	 * @param Gate
	 */
	public void setPeak(Gate peak) {
		this.peak = peak;
	}

	/**
	 * 
	 * Devuelve el color de la linea.
	 * 
	 * @return Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * Asigna el color de la linea.
	 * 
	 * @param Color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * 
	 * Devuelve un String con la informacion de la linea.
	 * 
	 * @param String
	 */
	public String toString() {
		return " tail: " + getTail().getGateID() + "   peak: " + getPeak().getGateID();
	}
}
