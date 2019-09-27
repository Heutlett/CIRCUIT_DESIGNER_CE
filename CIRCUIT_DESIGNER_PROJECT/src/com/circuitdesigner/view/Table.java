package com.circuitdesigner.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 * 
 * @author Adrian Araya Ramirez
 * 
 * @version 1.8
 *
 */
public class Table extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * 
	 * Constructor de la tabla
	 * 
	 * @param Object[][]
	 * @param String[]
	 */
	public Table(Object[][] rowData, String [] columnNames) {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setBounds(100, 100, 1000, 900);
		
		table = new JTable(rowData, columnNames);
		
		add(new JScrollPane(table), BorderLayout.CENTER);

	}
	/**
	 * 
	 * Selecciona la fila n
	 * 
	 * @param int
	 */
	public void seleccionarFila(int n) {
		table.setRowSelectionInterval(n, n);
	}
}
