package com.circuitdesigner.estructuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.circuitdesigner.view.Window;

public class WorkspacePanel extends JPanel implements MouseListener{

	private JLabel labelCopy;
	private Model m1;
	private boolean eraser = false;
	private Gate gateCopy;
	private boolean removeLine = false;
	private boolean draw = false;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color color;
	private JLabel startPoint;
	private JLabel endPoint;
	
	public WorkspacePanel() {
		
		m1 = Model.getInstance();
		initComponents();
		
	}
	
	private void borrarLineaGrafico(Graphics g) {
		g.setColor(Color.white);
		borrarLineaLista(x1,y1,x2,y2,color);
		removeLine = false;
		
	}
	
	private void borrarLineaLista(int x1,int y1,int x2,int y2,Color c) {
		
		for(int i = 0; i < m1.getLineList().size();i++) {
			
			Line l1 = m1.getLineList().get(i);
			
			if(l1.getX1() == x1 && l1.getX2() == x2 && l1.getY1() == y1 && l1.getY2() == y2 && l1.getColor().equals(c)) {
				System.out.println("Encontrado");
				this.remove(m1.getLineList().get(i).getPeakDeleteLabel());
				this.remove(m1.getLineList().get(i).getTailDeleteLabel());
				m1.getLineList().remove(i);
			}
			
		}
		
	}
	
	private void borrarLinea(Line l) {
		
		this.x1 = l.getX1();
		this.y1 = l.getY1();
		this.x2 = l.getX2();
		this.y2 = l.getY2();
		this.color = l.getColor();
		
		l.getTail().getLines().remove(l);
		l.getPeak().getLines().remove(l);
		
		removeLine = true;
		
		this.repaint();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3));
		
		if(removeLine) {
			borrarLineaGrafico(g);
		}else {
			g.setColor(color);
		}
		
		if(draw) {
			
			g2d.drawLine(x1, y1, x2, y2);
			draw = false;
		}
		
		repintar(g2d);
	}
	
	private void actualizarPantalla() {
		
		this.repaint();
		
	}
	
	private void repintar(Graphics2D g) {
		
		for(int i = 0; i < m1.getLineList().size();i++) {
			
			g.setColor(m1.getLineList().get(i).getColor());
			g.drawLine(m1.getLineList().get(i).getX1(), m1.getLineList().get(i).getY1(), m1.getLineList().get(i).getX2(),m1.getLineList().get(i).getY2());
		
		}
	}
	
	private void agregarComportamientoLinea(Line l) {
		
		l.getPeakDeleteLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				borrarLinea(l);
				System.out.println("Eliminando");
			}
		});
		l.getTailDeleteLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				borrarLinea(l);
				System.out.println("Eliminando");
			}
		});
		
	}
	
	private Line pintarLinea(int x1, int y1, int x2, int y2, Gate c1, Gate c2) {
		
		draw = true;
		color = new Color((int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1,(int) (Math.random() * 255) + 1);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		JLabel label1 = new JLabel("  X");
		label1.setFont(new Font("Serif", Font.BOLD, 14));
		this.add(label1);
		JLabel label2 = new JLabel("  X");
		label2.setFont(new Font("Serif", Font.BOLD, 14));
		this.add(label2);
		Line l1 = new Line(x1,y1,x2,y2,color,label1, label2,c1,c2);
		agregarComportamientoLinea(l1);
		m1.getLineList().add(l1);
		this.repaint();
		
		return l1;
		
	}
	
	private void initComponents() {
		
		addMouseListener(this);
		
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
				eraser = true;
				setCursor(c);
			}
		});
		btn_borrador.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_borrador.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_borrador.png")));
		btn_borrador.setBackground(Color.WHITE);
		btn_borrador.setBounds(236, 0, 80, 80);
		add(btn_borrador);
		
		setPreferredSize(new Dimension(2444, 1837));
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m1.showTable();
			}
		});
		btnPlay.setBounds(164, 22, 69, 25);
		add(btnPlay);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m1.printGates();
			}
		});
		btnPrint.setBounds(12, 22, 59, 25);
		add(btnPrint);
		
	}
	
	private void createMouseEvents(JLabel label) {
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(labelCopy != null) {
					labelCopy.setLocation(e.getX()+label.getX()-40, e.getY()+label.getY()-35);
					actualizarPantalla();
				}
			}
		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gateCopy = copiarCompuerta(label);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int y = 17;
				if(e.getX()<189) {
					remove(labelCopy);
					actualizarPantalla();
				}else {
					for(int i = 0; i < gateCopy.getInputs().size(); i++) {
						gateCopy.getInputs().get(i).getGateLabel().setVisible(true);
						gateCopy.getInputs().get(i).getGateLabel().setLocation(gateCopy.getGateLabel().getX()-25, gateCopy.getGateLabel().getY()+y);
						y = y+25;
					}
					gateCopy.getOutputs().getGateLabel().setVisible(true);
					gateCopy.getOutputs().getGateLabel().setLocation(gateCopy.getGateLabel().getX()+83, gateCopy.getGateLabel().getY()+28);
					gateCopy.getLabelID().setVisible(true);
					gateCopy.getLabelID().setLocation(gateCopy.getGateLabel().getX()+37, gateCopy.getGateLabel().getY());
				}
				
				
			}
		});
	}
	
	private void agregarEntradaSalida(Gate c) {
		
		for(int i = 0; i < c.getInputs().size(); i++) {
			add(c.getInputs().get(i).getGateLabel());
		}
		add(c.getOutputs().getGateLabel());
		add(c.getLabelID());
	}
	
	private Gate crearCompuerta(String tipo, JLabel copiaLabel) {
		
		Gate newCompuerta = null;
		
		if(tipo.equals("AND")) {
			newCompuerta = new Gate(Gate.GateType.AND,copiaLabel);
		}
		if(tipo.equals("NAND")) {
			newCompuerta = new Gate(Gate.GateType.NAND,copiaLabel);
		}
		if(tipo.equals("OR")) {
			newCompuerta = new Gate(Gate.GateType.OR,copiaLabel);
		}
		if(tipo.equals("NOR")) {
			newCompuerta = new Gate(Gate.GateType.NOR,copiaLabel);
		}
		if(tipo.equals("NOT")) {
			newCompuerta = new Gate(Gate.GateType.NOT,copiaLabel);
		}
		if(tipo.equals("XOR")) {
			newCompuerta = new Gate(Gate.GateType.XOR,copiaLabel);
		}
		if(tipo.equals("XNOR")){
			newCompuerta = new Gate(Gate.GateType.XNOR,copiaLabel);
		}
		
		agregarEntradaSalida(newCompuerta);
		
		return newCompuerta;
	}
	
	private void comprobacionDeLineas(JLabel l) {
		
		l.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(l.getText().contains("C")) {
					JOptionPane.showMessageDialog(null, "No se permite la conexion");
					resetPoints();
					return;
				}
				
				if(startPoint == null) {
					startPoint = (JLabel)e.getComponent();
					startPoint.setForeground(Color.red);
				}else {
					endPoint = (JLabel)e.getComponent();
					endPoint.setForeground(Color.red);
				}
				
				if(startPoint != null && endPoint != null) {
					if(startPoint.getName().equals(endPoint.getName())) {
						JOptionPane.showMessageDialog(null, "No se permite la conexion");
						startPoint.setForeground(Color.black);
						startPoint = null;
						endPoint = null;
					}else {
						if((startPoint.getName().contains("o") && endPoint.getName().contains("o")) || (startPoint.getName().contains("i") && endPoint.getName().contains("i"))) {
							JOptionPane.showMessageDialog(null, "No se permite la conexion");
						}else {
							
							if(startPoint.getName().contains("i")) {
								
								crearLinea(m1.findGateByInput(startPoint.getName()), m1.findGateByOutput(endPoint.getName()), (startPoint.getName()));
								
							}else {
								if(startPoint.getName().contains("o")) {
									
									crearLinea(m1.findGateByInput(endPoint.getName()), m1.findGateByOutput(startPoint.getName()), (endPoint.getName()));
								}
								
							}
							m1.printInputs();
							m1.printOutputs();
						}
						resetPoints();
					}
				}

			}
		});		
		
	}
	
	private void resetPoints() {
		if(startPoint!=null) {
			startPoint.setForeground(Color.black);
		}
		if(endPoint!=null) {
			endPoint.setForeground(Color.black);
		}
		startPoint = null;
		endPoint = null;
	}
	
	private void crearLinea(Gate compuertaEntrada, Gate compuertaSalida, String idBorrar) {
		
		if(compuertaEntrada.getGateID().equals(compuertaSalida.getGateID())) {
			JOptionPane.showMessageDialog(null, "No se puede hacer una conexion entre la misma compuerta");
			return;
		}
		
		Line line = pintarLinea(startPoint.getX(),startPoint.getY()+11,endPoint.getX()-5,endPoint.getY()+5,compuertaEntrada,compuertaSalida);
		
		Gate compuertaBorrar = compuertaEntrada.findInput(idBorrar);
		remove(compuertaBorrar.getGateLabel());
		m1.removeInput(compuertaBorrar.getGateID());
		compuertaEntrada.getInputs().remove(compuertaBorrar);
		compuertaEntrada.getInputs().add(compuertaSalida);
		
		remove(compuertaSalida.getOutputs().getGateLabel());
		m1.removeOutput(compuertaSalida.getOutputs().getGateID());
		compuertaSalida.setOutputs(compuertaEntrada);
		compuertaSalida.setLocked(true);
		compuertaEntrada.setLocked(true);
		
		compuertaEntrada.getLines().add(line);
		compuertaSalida.getLines().add(line);
		
		System.out.println("Conexion realizada entre " + startPoint.getName() + " y " + endPoint.getName());
		
	}
	
	private void comportamientoEntradas(Gate c) {
		
		//Solucionar problema de conectar entrada y salida de la misma compuerta
		comprobacionDeLineas(c.getOutputs().getGateLabel());
		
		for(int i = 0; i < c.getInputs().size(); i++) {
			
			JLabel actual = c.getInputs().get(i).getGateLabel();
			
			comprobacionDeLineas(actual);
			
		}
		
	}

	private Gate copiarCompuerta(JLabel label) {
		
		JLabel copiaLabel = new JLabel();
		
		Gate newCompuerta = crearCompuerta(label.getText(),copiaLabel);
		
		m1.addInputsOutputs(newCompuerta);
		
		m1.printInputs();
		m1.printOutputs();
		
		m1.addCompuertas(newCompuerta);
		
		labelCopy = copiaLabel;
		
		newCompuerta.getGateLabel().setIcon(label.getIcon());
		newCompuerta.getGateLabel().setBounds(label.getBounds());
		newCompuerta.getLabelID().setLocation(label.getX()+10,label.getY()-90);
		
		newCompuerta.setInputOutputLocations();
		
		comportamientoEntradas(newCompuerta);
		
		eraser = false;
		setCursor(Cursor.getDefaultCursor());
		
		copiaLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if(!newCompuerta.isLocked()) {
					labelCopy = newCompuerta.getGateLabel();
					
					int y = -20;
					
					newCompuerta.getGateLabel().setLocation(e.getX()+labelCopy.getX()-40, e.getY()+labelCopy.getY()-35);
					
					for(int i = 0; i < newCompuerta.getInputs().size();i++) {
						newCompuerta.getInputs().get(i).getGateLabel().setLocation(e.getX()+labelCopy.getX()-65, e.getY()+labelCopy.getY()+y);
						y = y + 25;
						
					}
					newCompuerta.getOutputs().getGateLabel().setLocation(e.getX()+labelCopy.getX()+42, e.getY()+labelCopy.getY()-7);
					newCompuerta.getLabelID().setLocation(e.getX()+labelCopy.getX(), e.getY()+labelCopy.getY()-35);
					
					actualizarPantalla();
				}
				
			}
			
		});
		copiaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(eraser) {
					borrarLabelsCompuerta(newCompuerta);
					borrarLineasCompuerta(newCompuerta);
					m1.removeGate(newCompuerta);
					actualizarPantalla();
					eraser = false;
					setCursor(Cursor.getDefaultCursor());
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(copiaLabel.getX()<189) {
					copiaLabel.setLocation(235, copiaLabel.getY());
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {

				Gate.printGatesInformation(newCompuerta);
			}
		});		

		add(labelCopy);
		actualizarPantalla();
		
		return newCompuerta;
		
	}
	
	private void borrarLineasCompuerta(Gate c) {
		
		for(int i = 0; i < c.getLines().size(); i++) {
			
			borrarLinea(c.getLines().get(i));
			
		}
		
	}
	
	private void borrarLabelsCompuerta(Gate c) {
		remove(c.getGateLabel());
		if(!c.getOutputs().getGateID().contains("C")) {
			remove(c.getOutputs().getGateLabel());
		}
		remove(c.getLabelID());
		for(int i = 0; i < c.getInputs().size(); i++) {
			if(!c.getInputs().get(i).getGateID().contains("C")) {
				remove(c.getInputs().get(i).getGateLabel());
			}
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		actualizarPantalla();
		setCursor(Cursor.getDefaultCursor());
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
