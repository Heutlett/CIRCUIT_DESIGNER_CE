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

	private JLabel copia;
	private JPanel panelPalette;
	private JPanel panelWorkSpace;
	private JScrollPane scrollPane;
	private boolean borrador = false;
	
	public Window() {
		setResizable(false);
		setTitle("Circuit Designer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1450, 996);
		setLocationRelativeTo(null);
		setLayout(null);
		iniciarComponentes();
	}

	private void iniciarComponentes() {

		Image i = Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/com/circuitdesigner/media/img_borrador_cursor.PNG"));
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, new Point(0,0), "cursor1"); 
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 0, 1000, 961);
		add(scrollPane);
		
		panelWorkSpace = new JPanel();
		panelWorkSpace.setBounds(100, 0, 1444, 837);
		panelWorkSpace.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelWorkSpace.setBackground(Color.BLUE);
		panelWorkSpace.setLayout(null);
		panelWorkSpace.setPreferredSize(new Dimension(2444, 1837));
		
		scrollPane.setViewportView(panelWorkSpace);
		
		
		
		
		/*
		
		JButton btn_borrador = new JButton("");
		btn_borrador.setContentAreaFilled(false);
		btn_borrador.setOpaque(false);
		btn_borrador.setForeground(Color.WHITE);
		btn_borrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrador = true;
				panelPalette.setCursor(c);
			}
		});
		btn_borrador.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_borrador.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_borrador.png")));
		btn_borrador.setBackground(Color.WHITE);
		btn_borrador.setBounds(0, 0, 80, 80);
		panelPalette.add(btn_borrador);
		
		panelWorkSpace = new JPanel();
		panelWorkSpace.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelWorkSpace.setBackground(Color.WHITE);
		panelWorkSpace.setBounds(0, 0, 236, 1000);
		add(panelWorkSpace);
		panelWorkSpace.setLayout(null);
		
		JLabel lblPalette = new JLabel("Palette");
		lblPalette.setBounds(76, 13, 75, 31);
		panelWorkSpace.add(lblPalette);
		lblPalette.setBackground(Color.WHITE);
		lblPalette.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalette.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lbl_linea2 = new JLabel("");
		lbl_linea2.setBounds(0, 64, 236, 1);
		panelWorkSpace.add(lbl_linea2);
		lbl_linea2.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lbl_palette_and = new JLabel("");
		lbl_palette_and.setBounds(71, 117, 80, 80);
		panelWorkSpace.add(lbl_palette_and);
		lbl_palette_and.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_and.PNG")));
		createMouseEvents(lbl_palette_and);
		
		JLabel lbl_palette_nand = new JLabel("");
		lbl_palette_nand.setBounds(71, 217, 80, 80);
		panelWorkSpace.add(lbl_palette_nand);
		lbl_palette_nand.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nand.PNG")));
		createMouseEvents(lbl_palette_nand);
		
		JLabel lbl_palette_or = new JLabel("");
		lbl_palette_or.setBounds(71, 317, 80, 80);
		panelWorkSpace.add(lbl_palette_or);
		lbl_palette_or.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_or.PNG")));
		createMouseEvents(lbl_palette_or);
		
		JLabel lbl_palette_nor = new JLabel("");
		lbl_palette_nor.setBounds(71, 417, 80, 80);
		panelWorkSpace.add(lbl_palette_nor);
		lbl_palette_nor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nor.PNG")));
		createMouseEvents(lbl_palette_nor);
		
		JLabel lbl_palette_not = new JLabel("");
		lbl_palette_not.setBounds(71, 517, 80, 80);
		panelWorkSpace.add(lbl_palette_not);
		lbl_palette_not.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_not.PNG")));
		createMouseEvents(lbl_palette_not);
		
		JLabel lbl_palette_xor = new JLabel("");
		lbl_palette_xor.setBounds(71, 617, 80, 80);
		panelWorkSpace.add(lbl_palette_xor);
		lbl_palette_xor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_xor.PNG")));
		createMouseEvents(lbl_palette_xor);
		
		JLabel lbl_palette_xnor = new JLabel("");
		lbl_palette_xnor.setBounds(71, 717, 80, 80);
		panelWorkSpace.add(lbl_palette_xnor);
		lbl_palette_xnor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_xnor.PNG")));
		createMouseEvents(lbl_palette_xnor);
		
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_output.png")));
		label.setBounds(76, 817, 80, 80);
		panel.add(label);
		*/
		
		

	}
	
	private void createMouseEvents(JLabel label) {
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(copia != null) {
					copia.setLocation(e.getX()-200, e.getY());
					panelPalette.updateUI();
					
				}
			}
			
			//+label.getX()-40 +label.getY()-35
		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copiarCompuerta(label);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getX()<189) {
					panelPalette.remove(copia);
					panelPalette.updateUI();
				}
				
			}
		});
	}
	
	private void copiarCompuerta(JLabel label) {
		
		JLabel copiaLabel = new JLabel();
		copia = copiaLabel;
		borrador = false;
		panelPalette.setCursor(Cursor.getDefaultCursor());
		
		copiaLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				copiaLabel.setLocation(e.getX()+copia.getX()-40, e.getY()+copia.getY()-35);
				panelPalette.updateUI();
				
				
			}
			
		});
		copiaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copia = copiaLabel;
				if(borrador) {
					panelPalette.remove(copiaLabel);
					panelPalette.updateUI();
					borrador = false;
					panelPalette.setCursor(Cursor.getDefaultCursor());
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(copiaLabel.getX()<0) {
					copiaLabel.setLocation(0, copiaLabel.getY());
				}
			}
		});		
		
		copia.setIcon(label.getIcon());
		copia.setBounds(label.getX()-120,label.getY()-40,80,80);
		
		panelPalette.add(copia);
		panelPalette.updateUI();
		
	}
}
