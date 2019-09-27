package com.circuitdesigner.structures;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.circuitdesigner.structures.Gate.GateType;
import com.circuitdesigner.view.Window;
import com.circuitdesigner.xml.persistance.ControlGateXML;

public class WorkspacePanel extends JPanel implements MouseListener{

	private Model m1;
	private boolean eraser = false;
	private Gate lastGateCreated;
	private boolean draw = false;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color color;
	private JLabel startPoint;
	private JLabel endPoint;
	private String[] stringTypeGates = {"AND", "NAND", "OR","NOR", "NOT","XOR","XNOR", "INPUT","OUTPUT"};
	private GateType[] typeGates = {GateType.AND, GateType.NAND, GateType.OR, GateType.NOR, GateType.NOT, GateType.XOR, GateType.XNOR, GateType.INPUT, GateType.OUTPUT};
	private int yAnterior = 0;
	private boolean insertValues = false;
	private JLabel lbl_palette_and;
	private JLabel lbl_palette_nand;
	private JLabel lbl_palette_or;
	private JLabel lbl_palette_nor;
	private JLabel lbl_palette_not;
	private JLabel lbl_palette_xor;
	private JLabel lbl_palette_xnor;
	
	public WorkspacePanel() {
		
		m1 = Model.getInstance();
		initComponents();
		
		
	}
	
	private void initComponents() {
		

		
		addMouseListener(this);
		
		actualizarPantalla();
		
		Image i = Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/com/circuitdesigner/media/img_borrador_cursor.PNG"));
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, new Point(0,0), "cursor1"); 
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		
		setPreferredSize(new Dimension(2444, 1837));
		
		setLayout(null);
		
		lbl_palette_and = new JLabel("AND");
		lbl_palette_and.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_and.PNG")));
		lbl_palette_and.setBounds(74, 100, 80, 80);
		add(lbl_palette_and);
		createMouseEvents(lbl_palette_and);;
		
		lbl_palette_nand = new JLabel("NAND");
		lbl_palette_nand.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nand.PNG")));
		lbl_palette_nand.setBounds(74, 200, 80, 80);
		add(lbl_palette_nand);
		createMouseEvents(lbl_palette_nand);
		
		lbl_palette_or = new JLabel("OR");
		lbl_palette_or.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_or.PNG")));
		lbl_palette_or.setBounds(74, 300, 80, 80);
		add(lbl_palette_or);
		createMouseEvents(lbl_palette_or);
		
		lbl_palette_nor = new JLabel("NOR");
		lbl_palette_nor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_nor.PNG")));
		lbl_palette_nor.setBounds(74, 400, 80, 80);
		add(lbl_palette_nor);
		createMouseEvents(lbl_palette_nor);
		
		lbl_palette_not = new JLabel("NOT");
		lbl_palette_not.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_not.PNG")));
		lbl_palette_not.setBounds(74, 500, 80, 80);
		add(lbl_palette_not);
		createMouseEvents(lbl_palette_not);
		
		lbl_palette_xor = new JLabel("XOR");
		lbl_palette_xor.setIcon(new ImageIcon(Window.class.getResource("/com/circuitdesigner/media/img_xor.PNG")));
		lbl_palette_xor.setBounds(74, 600, 80, 80);
		add(lbl_palette_xor);
		createMouseEvents(lbl_palette_xor);
		
		lbl_palette_xnor = new JLabel("XNOR");
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
		lbl_linea1.setBounds(0, 0, 237, 945);
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
		
		
		
		JButton btnPlay = new JButton("Simulate");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m1.showTable(insertValues);
			}
		});
		btnPlay.setBounds(329, 0, 116, 41);
		add(btnPlay);
		
		JButton btnPrint = new JButton("Con valores");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(insertValues) {
					insertValues = false;
					btnPrint.setText("Con valores");
					
				}else {
					insertValues = true;
					btnPrint.setText("Sin valores");
				}
			}
		});
		btnPrint.setBounds(457, 0, 146, 41);
		add(btnPrint);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre con el que desea guardar el circuito");
				
				m1.guardarCircuito(nombre);
				
			}
		});
		btnNewButton.setBounds(622, 0, 132, 41);
		add(btnNewButton);
		
		JLabel lblGuardado = new JLabel("GUARDADO");
		lblGuardado.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGuardado.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblGuardado.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de circuito que desea abrir");
				
				recuperarModelo(nombre);
			}
		});
		lblGuardado.setBounds(55, 780, 116, 90);
		add(lblGuardado);
		
	}
	
	private void createMouseEvents(JLabel label) {
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				lastGateCreated.getGateLabel().setLocation(e.getX()+label.getX()-40, e.getY()+label.getY()-35);
			}

		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lastGateCreated = createGate(label);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int y = 17;
				if(e.getX()<189) {
					remove(lastGateCreated.getGateLabel());
					removeGate(lastGateCreated);
					Gate.decreaseQuantity();
				}else {
					for(int i = 0; i < lastGateCreated.getInputs().size(); i++) {
						lastGateCreated.getInputs().get(i).getGateLabel().setVisible(true);
						lastGateCreated.getInputs().get(i).getGateLabel().setLocation(lastGateCreated.getGateLabel().getX()-25, lastGateCreated.getGateLabel().getY()+y);
						y = y+25;
					}
					lastGateCreated.getOutput().getGateLabel().setVisible(true);
					lastGateCreated.getOutput().getGateLabel().setLocation(lastGateCreated.getGateLabel().getX()+83, lastGateCreated.getGateLabel().getY()+28);
					lastGateCreated.getLabelID().setVisible(true);
					lastGateCreated.getLabelID().setLocation(lastGateCreated.getGateLabel().getX()+37, lastGateCreated.getGateLabel().getY());
				}
			}
		});
	}
	
	private void desconectarCompuertas(Line l) {
		
		//output
		
		//PEAK
		if(l.getPeak().getOutput() != null) {
			if(l.getPeak().getOutput().equals(l.getPeak()) || l.getPeak().getOutput().equals(l.getTail())){
				l.getPeak().setOutput(null);
			}
			
		}

		//Tail
		if(l.getTail().getOutput() != null) {
			if(l.getTail().getOutput().equals(l.getPeak()) || l.getTail().getOutput().equals(l.getTail())){
				l.getTail().setOutput(null);
			}
			
		}

		//input
		
		//Peak
		for(int i = 0; i < l.getPeak().getInputs().size(); i++) {
			
			if(l.getPeak().getInputs().get(i).equals(l.getPeak()) || l.getPeak().getInputs().get(i).equals(l.getTail())) {
				l.getPeak().getInputs().remove(i);
			}
			
		}
		//tail
		for(int i = 0; i < l.getTail().getInputs().size(); i++) {
			
			if(l.getTail().getInputs().get(i).equals(l.getPeak()) || l.getTail().getInputs().get(i).equals(l.getTail())) {
				l.getTail().getInputs().remove(i);
			}
			
		}
		
	}
	
	private void borrarLineasCompuerta(Line l) {
		
		for(int i = 0; i < l.getPeak().getLines().size(); i++) {
			if(l.getPeak().getLines().get(i).equals(l)){

				l.getPeak().getLines().remove(i);
			}
		}
		for(int i = 0; i < l.getTail().getLines().size(); i++) {
			if(l.getTail().getLines().get(i).equals(l)){

				l.getTail().getLines().remove(i);
			}
		}
		
	}
	
	private void borrarLinea(Line l) {
		this.remove(l.getPeakDeleteLabel());
		this.remove(l.getTailDeleteLabel());
		borrarLineasCompuerta(l);
		desconectarCompuertas(l);
		m1.removeLine(l);
		
		
		this.repaint();
	}
	
	private void borrarLineasDelPanel(Gate c) {
		
		while(c.getLines().size() != 0) {
			borrarLinea(c.getLines().get(0));
		}

	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3));
		
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
	/*
	 * COMPORTAMIENTO DE LAS X DE LAS LINEAS
	 * 
	 */
	private void agregarComportamientoLinea(Line l) {
		
		l.getPeakDeleteLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!eraser) {
					borrarLinea(l);
					checkOutputsInputs();
					checkForUnlockDrag();
				}else {
					eraser = false;
					setCursor(Cursor.getDefaultCursor());
				}
			}
		});
		l.getTailDeleteLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!eraser) {
					borrarLinea(l);
					checkOutputsInputs();
					checkForUnlockDrag();
				}else {
					eraser = false;
					setCursor(Cursor.getDefaultCursor());
				}
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
		
		if(!m1.buscaLineaPorTailPeak(l1.getTail().getGateID(), l1.getPeak().getGateID())) {
			m1.getLineList().add(l1);
		}
		this.repaint();
		
		return l1;	
	}
	
	private void agregarEntradaSalida(Gate c) {
		
		for(int i = 0; i < c.getInputs().size(); i++) {
			add(c.getInputs().get(i).getGateLabel());
		}
		add(c.getOutput().getGateLabel());
		add(c.getLabelID());
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
		
		if(compuertaEntrada == null || compuertaSalida == null) {
			return;
		}
		
		if(compuertaEntrada.getGateID().equals(compuertaSalida.getGateID())) {
			JOptionPane.showMessageDialog(null, "No se puede hacer una conexion entre la misma compuerta");
			return;
		}
		
		Line line = pintarLinea(startPoint.getX(),startPoint.getY()+11,endPoint.getX()+5,endPoint.getY(),compuertaEntrada,compuertaSalida);
		
		Gate compuertaBorrar = compuertaEntrada.findInput(idBorrar);
		remove(compuertaBorrar.getGateLabel());
		m1.getInputList().remove(compuertaBorrar);
		compuertaEntrada.getInputs().remove(compuertaBorrar);
		compuertaEntrada.getInputs().add(compuertaSalida);
		
		remove(compuertaSalida.getOutput().getGateLabel());
		m1.getOutputList().remove(compuertaSalida.getOutput());
		compuertaSalida.setOutput(compuertaEntrada);
		compuertaSalida.setLocked(true);
		compuertaEntrada.setLocked(true);
		
		compuertaEntrada.getLines().add(line);
		compuertaSalida.getLines().add(line);
		
		System.out.println("Conexion realizada entre " + startPoint.getName() + " y " + endPoint.getName());
		
	}
	
	private void comportamientoEntradas(Gate c) {
		
		comprobacionDeLineas(c.getOutput().getGateLabel());
		
		for(int i = 0; i < c.getInputs().size(); i++) {
			
			JLabel actual = c.getInputs().get(i).getGateLabel();
			
			comprobacionDeLineas(actual);
			
		}
		
	}

	private GateType stringToGateType(String type) {
		
		for(int i = 0; i < 7; i++) {
			if(type.equals(stringTypeGates[i])){
				return typeGates[i];
			}
		}
		return null;
	}
	/*
	 * 
	 * Inicia los atributos de la compuerta y la agrega al modelo
	 * 
	 */
	private void initGate(Gate gate, JLabel labelModel) {
		agregarEntradaSalida(gate);
		
		m1.addInputsOutputs(gate);
		m1.printInputs();
		m1.printOutputs();
		m1.addGate(gate);
		
		gate.getGateLabel().setIcon(labelModel.getIcon());
		gate.getGateLabel().setBounds(labelModel.getBounds());
		gate.getLabelID().setLocation(labelModel.getX()+10,labelModel.getY()-90);
		
		gate.setInputOutputLocations();
		
		comportamientoEntradas(gate);
	}
	
	private Gate createGate(JLabel labelModel) {
		
		Gate newGate = new Gate(stringToGateType(labelModel.getText()));
		
		initGate(newGate, labelModel);
		
		eraser = false;
		
		setCursor(Cursor.getDefaultCursor());
		
		newGate.getGateLabel().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				dragGate(newGate, e);
			}
			
		});
		newGate.getGateLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(eraser) {
					removeGate(newGate);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				limitLocation(newGate);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Gate.toString(newGate);
			}
		});		

		add(newGate.getGateLabel());
		actualizarPantalla();
		
		return newGate;
	}
	
	/*
	 * Arrastra el gate por la pantalla
	 */
	private void dragGate(Gate gate, MouseEvent e) {
		if(!gate.isLocked()) {
			int myPositionX = gate.getGateLabel().getX();
			int myPositionY = gate.getGateLabel().getY();
			
			int y = -20;
			
			gate.getGateLabel().setLocation(e.getX()+myPositionX-40, e.getY()+myPositionY-35);
			
			for(int i = 0; i < gate.getInputs().size();i++) {
				gate.getInputs().get(i).getGateLabel().setLocation(e.getX()+myPositionX-65, e.getY()+myPositionY+y);
				y = y + 25;
				
			}
			gate.getOutput().getGateLabel().setLocation(e.getX()+myPositionX+42, e.getY()+myPositionY-7);
			gate.getLabelID().setLocation(e.getX()+myPositionX, e.getY()+myPositionY-35);
			
			actualizarPantalla();
		}
	}
	
	/*
	 * 
	 * Reubica la compuerta si se sale del borde del panel
	 * 
	 */
	private void limitLocation(Gate gate) {
		if(gate.getGateLabel().getX()<189) {
			gate.getGateLabel().setLocation(270, gate.getGateLabel().getY());
			for(int i = 0; i < gate.getInputs().size(); i++) {
				gate.getInputs().get(i).getGateLabel().setLocation(245, gate.getInputs().get(i).getGateLabel().getY());
			}
			gate.getOutput().getGateLabel().setLocation(350, gate.getOutput().getGateLabel().getY());
			gate.getLabelID().setLocation(300, gate.getLabelID().getY());
		}
	}
	/*
	 * 
	 * Elimina la compuerta
	 * 
	 */
	private void removeGate(Gate gate) {
		
		borrarLabelsCompuerta(gate);
		
		borrarLineasDelPanel(gate);
		
		m1.removeGate(gate);
		
		checkOutputsInputs();
		checkForUnlockDrag();
		actualizarPantalla();
		eraser = false;
		setCursor(Cursor.getDefaultCursor());
		
	}
	
	private void checkForUnlockDrag() {
		for(int i = 0; i < m1.getGateList().size();i++) {
			if(m1.getGateList().get(i).getOutput() != null) {
				if(!m1.getGateList().get(i).getOutput().getGateID().contains("C")){
					for(int e = 0; e < m1.getGateList().get(i).getInputs().size(); e++) {
						if(!m1.getGateList().get(i).getInputs().get(e).getGateID().contains("C")) {
							if(m1.getGateList().get(i).getLines().size()==0) {
								m1.getGateList().get(i).setLocked(false);
							}
						}
					}
				}
			}
		}
	}
	/*
	 * 
	 * 
	 * Recorre la lista de compuertas en busca de una compuerta con menos de 2 entradas o sin salida y se las agrega
	 * 
	 * atrapar excepcion de not
	 * 
	 */
	private void checkOutputsInputs() {
		
		boolean encuentra = false;
		
		for(int i = 0; i < m1.getGateList().size();i++) {
			if(!m1.getGateList().get(i).getType().equals(GateType.NOT) && m1.getGateList().get(i).getInputs().size() < 2) {
				while(m1.getGateList().get(i).getInputs().size() < 2) {
					encuentra = true;
					Gate nuevaEntrada = new Gate(1, GateType.INPUT,m1.getGateList().get(i).getGateID());
					add(nuevaEntrada.getGateLabel());
					nuevaEntrada.getGateLabel().setVisible(true);
					nuevaEntrada.getGateLabel().setLocation(m1.getGateList().get(i).getGateLabel().getX()-28, m1.getGateList().get(i).getGateLabel().getY()+yAnterior+20);
					
					yAnterior+=20;
					if(yAnterior > 40) {
						yAnterior = 0;
					}
					m1.getGateList().get(i).getInputs().add(nuevaEntrada);
					m1.getInputList().add(nuevaEntrada);
				}
			}else if(m1.getGateList().get(i).getType().equals(GateType.NOT) && m1.getGateList().get(i).getInputs().size() == 0) {
				encuentra = true;
				Gate nuevaEntrada = new Gate(1, GateType.INPUT,m1.getGateList().get(i).getGateID());
				add(nuevaEntrada.getGateLabel());
				nuevaEntrada.getGateLabel().setVisible(true);
				nuevaEntrada.getGateLabel().setLocation(m1.getGateList().get(i).getGateLabel().getX()-28, m1.getGateList().get(i).getGateLabel().getY()+34);
				m1.getGateList().get(i).getInputs().add(nuevaEntrada);
				m1.getInputList().add(nuevaEntrada);
			}
			if(m1.getGateList().get(i).getOutput() == null) {
				Gate nuevaSalida = new Gate(1, GateType.OUTPUT,m1.getGateList().get(i).getGateID());
				add(nuevaSalida.getGateLabel());
				nuevaSalida.getGateLabel().setLocation(m1.getGateList().get(i).getGateLabel().getX()+82, m1.getGateList().get(i).getGateLabel().getY()+28);
				m1.getGateList().get(i).setOutput(nuevaSalida);
				nuevaSalida.getGateLabel().setVisible(true);
				m1.getOutputList().add(nuevaSalida);
				encuentra = true;
			}
			if(encuentra) {
				comportamientoEntradas(m1.getGateList().get(i));
				encuentra = false;
			}
			
		}
		
	}

	
	private void borrarLabelsCompuerta(Gate c) {
		remove(c.getGateLabel());
		if(!c.getOutput().getGateID().contains("C")) {
			remove(c.getOutput().getGateLabel());
		}
		remove(c.getLabelID());
		for(int i = 0; i < c.getInputs().size(); i++) {
			if(!c.getInputs().get(i).getGateID().contains("C")) {
				remove(c.getInputs().get(i).getGateLabel());
			}
			
		}
	}
	
	private void unirCompuertas(ArrayList<Gate> compuertas) {
		
		for(int i = 0; i < compuertas.size(); i++) {
			for(int e = 0; e < compuertas.size(); e++) {
				
				for(int a = 0; a < compuertas.get(i).getInputs().size(); a++) {
					if(compuertas.get(i).getInputs().get(a).getX() == compuertas.get(e).getX() && compuertas.get(i).getInputs().get(a).getY() == compuertas.get(e).getY()) {
						compuertas.get(i).getInputs().set(a, compuertas.get(e));
					}
						
				}
				
				if(compuertas.get(i).getOutput().getX() == compuertas.get(e).getX() && compuertas.get(i).getOutput().getY() == compuertas.get(e).getY()) {
					compuertas.get(i).setOutput(compuertas.get(e));
				}
				
			}
		}
		
	}
	
	private void recuperarModelo(String nombre) {
		
		ArrayList<Gate> listaGate = ControlGateXML.readGateXML(nombre);
		
		unirCompuertas(listaGate);
		
		for(int i = 0; i < listaGate.size(); i++) {
			listaGate.get(i).toStringXML();
		}
		
		
		for(int i = 0; i < listaGate.size(); i++) {
			
			Gate nueva = listaGate.get(i);
			
			nueva.setGateID(Gate.getNewGateID());
			
			nueva.setLines(new ArrayList<Line>());
			
			JLabel labelID = new JLabel(nueva.getGateID());
			labelID.setForeground(Color.blue);
			labelID.setSize(40,20);
			nueva.setLabelID(labelID);
			this.add(labelID);
			
			JLabel label = new JLabel();
			label.setText("asdasd");
			label.setSize(80,32);
			label.setLocation(nueva.getX(), nueva.getY());
			ponerImagen(label, listaGate.get(i).getType());
			this.add(label);
			nueva.setGateLabel(label);
			nueva.getGateLabel().setName(nueva.getGateID());
			
			nueva.getLabelID().setLocation(nueva.getGateLabel().getX()+25,nueva.getGateLabel().getY()-19);
			
			nueva.getGateLabel().addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					dragGate(nueva, e);
				}
				
			});
			nueva.getGateLabel().addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(eraser) {
						removeGate(nueva);
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					limitLocation(nueva);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					Gate.toString(nueva);
				}
			});		
			
			
			for(int e = 0; e < nueva.getInputs().size(); e++) {
				if(nueva.getInputs().get(e).getType() == GateType.INPUT ) {
					
					Gate entrada = new Gate(1, GateType.INPUT, nueva.getGateID());
					
					entrada.getGateLabel().setLocation(nueva.getGateLabel().getX()-31, nueva.getGateLabel().getY()-5+(e*20));
					
					entrada.getGateLabel().setName(entrada.getGateID());
					
					nueva.getInputs().set(e, entrada);
					
					this.add(entrada.getGateLabel());
					
					entrada.getGateLabel().setVisible(true);
					
					comprobacionDeLineas(entrada.getGateLabel());
					
					m1.getInputList().add(entrada);				
				}
			}
			
			if(nueva.getOutput().getType() == GateType.OUTPUT) {
				
				Gate salida = new Gate(1, GateType.OUTPUT,nueva.getGateID());
				
				salida.getGateLabel().setLocation(nueva.getGateLabel().getX()+85, nueva.getGateLabel().getY()+5);
				
				salida.getGateLabel().setName(salida.getGateID());
				
				nueva.setOutput(salida);
				
				this.add(salida.getGateLabel());
				
				salida.getGateLabel().setVisible(true);
				
				comprobacionDeLineas(salida.getGateLabel());
				
				m1.getOutputList().add(salida);
				
			}
			
			
			
			this.repaint();
			
			m1.addGate(nueva);
			
			
			
		}
		
		agregarLineasRecuperar(listaGate);
		actualizarPantalla();
	}
	
	private void agregarLineasRecuperar(ArrayList<Gate> gates) {
		
		for(int i = 0; i < gates.size(); i++) {
			
			for(int e = 0; e < gates.get(i).getInputs().size(); e++) {
				if(gates.get(i).getInputs().get(e).getType() != GateType.INPUT ) {
					
					Line line = pintarLinea(gates.get(i).getGateLabel().getX()-100,gates.get(i).getGateLabel().getY(),gates.get(i).getInputs().get(e).getX(),gates.get(i).getInputs().get(e).getY(),gates.get(i),gates.get(i).getInputs().get(e));
					
					if(!gates.get(i).getInputs().get(e).buscaLineaPorTailPeak(line.getTail().getGateID(), line.getPeak().getGateID())) {
						gates.get(i).getInputs().get(e).getLines().add(line);
					}
					if(!gates.get(i).buscaLineaPorTailPeak(line.getTail().getGateID(), line.getPeak().getGateID())) {
						gates.get(i).getLines().add(line);
					}
				}
			}
			
			if(gates.get(i).getOutput().getType() != GateType.OUTPUT) {
				
				Line line = pintarLinea(gates.get(i).getGateLabel().getX(),gates.get(i).getGateLabel().getY(),gates.get(i).getOutput().getX(),gates.get(i).getOutput().getY(),gates.get(i),gates.get(i).getOutput());
				
				if(!gates.get(i).buscaLineaPorTailPeak(line.getTail().getGateID(), line.getPeak().getGateID())) {
					gates.get(i).getLines().add(line);
				}
				
				if(!gates.get(i).getOutput().buscaLineaPorTailPeak(line.getTail().getGateID(), line.getPeak().getGateID())) {
					gates.get(i).getOutput().getLines().add(line);
				}
			}
		}
		
	}
	
	private void ponerImagen(JLabel label, GateType type) {
		
		if(type.equals(GateType.AND)) {
			label.setIcon(lbl_palette_and.getIcon());
		}
		if(type.equals(GateType.NAND)) {
			label.setIcon(lbl_palette_nand.getIcon());
		}
		if(type.equals(GateType.OR)) {
			label.setIcon(lbl_palette_or.getIcon());
		}
		if(type.equals(GateType.NOR)) {
			label.setIcon(lbl_palette_nor.getIcon());
		}
		if(type.equals(GateType.NOT)) {
			label.setIcon(lbl_palette_not.getIcon());
		}
		if(type.equals(GateType.XOR)) {
			label.setIcon(lbl_palette_xor.getIcon());
		}
		if(type.equals(GateType.XNOR)) {
			label.setIcon(lbl_palette_xnor.getIcon());
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		actualizarPantalla();
		setCursor(Cursor.getDefaultCursor());
		eraser = false;
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
