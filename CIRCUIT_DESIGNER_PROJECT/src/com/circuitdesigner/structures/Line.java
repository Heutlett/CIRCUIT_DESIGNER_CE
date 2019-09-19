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

	
	public JLabel getTailDeleteLabel() {
		return tailDeleteLabel;
	}


	public void setTailDeleteLabel(JLabel tailDeleteLabel) {
		this.tailDeleteLabel = tailDeleteLabel;
	}


	public JLabel getPeakDeleteLabel() {
		return peakDeleteLabel;
	}


	public void setPeakDeleteLabel(JLabel peakDeleteLabel) {
		this.peakDeleteLabel = peakDeleteLabel;
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

	public Gate getTail() {
		return tail;
	}

	public void setTail(Gate tail) {
		this.tail = tail;
	}

	public Gate getPeak() {
		return peak;
	}

	public void setPeak(Gate peak) {
		this.peak = peak;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString() {
		return " tail: " + getTail().getGateID() + "   peak: " + getPeak().getGateID();
	}
	
	

}
