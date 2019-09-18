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
	
	public Gate(GateType type, JLabel gateLabel) {
		
		this.lines = new ArrayList<Line>();
		this.gateID = "C" + this.gatesQuantity;
		this.labelID = new JLabel(this.gateID);
		this.labelID.setForeground(Color.blue);
		this.labelID.setSize(40,20);
		this.labelID.setVisible(false);
		gatesQuantity++;
		this.type = type;
		this.gateLabel = gateLabel;
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
		if(pGate.getOutput().getType() == Gate.GateType.INPUT || pGate.getOutput().getType() == Gate.GateType.OUTPUT) {
			
			System.out.println("Salida: " + pGate.getOutput().getGateID());
			
		}else {
			System.out.println("Salida: " + pGate.getOutput().getGateID());
		}
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
	
	private boolean sonInputs() {
		for(int i = 0; i < inputs.size(); i++) {
			if(inputs.get(i).getValue() == 3) {
				return false;
			}
		}
		return true;
	}
	
	public int calculate() {
		
		
		for(int i = 0; i < this.inputs.size();i++) {

			
			if(this.inputs.get(i).getValue() == 3) {
				
				if(inputs.get(i).calculate() == 0) {
					return 0;
				}
				
			}
			else if(this.inputs.get(i).getValue() == 0) {
				return 0;
			}
			
		}

		return 1;

		
		
	}
	

}
