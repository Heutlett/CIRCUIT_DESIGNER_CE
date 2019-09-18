package com.circuitdesigner.estructuras;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

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
	
	//Constructor para entradas y salidas
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
			this.value = value;
		}
		
		this.type = type;
		this.gateLabel = new JLabel();
		this.gateLabel.setText(gateID);
		this.gateLabel.setSize(40, 20);
		this.gateLabel.setVisible(false);
		this.gateLabel.setName(gateID);
	}
	
	public Gate(GateType type) {
		
		this.lines = new ArrayList<Line>();
		this.gateID = getNewGateID();
		this.labelID = new JLabel(this.gateID);
		this.labelID.setForeground(Color.blue);
		this.labelID.setSize(40,20);
		this.labelID.setVisible(false);
		this.type = type;
		//this.gateLabel = gateLabel;
		this.gateLabel = new JLabel();
		inputs = new ArrayList<Gate>();
		if(type != GateType.NOT) {
			inputs.add(new Gate(1, GateType.INPUT,this.gateID));
			inputs.add(new Gate(1,GateType.INPUT,this.gateID));
		}else {
			inputs.add(new Gate(1,GateType.INPUT,this.gateID));
		}
		output = new Gate(1, GateType.OUTPUT,this.gateID);
		this.gateLabel.setName(gateID);
		this.value = 3;
	}
	
	public String getNewGateID() {
		
		String id = "C" + this.gatesQuantity;
		gatesQuantity++;
		return id;
	}
	
	public Gate findInput(String pInputName) {
		Gate gate = null;
		for(int j = 0; j < getInputs().size(); j++) {
			if(getInputs().get(j).getGateLabel().getName().equals(pInputName)) {
				return getInputs().get(j);
			}
		}
		return gate;
	}
	
	public static void printGatesInformation(Gate pGate) {
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

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean bloqueada) {
		this.locked = bloqueada;
	}

	public ArrayList<Gate> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<Gate> entradas) {
		this.inputs = entradas;
	}

	public Gate getOutput() {
		return output;
	}

	public void setOutput(Gate salida) {
		this.output = salida;
	}

	public GateType getType() {
		return type;
	}

	public void setType(GateType tipo) {
		this.type = tipo;
	}

	public JLabel getGateLabel() {
		return gateLabel;
	}

	public void setGateLabel(JLabel labelCompuerta) {
		this.gateLabel = labelCompuerta;
	}

	public static int getInQuantity() {
		return inQuantity;
	}

	public static void setInQuantity(int cantProposicionesIn) {
		Gate.inQuantity = cantProposicionesIn;
	}

	public static int getOutQuantity() {
		return outQuantity;
	}

	public static void setOutQuantity(int cantProposicionesOut) {
		Gate.outQuantity = cantProposicionesOut;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int valorEntrada) {
		this.value = valorEntrada;
	}


	public static int getGatesQuantity() {
		return gatesQuantity;
	}

	public static void setGatesQuantity(int cantCompuertas) {
		Gate.gatesQuantity = cantCompuertas;
	}

	public String getGateID() {
		return gateID;
	}

	public void setGateID(String idCompuerta) {
		this.gateID = idCompuerta;
	}

	public JLabel getLabelID() {
		return labelID;
	}

	public void setLabelID(JLabel labelId) {
		this.labelID = labelId;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}

	public String getPreviusGateOutputID() {
		return previusGateOutputID;
	}

	public void setPreviusGateOutputID(String previusGateOutputID) {
		this.previusGateOutputID = previusGateOutputID;
	}
	
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
	public static void decreaseQuantity() {
		inQuantity-=2;
		outQuantity--;
		gatesQuantity--;
	}
	

}
