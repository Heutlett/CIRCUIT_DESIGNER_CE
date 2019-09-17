package com.circuitdesigner.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import com.circuitdesigner.estructuras.Compuerta;
import com.circuitdesigner.estructuras.PanelWorkspace;
import com.circuitdesigner.estructuras.Sistema;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame{

	private JPanel panel;
	private JScrollPane scrollPane;	
	
	public Window() {
		setResizable(false);
		setTitle("Circuit Designer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 872);
		setLocationRelativeTo(null);
		iniciarComponentes();
	}

	private void iniciarComponentes() {

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0,0,1500,1000);
		
		panel = new PanelWorkspace();
		
		scrollPane.setViewportView(panel);
		
		getContentPane().add(scrollPane);
	}
	
	
	
	
}
