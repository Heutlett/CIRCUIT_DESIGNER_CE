package com.circuitdesigner.structures;

import java.awt.Color;
import javax.swing.JLabel;

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
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param color
	 * @param tailDeleteLabel
	 * @param peakDeleteLabel
	 * @param tail
	 * @param peak
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
	 * @return
	 */
	public JLabel getTailDeleteLabel() {
		return tailDeleteLabel;
	}


	/**
	 * @param tailDeleteLabel
	 */
	public void setTailDeleteLabel(JLabel tailDeleteLabel) {
		this.tailDeleteLabel = tailDeleteLabel;
	}


	/**
	 * @return
	 */
	public JLabel getPeakDeleteLabel() {
		return peakDeleteLabel;
	}


	/**
	 * @param peakDeleteLabel
	 */
	public void setPeakDeleteLabel(JLabel peakDeleteLabel) {
		this.peakDeleteLabel = peakDeleteLabel;
	}


	/**
	 * @return
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * @param x1
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}

	/**
	 * @return
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * @param y1
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}

	/**
	 * @return
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * @param x2
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * @return
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * @param y2
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}

	/**
	 * @return
	 */
	public Gate getTail() {
		return tail;
	}

	/**
	 * @param tail
	 */
	public void setTail(Gate tail) {
		this.tail = tail;
	}

	/**
	 * @return
	 */
	public Gate getPeak() {
		return peak;
	}

	/**
	 * @param peak
	 */
	public void setPeak(Gate peak) {
		this.peak = peak;
	}

	/**
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 *
	 */
	public String toString() {
		return " tail: " + getTail().getGateID() + "   peak: " + getPeak().getGateID();
	}
	
	

}
