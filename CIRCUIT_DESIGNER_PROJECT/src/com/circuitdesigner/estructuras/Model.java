package com.circuitdesigner.estructuras;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.circuitdesigner.view.Table;

public class Model {
	
	private static final Model INSTANCE = new Model();
	
	private ArrayList<Gate> gateList;
	private ArrayList<Line> lineList;
	private ArrayList<Gate> inputList;
	private ArrayList<Gate> outputList;

	private Model() {
		
		gateList = new ArrayList<Gate>();
		lineList = new ArrayList<Line>();
		inputList = new ArrayList<Gate>();
		outputList = new ArrayList<Gate>();
		
	}
	
	private String[] generateColumnNames() {
		
		String [] columnNames = new String[inputList.size()+1];
		
		for(int i = 0; i < inputList.size(); i++) {
			columnNames[i] = inputList.get(i).getPropID();
		}
		columnNames[inputList.size()] = outputList.get(0).getPropID();
		return columnNames;
	}
	
	private void calculate(int [][] table, Object [][] result) {
	
		int truesQuantity = 0;
		int falsesQuantity = 0;
		
		for(int i = 0; i < (int)Math.pow(2, inputList.size()); i++) {
			
			
			for(int e = 0; e < inputList.size(); e++) {
				
				inputList.get(e).setInValue(table[i][e]);
				
			}
			//si es and
			for(int e = 0; e < inputList.size(); e++) {
				
				if(inputList.get(e).getInValue() == 0) {
					falsesQuantity++;
				}
				
			}
			if(falsesQuantity > 0) {
				falsesQuantity = 0;
				result[i][inputList.size()] = 0;
			}else {
				falsesQuantity = 0;
				result[i][inputList.size()] = 1;
			}
		}
		
	}
	
	public void showTable() {
		
		if(inputList.size()<1) {
			JOptionPane.showMessageDialog(null, "No se puede ejecutar si se encuentra vacio!!!");
			return;
		}
		
		if(outputList.size()>1) {
			JOptionPane.showMessageDialog(null, "Solo puede existir una salida!!!");
			return;
		}
		
		int [][] table = generateTable(inputList.size());
		
		String [] columnNames = generateColumnNames();
		
		Object [][] rowData = new Object [(int)Math.pow(2, inputList.size())][inputList.size()+1];
		
		for(int i = 0; i < (int)Math.pow(2, inputList.size()); i++) {
			for(int e = 0; e < inputList.size(); e++) {
				rowData[i][e] = table[i][e];
			}
		}

		calculate(table, rowData);
		
		Table t = new Table(rowData, columnNames);
		t.setVisible(true);
		
	}
	
	public void removeInput(String inputName) {
		for(int i = 0; i < inputList.size();i++) {
			if(inputList.get(i).getPropID().equals(inputName)){
				inputList.remove(i);
			}
		}
	}
	
	public void removeOutput(String outputName) {
		for(int i = 0; i < outputList.size();i++) {
			if(outputList.get(i).getPropID().equals(outputName)){
				outputList.remove(i);
			}
		}
	}
	
	private void removeInsOutsGate(Gate gate) {
		removeOutput(gate.getOutputs().getPropID());
		for(int i = 0; i < gate.getInputs().size(); i++) {
			removeInput(gate.getInputs().get(i).getPropID());
		}
	}
	
	public Gate findGateByOutput(String output) {
		
		Gate gate = null;
		
		for(int i = 0; i < gateList.size(); i++) {
			
			if(gateList.get(i).getOutputs().getPropID().equals(output)){
				return gateList.get(i);
			}
		}
		return gate;
	}
	
	public Gate findGateByInput(String input) {
		
		Gate gate = null;
		
		for(int i = 0; i < gateList.size(); i++) {
			
			for(int e = 0; e < gateList.get(i).getInputs().size(); e++) {
				
				if(gateList.get(i).getInputs().get(e).getPropID().equals(input)){
					return gateList.get(i);
				}
			}
		}
		return gate;
	}

	public void addInputsOutputs(Gate gate) {
		
		for(int i = 0; i < gate.getInputs().size(); i++) {
			inputList.add(gate.getInputs().get(i));
		}
		outputList.add(gate.getOutputs());
		
	}
	
	public void printInputs() {
		System.out.print("Ins: ");
		for(int i = 0; i < inputList.size();i++) {
			
			System.out.print(inputList.get(i).getPropID() + " ");
			
		}
		System.out.println();
	}
	
	public void printOutputs() {
		System.out.print("Outs: ");
		for(int i = 0; i < outputList.size();i++) {
			
			System.out.print(outputList.get(i).getPropID() + " ");
			
		}
		System.out.println();
		System.out.println();
	}
	
    public ArrayList<Gate> getInputList() {
		return inputList;
	}

	public void setInputList(ArrayList<Gate> inputList) {
		this.inputList = inputList;
	}

	public ArrayList<Gate> getOutputList() {
		return outputList;
	}

	public void setOutputList(ArrayList<Gate> outputList) {
		this.outputList = outputList;
	}

	public static Model getInstance() {
        return INSTANCE;
    }
    
	public void addCompuertas(Gate c) {
		gateList.add(c);
	}
	
	public static void printTable(int n, int [][]finalTable) {
		
		for(int  i =0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				System.out.print(finalTable[i][e] + " ");
			}
			System.out.println();
		}
		
	}
	
	public void removeGate(Gate gate) {
		removeInsOutsGate(gate);
		gateList.remove(gate);
		printInputs();
		printOutputs();
	}
	
	public static int[][] generateTable(int n) {
		
		int number = 1;
		int [][] table = new int[n][(int) Math.pow(2, n)];
		int [][] finalTable = new int[(int) Math.pow(2, n)][n];
		
		for(int i = 0; i < n; i++) {
			
			int counter = 0;
			
			for(int e = 0; e < (int) Math.pow(2, n); e++) {
				
				int trueOrFalse = (int) Math.pow(2, n-i)/2;
				
				if(counter == trueOrFalse) {
					if(number == 1) {
						number = 0;
					}else {
						number = 1;
					}
					counter = 0;
				}
				counter++;
				table[i][e] = number;
			}
		}
		
		for(int i = 0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				finalTable[i][e] = table[e][i];
			}
			
		}

		return finalTable;
	}
	
	public ArrayList<Gate> getGateList() {
		return gateList;
	}

	public void setGateList(ArrayList<Gate> gateList) {
		this.gateList = gateList;
	}

	public ArrayList<Line> getLineList() {
		return lineList;
	}

	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
	}


}
