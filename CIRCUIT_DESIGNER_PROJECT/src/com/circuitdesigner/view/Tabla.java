package com.circuitdesigner.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;

public class Tabla extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public Tabla(Object[][] datosFila, String [] nombresColumnas) {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setBounds(100, 100, 1000, 900);
		
		table = new JTable(datosFila, nombresColumnas);
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		

	}
}
