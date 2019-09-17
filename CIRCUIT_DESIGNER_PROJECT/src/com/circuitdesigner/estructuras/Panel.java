package com.circuitdesigner.estructuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	
	public void paintComponent(Graphics g ) {
		
		super.paintComponent(g);
		
		//System.out.println(  +" " + (int)Math.random()*255 + " "+ (int)Math.random()*255);
		
		g.setColor(new Color( (int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1    ));
		
		g.drawLine(0, 0, 1050, 49);
		
		
		
		
		
		//g.drawLine(0, 0, 1050, 49);
		
	}

}
