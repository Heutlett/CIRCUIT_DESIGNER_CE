package com.circuitdesigner.estructuras;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel{
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(new Color( (int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1    ));
		
		g.drawLine(x1, y1, x2, y2);
		
		
	}
	
	public void pintar(Graphics g, int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		paintComponent(g);
	}

}
