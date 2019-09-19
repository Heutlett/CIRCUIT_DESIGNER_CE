package com.circuitdesigner.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.circuitdesigner.structures.WorkspacePanel;

public class Window extends JFrame{

	private JPanel panel;
	private JScrollPane scrollPane;	
	
	public Window() {
		setResizable(false);
		setTitle("Circuit Designer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 872);
		setLocationRelativeTo(null);
		initComponents();
	}

	private void initComponents() {

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0,0,1500,1000);
		panel = new WorkspacePanel();
		scrollPane.setViewportView(panel);
		getContentPane().add(scrollPane);
	}
}
