package com.circuitdesigner.estructuras;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.circuitdesigner.view.Window;

public class PanelWorkspace extends JPanel implements MouseListener{

	private JLabel copia;
	private Sistema s1;
	private boolean borrador = false;
	private Compuerta copiaCompuerta;
	
	private boolean borrarLinea = false;
	private boolean dibujar = false;
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color color;
	
	public PanelWorkspace() {
		
		s1 = Sistema.getInstance();
		initComponents();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if(borrarLinea) {
			borrarLinea(g);
		}else {
			g.setColor(color);
		}
		
		if(dibujar) {
			g.drawLine(x1, y1, x2, y2);
			dibujar = false;
		}
		
		repintar(g);
	}
	
	private void actualizarPantalla() {
		
		this.repaint();
		
	}
	
	private void repintar(Graphics g) {
		
		for(int i = 0; i < s1.getListaLineas().size();i++) {
			
			g.setColor(s1.getListaLineas().get(i).getColor());
			g.drawLine(s1.getListaLineas().get(i).getX1(), s1.getListaLineas().get(i).getY1(), s1.getListaLineas().get(i).getX2(),s1.getListaLineas().get(i).getY2());
		
		}
	}
	
	private void borrarLineaLista(int x1,int y1,int x2,int y2,Color c) {
		
		for(int i = 0; i < s1.getListaLineas().size();i++) {
			
			Linea l1 = s1.getListaLineas().get(i);
			
			if(l1.getX1() == x1 && l1.getX2() == x2 && l1.getY1() == y1 && l1.getY2() == y2 && l1.getColor().equals(c)) {
				this.remove(s1.getListaLineas().get(i).getLabel());
				s1.getListaLineas().remove(i);
			}
			
		}
		
	}
	
	private void borrarLinea(Graphics g) {
		g.setColor(Color.white);
		borrarLinea = false;
		borrarLineaLista(x1,y1,x2,y2,color);
		
	}
	
	private void agregarComportamientoLinea(Linea l) {
		
		l.getLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				borrarLinea(l);
				System.out.println("Eliminando");
			}
		});
		
	}
	
	private void pintarLinea(int x1, int y1, int x2, int y2) {
		
		dibujar = true;
		color = new Color((int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		JLabel label = new JLabel("X");
		this.add(label);
		Linea l1 = new Linea(x1,y1,x2,y2,color,label);
		agregarComportamientoLinea(l1);
		s1.getListaLineas().add(l1);
		this.repaint();
		
	}
	
	private void borrarLinea(Linea l) {
		
		borrarLinea = true;
		
		this.x1 = l.getX1();
		this.y1 = l.getY1();
		this.x2 = l.getX2();
		this.y2 = l.getY2();
		this.repaint();
		
	}
	
	private void initComponents() {
		
		addMouseListener(this);
		
		pintarLinea(100,400,1000,100);
		
		pintarLinea(100,100,540,100);
		
		pintarLinea(0,123,241,234);
		
		actualizarPantalla();
		
		Image i = Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/com/circuitdesigner/media/img_borrador_cursor.PNG"));
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, new Point(0,0), "cursor1"); 
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		
		setLayout(null);
		
		JLabel lbl_palette_and = new JLabel("AND");
		lbl_palette_and.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_and.PNG")));
		lbl_palette_and.setBounds(74, 100, 80, 80);
		add(lbl_palette_and);
		createMouseEvents(lbl_palette_and);;
		
		JLabel lbl_palette_nand = new JLabel("NAND");
		lbl_palette_nand.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nand.PNG")));
		lbl_palette_nand.setBounds(74, 200, 80, 80);
		add(lbl_palette_nand);
		createMouseEvents(lbl_palette_nand);
		
		JLabel lbl_palette_or = new JLabel("OR");
		lbl_palette_or.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_or.PNG")));
		lbl_palette_or.setBounds(74, 300, 80, 80);
		add(lbl_palette_or);
		createMouseEvents(lbl_palette_or);
		
		JLabel lbl_palette_nor = new JLabel("NOR");
		lbl_palette_nor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nor.PNG")));
		lbl_palette_nor.setBounds(74, 400, 80, 80);
		add(lbl_palette_nor);
		createMouseEvents(lbl_palette_nor);
		
		JLabel lbl_palette_not = new JLabel("NOT");
		lbl_palette_not.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_not.PNG")));
		lbl_palette_not.setBounds(74, 500, 80, 80);
		add(lbl_palette_not);
		createMouseEvents(lbl_palette_not);
		
		JLabel lbl_palette_xor = new JLabel("XOR");
		lbl_palette_xor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_xor.PNG")));
		lbl_palette_xor.setBounds(74, 600, 80, 80);
		add(lbl_palette_xor);
		createMouseEvents(lbl_palette_xor);
		
		JLabel lbl_palette_xnor = new JLabel("XNOR");
		lbl_palette_xnor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_xnor.PNG")));
		lbl_palette_xnor.setBounds(74, 700, 80, 80);
		add(lbl_palette_xnor);
		createMouseEvents(lbl_palette_xnor);
		
		JLabel lblPalette = new JLabel("Palette");
		lblPalette.setBounds(0, 0, 238, 59);
		add(lblPalette);
		lblPalette.setBackground(Color.WHITE);
		lblPalette.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalette.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lbl_linea1 = new JLabel("");
		lbl_linea1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_linea1.setBounds(0, 0, 237, 841);
		add(lbl_linea1);
		
		JLabel lbl_linea2 = new JLabel("");
		lbl_linea2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_linea2.setBounds(0, 65, 236, 1);
		add(lbl_linea2);
		
		JButton btn_borrador = new JButton("");
		btn_borrador.setContentAreaFilled(false);
		btn_borrador.setOpaque(false);
		btn_borrador.setForeground(Color.WHITE);
		btn_borrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrador = true;
				setCursor(c);
			}
		});
		btn_borrador.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_borrador.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_borrador.png")));
		btn_borrador.setBackground(Color.WHITE);
		btn_borrador.setBounds(236, 0, 80, 80);
		add(btn_borrador);
		
		setPreferredSize(new Dimension(2444, 1837));
		
	}
	
	private void createMouseEvents(JLabel label) {
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(copia != null) {
					copia.setLocation(e.getX()+label.getX()-40, e.getY()+label.getY()-35);
					actualizarPantalla();
				}
			}
		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copiaCompuerta = copiarCompuerta(label);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int y = 5;
				if(e.getX()<189) {
					remove(copia);
					actualizarPantalla();
				}else {
					for(int i = 0; i < copiaCompuerta.getEntradas().size(); i++) {
						copiaCompuerta.getEntradas().get(i).getLabelCompuerta().setVisible(true);
						copiaCompuerta.getEntradas().get(i).getLabelCompuerta().setLocation(copiaCompuerta.getLabelCompuerta().getX()-17, copiaCompuerta.getLabelCompuerta().getY()+y);
						y = y+20;
					}
					copiaCompuerta.getSalida().getLabelCompuerta().setVisible(true);
					copiaCompuerta.getSalida().getLabelCompuerta().setLocation(copiaCompuerta.getLabelCompuerta().getX()+75, copiaCompuerta.getLabelCompuerta().getY()+20);
				}
				
				
			}
		});
	}
	
	private void agregarEntradaSalida(Compuerta c) {
		
		for(int i = 0; i < c.getEntradas().size(); i++) {
			add(c.getEntradas().get(i).getLabelCompuerta());
		}
		add(c.getSalida().getLabelCompuerta());
	}
	
	private Compuerta crearCompuerta(String tipo, JLabel copiaLabel) {
		
		Compuerta newCompuerta = null;
		
		if(tipo.equals("AND")) {
			newCompuerta = new Compuerta(Compuerta.tipoCompuerta.AND,copiaLabel);
		}
		if(tipo.equals("NAND")) {
			newCompuerta = new Compuerta(Compuerta.tipoCompuerta.NAND,copiaLabel);
		}
		if(tipo.equals("OR")) {
			newCompuerta = new Compuerta(Compuerta.tipoCompuerta.OR,copiaLabel);
		}
		if(tipo.equals("NOR")) {
			newCompuerta = new Compuerta(Compuerta.tipoCompuerta.NOR,copiaLabel);
		}
		if(tipo.equals("NOT")) {
			newCompuerta = new Compuerta(Compuerta.tipoCompuerta.NOT,copiaLabel);
		}
		if(tipo.equals("XOR")) {
			newCompuerta = new Compuerta(Compuerta.tipoCompuerta.XOR,copiaLabel);
		}
		if(tipo.equals("XNOR")){
			newCompuerta = new Compuerta(Compuerta.tipoCompuerta.XNOR,copiaLabel);
		}
		
		agregarEntradaSalida(newCompuerta);
		
		return newCompuerta;
	}
	
	private void ubicarEntradasYSalidas(Compuerta c) {
		
		int y = c.getLabelCompuerta().getY() - 20;
		int x = c.getLabelCompuerta().getX();
		
		c.getSalida().getLabelCompuerta().setLocation(x+90,y+20);
		for(int i = 0; i < c.getEntradas().size(); i++) {
			
			c.getEntradas().get(i).getLabelCompuerta().setLocation(x,y);
			y += 20;
			
		}
		
	}
	
	private Compuerta copiarCompuerta(JLabel label) {
		
		JLabel copiaLabel = new JLabel();
		
		Compuerta newCompuerta = crearCompuerta(label.getText(),copiaLabel);
		
		s1.addCompuertas(newCompuerta);
		
		copia = copiaLabel;
		
		newCompuerta.getLabelCompuerta().setIcon(label.getIcon());
		newCompuerta.getLabelCompuerta().setBounds(label.getBounds());
		
		ubicarEntradasYSalidas(newCompuerta);
		
		borrador = false;
		setCursor(Cursor.getDefaultCursor());
		
		copiaLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				int y = -7;
				
				newCompuerta.getLabelCompuerta().setLocation(e.getX()+copia.getX()-40, e.getY()+copia.getY()-35);
				
				for(int i = 0; i < newCompuerta.getEntradas().size();i++) {
					newCompuerta.getEntradas().get(i).getLabelCompuerta().setLocation(e.getX()+copia.getX()-50, e.getY()+copia.getY()+y);
					y = y - 20;
					
				}
				newCompuerta.getSalida().getLabelCompuerta().setLocation(e.getX()+copia.getX()+45, e.getY()+copia.getY()-13);
				
				actualizarPantalla();
				
			}
			
		});
		copiaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				copia = copiaLabel;
				if(borrador) {
					remove(copiaLabel);
					actualizarPantalla();
					borrador = false;
					setCursor(Cursor.getDefaultCursor());
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(copiaLabel.getX()<189) {
					copiaLabel.setLocation(235, copiaLabel.getY());
				}
			}
		});		

		
		
		add(copia);
		actualizarPantalla();
		
		return newCompuerta;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		actualizarPantalla();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
