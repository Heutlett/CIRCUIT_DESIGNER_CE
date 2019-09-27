package com.circuitdesigner.structures;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
/**
 * 
 * @author Adrian Araya Ramirez
 *
 * @version 1.8
 *
 */
public class Gate {
	
	public static enum GateType{
		AND, NAND, OR, NOR, NOT, XOR, XNOR, INPUT, OUTPUT
	};
	
	private String gateID ="";
	private static int inQuantity = 1;
	private static int outQuantity = 1;
	private static int gatesQuantity;
	private int value;
	private boolean locked = false;
	private ArrayList<Gate> inputs;
	private Gate output;
	private GateType type;
	private JLabel gateLabel;
	private JLabel labelID;
	private ArrayList<Line> lines;
	private String previusGateOutputID;
	private int cantTrue = 0;
	private int cantFalse = 0;
	private int x;
	private int y;
	
	/**
	 * 
	 * Constructor para compuertas tipo entrada o salida.
	 * 
	 * @param int
	 * @param GateType
	 * @param String
	 */
	public Gate(int value, GateType type, String previusGateOutputID) {
		
		if(type == GateType.OUTPUT) {
			this.gateID = "o<" + outQuantity + ">";
			outQuantity += 1;
			this.value = 2;
			this.previusGateOutputID = previusGateOutputID;
		}
		if(type ==GateType.INPUT) {
			this.gateID = "i<" + inQuantity + ">";
			inQuantity += 1;
			this.value = 1;
		}
		
		this.type = type;
		this.gateLabel = new JLabel();
		this.gateLabel.setText(gateID);
		this.gateLabel.setSize(40, 20);
		this.gateLabel.setVisible(false);
		this.gateLabel.setName(gateID);
	}
	
	/**
	 * 
	 * Contructor para compuertas generales: AND, NAND, OR, NOR, NOT, XOR, XNOR.
	 * 
	 * @param GateType
	 */
	public Gate(GateType type) {
		
		this.lines = new ArrayList<Line>();
		this.gateID = getNewGateID();
		this.labelID = new JLabel(this.gateID);
		this.labelID.setForeground(Color.blue);
		this.labelID.setSize(40,20);
		this.labelID.setVisible(false);
		this.type = type;
		this.gateLabel = new JLabel();
		inputs = new ArrayList<Gate>();
		if(type != GateType.NOT) {
			inputs.add(new Gate(1, GateType.INPUT, this.gateID));
			inputs.add(new Gate(1,GateType.INPUT, this.gateID));
		}else {
			inputs.add(new Gate(1,GateType.INPUT,this.gateID));
		}
		output = new Gate(1, GateType.OUTPUT,this.gateID);
		this.gateLabel.setName(gateID);
		this.value = 3;
	}
	/**
	 * 
	 * Constructor para compuertas recuperadas de XML.
	 * 
	 * @param int
	 * @param boolean
	 * @param GateType
	 * @param int
	 * @param int
	 */
	public Gate(int value, boolean locked, GateType type, int x, int y) {
		
		this.value = value;
		this.locked = locked;
		this.type = type;
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * 
	 * Si la compuerta esta bloqueada devuelve true, y false de lo contrario.
	 * 
	 * @return boolean
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * 
	 * Bloquea la compuerta para que esta no se pueda arrastrar con el mouse, este bloqueo se aplica cuando la compuerta tiene
	 * enlaces a otras compuertas para evitar problemas con las lineas (si se borran sus conexiones, se vuelve a desbloquear).
	 * 
	 * @param boolean
	 */
	public void setLocked(boolean bloqueada) {
		this.locked = bloqueada;
	}

	/**
	 * 
	 * Devuelve una lista que guarda las entradas de la compuerta.
	 * 
	 * @return ArrayList<Gate>
	 */
	public ArrayList<Gate> getInputs() {
		return inputs;
	}

	/**
	 * 
	 * Sustituye la lista de entradas por la lista de entradas pasada por parametro.
	 * 
	 * @param ArrayList<Gate>
	 */
	public void setInputs(ArrayList<Gate> entradas) {
		this.inputs = entradas;
	}

	/**
	 * 
	 * Devuelve la salida de la compuerta.
	 * 
	 * @return Gate
	 */
	public Gate getOutput() {
		return output;
	}

	/**
	 * 
	 * Asigna una salida a la compuerta.
	 * 
	 * @param Gate
	 */
	public void setOutput(Gate salida) {
		this.output = salida;
	}

	/**
	 * 
	 * Devuelve el tipo de la compuerta (AND, NAND, OR, NOR, NOT, XOR, XNOR, INPUT, OUTPUT).
	 * 
	 * @return GateType
	 */
	public GateType getType() {
		return type;
	}

	/**
	 * 
	 * Asigna el tipo de la compuerta (AND, NAND, OR, NOR, NOT, XOR, XNOR, INPUT, OUTPUT).
	 * 
	 * @param GateType
	 */
	public void setType(GateType tipo) {
		this.type = tipo;
	}

	/**
	 * 
	 * Devuelve el JLabel que se encarga de mostrar la imagen de la compuerta.
	 * 
	 * @return JLabel
	 */
	public JLabel getGateLabel() {
		return gateLabel;
	}

	/**
	 * 
	 * Asigna el JLabel que se encarga de mostrar la imagen de la compuerta.
	 * 
	 * @param JLabel
	 */
	public void setGateLabel(JLabel labelCompuerta) {
		this.gateLabel = labelCompuerta;
	}

	/**
	 * 
	 * Devuelve la cantidad de entradas que existen en el programa.
	 * 
	 * @return int
	 */
	public static int getInQuantity() {
		return inQuantity;
	}

	/**
	 * 
	 * Asigna la cantidad de proposiones totales de programa.
	 * 
	 * @param int
	 */
	public static void setInQuantity(int cantProposicionesIn) {
		Gate.inQuantity = cantProposicionesIn;
	}

	/**
	 * 
	 * Devuelve la cantidad de salidas que existen en el programa.
	 * 
	 * @return int
	 */
	public static int getOutQuantity() {
		return outQuantity;
	}

	/**
	 * 
	 * Asigna la cantidad de salidas que existen en el programa.
	 * 
	 * @param int
	 */
	public static void setOutQuantity(int cantProposicionesOut) {
		Gate.outQuantity = cantProposicionesOut;
	}

	/**
	 * 
	 * Devuelve el valor de la compuerta (0,1).
	 * 
	 * @return int
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * Asigna el valor de la compuerta.
	 * 
	 * @param int
	 */
	public void setValue(int valorEntrada) {
		this.value = valorEntrada;
	}
	
	/**
	 * 
	 * Devuelve la cantidad total de compuertas que existen en el programa.
	 * 
	 * @return int
	 */
	public static int getGatesQuantity() {
		return gatesQuantity;
	}
	
	/**
	 * 
	 * Asigna la cantidad total de compuertas que existen en el programa.
	 * 
	 * @param int
	 */
	public static void setGatesQuantity(int cantCompuertas) {
		Gate.gatesQuantity = cantCompuertas;
	}

	/**
	 * 
	 * Devuelve el ID de la compuerta (i<n>, o<n>, C<n>).
	 * 
	 * @return String
	 */
	public String getGateID() {
		return gateID;
	}

	/**
	 * 
	 * Asigna el ID de la compuerta (i<n>, o<n>, C<n>).
	 * 
	 * @param String
	 */
	public void setGateID(String idCompuerta) {
		this.gateID = idCompuerta;
	}

	/**
	 * 
	 * Devuelve el JLabel que permite que se vea el identificador de la compuerta (i<n>, o<n>, C<n>).
	 * 
	 * @return JLabel
	 */
	public JLabel getLabelID() {
		return labelID;
	}

	/**
	 * 
	 * Asigna el JLabel que permite que se vea el identificador de la compuerta (i<n>, o<n>, C<n>).
	 * 
	 * @param JLabel 
	 */
	public void setLabelID(JLabel labelId) {
		this.labelID = labelId;
	}

	/**
	 * 
	 * Devuelve la lista que almacena las lineas que posee esta compuerta.
	 * 
	 * @return ArrayList<Line>
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}

	/**
	 * 
	 * Asigna la lista que almacena las lineas que posee la compuerta.
	 * 
	 * @param ArrayList<Line>
	 */
	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}

	/**
	 * 
	 * Devuelve el ID de la compuerta padre, en caso de ser una compuerta hija.
	 * 
	 * @return String
	 */
	public String getPreviusGateOutputID() {
		return previusGateOutputID;
	}

	/**
	 * 
	 * Asigna el ID de la compuerta padre, en caso de ser una compuerta hija.
	 * 
	 * @param String
	 */
	public void setPreviusGateOutputID(String previusGateOutputID) {
		this.previusGateOutputID = previusGateOutputID;
	}
	
	/**
	 * 
	 * Devuelve la cantidad de trues que tiene la compuerta en sus entradas para realizar el calculo de su salida.
	 * 
	 * @return int
	 */
	public int getCantTrue() {
		return cantTrue;
	}
	
	/**
	 * 
	 * Asigna la cantidad de trues que tiene la compuerta en sus entradas para realizar el calculo de su salida.
	 * 
	 * @param int
	 */
	public void setCantTrue(int cantTrue) {
		this.cantTrue = cantTrue;
	}

	/**
	 * 
	 * Devuelve la cantidad de falses que tiene la compuerta en sus entradas para realizar el calculo de su salida.
	 * 
	 * @return int
	 */
	public int getCantFalse() {
		return cantFalse;
	}
	
	/**
	 * 
	 * Asigna la cantidad de falses que tiene la compuerta en sus entradas para realizar el calculo de su salida.
	 * 
	 * @param int
	 */
	public void setCantFalse(int cantFalse) {
		this.cantFalse = cantFalse;
	}
	
	/**
	 * 
	 * Devuelve la coordenada X donde se encuentra ubicada la compuerta en el panel.
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * Asigna la coordenada X donde se encuentra ubicada la compuerta en el panel.
	 * 
	 * @param int
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * Devuelve la coordenada Y donde se encuentra ubicada la compuerta en el panel.
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * Asigna la coordenada Y donde se encuentra ubicada la compuerta en el panel.
	 * 
	 * @param int
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * Devuelve un ID unico de compuerta general (AND, NAND, OR, NOR, NOT, XOR, XNOR).
	 * 
	 * @return String
	 */
	public static String getNewGateID() {
		
		String id = "C" + gatesQuantity;
		gatesQuantity++;
		return id;
	}
	
	/**
	 * 
	 * Busca una entrada por ID en la lista de entradas de la compuerta y la devuelve en caso de encontrarla.
	 * 
	 * @param String
	 * @return Gate
	 */
	public Gate findInput(String pInputName) {
		Gate gate = null;
		for(int j = 0; j < getInputs().size(); j++) {
			if(getInputs().get(j).getGateLabel().getName().equals(pInputName)) {
				return getInputs().get(j);
			}
		}
		return gate;
	}
	
	/**
	 * 
	 * Ubica los labels de la compuerta en el panel.
	 * 
	 */
	public void setInputOutputLocations() {
		
		int y = this.getGateLabel().getY() - 20;
		int x = this.getGateLabel().getX();
		
		this.getOutput().getGateLabel().setLocation(x+90,y+20);
		this.getLabelID().setLocation(x+40, y-20);
		for(int i = 0; i < this.getInputs().size(); i++) {
			
			this.getInputs().get(i).getGateLabel().setLocation(x,y);
			y += 30;	
		}
		
	}
	
	/**
	 * 
	 * Hace el calculo de la salida de la compuerta por medio de un metodo recursivo.
	 * 
	 * @return int
	 */
	public int calculate() {
		
		cantTrue = 0;
		cantFalse = 0;
		
		for(int i = 0; i < this.inputs.size();i++) {
			
			if(this.inputs.get(i).getValue() == 3) {
				
				if(this.type == GateType.AND) {
					if(inputs.get(i).calculate() == 0) {
						return 0;
					}
				}
				if(this.type == GateType.NAND) {
					if(inputs.get(i).calculate() == 0) {
						return 1;
					}
				}
				if(this.type == GateType.OR) {
					if(inputs.get(i).calculate() == 1) {
						return 1;
					}
				}
				if(this.type == GateType.NOR) {
					if(inputs.get(i).calculate() == 1) {
						return 0;
					}
				}
				if(this.type == GateType.NOT) {
					if(inputs.get(i).calculate() == 1) {
						return 0;
					}else {
						return 1;
					}
				}
				if(this.type == GateType.XOR) {
					if(inputs.get(i).calculate() == 1) {
						cantTrue++;
					}
				}
				if(this.type == GateType.XNOR) {
					if(inputs.get(i).calculate() == 0) {
						cantFalse++;
					}
				}
			}
			else {
				
				if(this.type == GateType.AND) {
					if(this.inputs.get(i).getValue() == 0) {
						return 0;
					}
				}
				
				if(this.type == GateType.NAND) {
					if(this.inputs.get(i).getValue() == 0) {
						return 1;
					}
				}
				if(this.type == GateType.OR) {
					if(this.inputs.get(i).getValue() == 1) {
						return 1;
					}
				}
				if(this.type == GateType.NOR) {
					if(this.inputs.get(i).getValue() == 1) {
						return 0;
					}
				}
				if(this.type == GateType.NOT) {
					if(this.inputs.get(i).getValue() == 1) {
						return 0;
					}else {
						return 1;
					}
				}
				if(this.type == GateType.XOR) {
					if(this.inputs.get(i).getValue() == 1) {
						cantTrue++;
					}
				}
				if(this.type == GateType.XNOR) {
					if(this.inputs.get(i).getValue() == 0) {
						cantFalse++;
					}
				}
			}
			
		}
		
		if(this.type == GateType.AND) {
			return 1;
		}
		if(this.type == GateType.NAND) {
			return 0;
		}
		if(this.type == GateType.OR) {
			return 0;
		}
		if(this.type == GateType.NOR) {
			return 1;
		}
		if(this.type == GateType.XOR) {
			if(cantTrue==1) {
				return 1;
			}else {
				return 0;
			}
		}
		if(this.type == GateType.XNOR) {
			if(cantFalse==1) {
				return 0;
			}else {
				return 1;
			}
		}
		return 9;
		
	}
	
	/**
	 * 
	 * Disminuye el valor de los contadores, se utiliza cuando se borra una compuerta.
	 * 
	 */
	public static void decreaseQuantity() {
		inQuantity-=2;
		outQuantity--;
		gatesQuantity--;
	}
	
	/**
	 * 
	 * Imprime en consola la informacion de la compuerta.
	 * 
	 * @param Gate
	 */
	public static void toString(Gate pGate) {
		System.out.println();
		System.out.println("Mostrando los datos de la compuerta: " + pGate.getGateID());
		for(int i = 0; i < pGate.getInputs().size(); i++) {
			if(pGate.getInputs().get(i).getType() == Gate.GateType.INPUT || pGate.getInputs().get(i).getType() == Gate.GateType.OUTPUT) {
				System.out.println("Entrada " + i + ": " + pGate.getInputs().get(i).getGateID());
			}else {
				System.out.println("Entrada " + i + ": " + pGate.getInputs().get(i).getGateID());
			}
			
		}
		if(pGate.getOutput()!= null) {
			if(pGate.getOutput().getType() == Gate.GateType.INPUT || pGate.getOutput().getType() == Gate.GateType.OUTPUT) {
				
				System.out.println("Salida: " + pGate.getOutput().getGateID());
				
			}else {
				System.out.println("Salida: " + pGate.getOutput().getGateID());
			}
		}else {
			System.out.println("Salida: null");
		}
		System.out.println("Cantidad de lineas: " + pGate.getLines().size());
		System.out.println();
	}
	
	/**
	 * Imprime en consola la informacion de la compuerta importada de XML.
	 */
	public void toStringXML() {
		
		System.out.println("GATE");
		System.out.println("VALUE: " + this.value);
		System.out.println("LOCKED: " + this.locked);
		System.out.println("TYPE: " + this.type.toString());
		System.out.println("X: : " + this.x );
		System.out.println("Y: " + this.y);
		System.out.println("INPUTS");
		for(int i = 0; i < inputs.size(); i++) {
			System.out.println("INPUT");
			System.out.println("VALUE: " + inputs.get(i).getValue());
			System.out.println("LOCKED: " + inputs.get(i).isLocked());
			System.out.println("TYPE: " + inputs.get(i).getType().toString());
			System.out.println("X: : " + inputs.get(i).getX());
			System.out.println("Y: " + inputs.get(i).getY());
		}
		System.out.println("OUTPUT");
		System.out.println("VALUE: " + output.getValue());
		System.out.println("LOCKED: " + output.isLocked());
		System.out.println("TYPE: " + output.getType().toString());
		System.out.println("X: : " + output.getX());
		System.out.println("Y: " + output.getY());
	}
	
	/**
	 * 
	 * Busca si la compuerta posee una linea que tenga el tail y el peak igual al de los parametros y devuelve true si la encuentra y 
	 * false de lo contrario.
	 * 
	 * @param String
	 * @param String
	 * @return boolean
	 */
	public boolean buscaLineaPorTailPeak(String tailID, String peakID) {
		
		for(int i = 0; i < lines.size(); i++) {
			if((lines.get(i).getTail().getGateID().equals(tailID) && lines.get(i).getPeak().getGateID().equals(peakID)) || ((lines.get(i).getTail().getGateID().equals(peakID) && lines.get(i).getPeak().getGateID().equals(tailID)))) {
				
				return true;
				
			}
		}
		
		return false;
		
	}
	
}
