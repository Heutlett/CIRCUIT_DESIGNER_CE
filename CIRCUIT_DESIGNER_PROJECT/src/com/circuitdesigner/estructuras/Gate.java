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
	private String propID ="";
	private static int inQuantity = 1;
	private static int outQuantity = 1;
	private static int gatesQuantity;
	private int inValue;
	private int outValue;
	private boolean locked = false;
	private ArrayList<Gate> inputs;
	private Gate outputs;
	private GateType type;
	private JLabel gateLabel;
	private JLabel labelID;

	
	//Constructor para entradas y salidas
	public Gate(int inValue, GateType type) {
		
		if(type == GateType.OUTPUT) {
			this.propID = "o<" + outQuantity + ">";
			outQuantity += 1;
		}
		if(type ==GateType.INPUT) {
			this.propID = "i<" + inQuantity + ">";
			inQuantity += 1;
		}
		
		this.inValue = inValue;
		this.type = type;
		this.gateLabel = new JLabel();
		this.gateLabel.setText(propID);
		this.gateLabel.setSize(40, 20);
		this.gateLabel.setVisible(false);
		this.gateLabel.setName(propID);
	}
	
	public Gate(GateType type, JLabel gateLabel) {
		
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
			inputs.add(new Gate(1, GateType.INPUT));
			inputs.add(new Gate(1,GateType.INPUT));
		}else {
			inputs.add(new Gate(1,GateType.INPUT));
		}
		outputs = new Gate(1, GateType.OUTPUT);
		this.gateLabel.setName(gateID);
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
				System.out.println("Entrada " + i + ": " + pGate.getInputs().get(i).getPropID());
			}else {
				System.out.println("Entrada " + i + ": " + pGate.getInputs().get(i).getGateID());
			}
			
		}
		if(pGate.getOutputs().getType() == Gate.GateType.INPUT || pGate.getOutputs().getType() == Gate.GateType.OUTPUT) {
			
			System.out.println("Salida: " + pGate.getOutputs().getPropID());
			
		}else {
			System.out.println("Salida: " + pGate.getOutputs().getGateID());
		}
		System.out.println();
	}
	
	public void setInputOutputLocations() {
		
		int y = this.getGateLabel().getY() - 20;
		int x = this.getGateLabel().getX();
		
		this.getOutputs().getGateLabel().setLocation(x+90,y+20);
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

	public Gate getOutputs() {
		return outputs;
	}

	public void setOutputs(Gate salida) {
		this.outputs = salida;
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

	public int getInValue() {
		return inValue;
	}

	public void setInValue(int valorEntrada) {
		this.inValue = valorEntrada;
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

	public String getPropID() {
		return propID;
	}

	public void setPropID(String idProposicion) {
		this.propID = idProposicion;
	}

	public JLabel getLabelID() {
		return labelID;
	}

	public void setLabelID(JLabel labelId) {
		this.labelID = labelId;
	}
	
	public int getOutValue() {
		return outValue;
	}

	public void setOutValue(int outValue) {
		this.outValue = outValue;
	}
	
	

}
