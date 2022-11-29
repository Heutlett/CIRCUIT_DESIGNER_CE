package com.circuitdesigner.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.circuitdesigner.structures.WorkspacePanel;
import java.awt.event.MouseWheelListener;
/**
 * 
 * @author Adrian Araya Ramirez
 * 
 * @version 1.8
 *
 */
public class Window extends JFrame{

	private JPanel panel;
	private JScrollPane scrollPane;	
	/**
	 * Constructor de la ventana.
	 */
	public Window() {
		setResizable(false);
		setTitle("Circuit Designer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 772);
		setLocationRelativeTo(null);
		initComponents();
	}
	/**
	 * Inicializa los componentes de la ventana.
	 */
	private void initComponents() {
		
		panel = new WorkspacePanel();
		scrollPane = new JScrollPane();		
		scrollPane.setBounds(0,0,1500,1000);
		scrollPane.setViewportView(panel);
		getContentPane().add(scrollPane);
	}
	
}
