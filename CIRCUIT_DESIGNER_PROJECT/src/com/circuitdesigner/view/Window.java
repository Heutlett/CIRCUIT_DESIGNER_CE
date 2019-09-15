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
	private JScrollPane scrollPane;
	private boolean borrador = false;
	
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
		
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		//setContentPane(contentPane);
		
		Image i = Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/com/circuitdesigner/media/img_borrador_cursor.PNG"));
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, new Point(0,0), "cursor1"); 
		//contentPane.setLayout(null);
		
		panelPalette = new JPanel();
		//panelPalette.setBounds(0, 0, 1444, 837);
		panelPalette.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPalette.setBackground(Color.WHITE);
		
		//contentPane.add(panelPalette);
		panelPalette.setLayout(null);
		
		JLabel lbl_palette_and = new JLabel("");
		lbl_palette_and.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_and.PNG")));
		lbl_palette_and.setBounds(74, 100, 80, 80);
		panelPalette.add(lbl_palette_and);
		createMouseEvents(lbl_palette_and);
		
		JLabel lbl_palette_nand = new JLabel("");
		lbl_palette_nand.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nand.PNG")));
		lbl_palette_nand.setBounds(74, 200, 80, 80);
		panelPalette.add(lbl_palette_nand);
		createMouseEvents(lbl_palette_nand);
		
		JLabel lbl_palette_or = new JLabel("");
		lbl_palette_or.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_or.PNG")));
		lbl_palette_or.setBounds(74, 300, 80, 80);
		panelPalette.add(lbl_palette_or);
		createMouseEvents(lbl_palette_or);
		
		JLabel lbl_palette_nor = new JLabel("");
		lbl_palette_nor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nor.PNG")));
		lbl_palette_nor.setBounds(74, 400, 80, 80);
		panelPalette.add(lbl_palette_nor);
		createMouseEvents(lbl_palette_nor);
		
		JLabel lbl_palette_not = new JLabel("");
		lbl_palette_not.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_not.PNG")));
		lbl_palette_not.setBounds(74, 500, 80, 80);
		panelPalette.add(lbl_palette_not);
		createMouseEvents(lbl_palette_not);
		
		JLabel lbl_palette_xor = new JLabel("");
		lbl_palette_xor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_xor.PNG")));
		lbl_palette_xor.setBounds(74, 600, 80, 80);
		panelPalette.add(lbl_palette_xor);
		createMouseEvents(lbl_palette_xor);
		
		JLabel lbl_palette_xnor = new JLabel("");
		lbl_palette_xnor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_xnor.PNG")));
		lbl_palette_xnor.setBounds(74, 700, 80, 80);
		panelPalette.add(lbl_palette_xnor);
		createMouseEvents(lbl_palette_xnor);
		
		JLabel lblPalette = new JLabel("Palette");
		lblPalette.setBounds(0, 0, 238, 59);
		panelPalette.add(lblPalette);
		lblPalette.setBackground(Color.WHITE);
		lblPalette.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalette.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lbl_linea1 = new JLabel("");
		lbl_linea1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_linea1.setBounds(0, 0, 237, 841);
		panelPalette.add(lbl_linea1);
		
		JLabel lbl_linea2 = new JLabel("");
		lbl_linea2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_linea2.setBounds(0, 65, 236, 1);
		panelPalette.add(lbl_linea2);
		
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
		btn_borrador.setBounds(236, 0, 80, 80);
		panelPalette.add(btn_borrador);
		
		panelPalette.setPreferredSize(new Dimension(2444, 1837));
		
		scrollPane.setViewportView(panelPalette);
		
		add(scrollPane);
	}
	
	private void createMouseEvents(JLabel label) {
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(copia != null) {
					copia.setLocation(e.getX()+label.getX()-40, e.getY()+label.getY()-35);
					panelPalette.updateUI();
				}
			}
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
				if(copiaLabel.getX()<189) {
					copiaLabel.setLocation(235, copiaLabel.getY());
				}
			}
		});		
		
		copia.setIcon(label.getIcon());
		copia.setBounds(label.getBounds());
		
		panelPalette.add(copia);
		panelPalette.updateUI();
		
	}
}
