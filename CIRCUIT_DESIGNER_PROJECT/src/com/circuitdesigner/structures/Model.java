package com.circuitdesigner.structures;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.circuitdesigner.view.Table;

public class Model {
	
	private static final Model INSTANCE = new Model();
	
	private GateLinkedList gateList;
	private ArrayList<Line> lineList;
	private ArrayList<Gate> inputList;
	private ArrayList<Gate> outputList;
	private Gate outputGate;

	/**
	 * 
	 */
	private Model() {
		
		gateList = new GateLinkedList();
		lineList = new ArrayList<Line>();
		inputList = new ArrayList<Gate>();
		outputList = new ArrayList<Gate>();
		
	}
	
	/**
	 * @return
	 */
	private String[] generateColumnNames() {
		
		
		String [] columnNames = new String[inputList.size()+outputList.size()];
		
		System.out.println("Cantidad de columnas: " + (inputList.size()+outputList.size()));
		
		for(int i = 0; i < inputList.size(); i++) {
			columnNames[i] = inputList.get(i).getGateID();
		}
		for(int i = 0; i < outputList.size(); i++) {
			columnNames[inputList.size()+i] = outputList.get(i).getGateID();
		}
		for(int i = 0; i < inputList.size()+outputList.size(); i++){
			System.out.print(columnNames[i] +" ");
		}

		return columnNames;
	}
	
	/**
	 * @param resultTable
	 * @param indice
	 */
	private void calculate(Object [][] resultTable, int indice) {
		
		int resultado;

		for(int i = 0; i < outputList.size(); i++) {
			
			outputGate = gateList.getById(outputList.get(i).getPreviusGateOutputID());
			resultado = outputGate.calculate();
			resultTable[indice][inputList.size()+i] = resultado;
			
		}
	}
	
	/**
	 * @param table
	 * @param resultTable
	 */
	private void calculateEachIteration(int [][] table, Object [][] resultTable) {
		
		for(int i = 0; i < (int)Math.pow(2, inputList.size()); i++) {
			
			
			for(int e = 0; e < inputList.size(); e++) {
				
				inputList.get(e).setValue(table[i][e]);
				System.out.println(inputList.get(e).getGateID() + " = " + inputList.get(e).getValue());
				
			}
			
			calculate(resultTable, i);
		}
		
	}
	
	/**
	 * @param insertValues
	 */
	public void showTable(boolean insertValues) {
		
		if(inputList.size()<1) {
			JOptionPane.showMessageDialog(null, "No se puede ejecutar si se encuentra vacio!!!");
			return;
		}
		int [][] table = generateTable(inputList.size());
		
		int [] valueTable = new int[inputList.size()];
		
		int n = 0;
		
		boolean iguales = true;
		
		if(insertValues) {
			for(int i = 0; i < inputList.size(); i++) {
				valueTable[i] = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese el valor de: " + inputList.get(i).getGateID()+ "    (Entre 0 y 1)"));
				if(valueTable[i] != 1 && valueTable[i] != 0) {
					JOptionPane.showMessageDialog(null, "El valor ingresado es incorrecto, debe ser 0 o 1");
					i--;
				}
			}
		}
		
		for(int i = 0; i < (int) Math.pow(2, inputList.size()); i++) {
			for(int e = 0; e < inputList.size(); e++) {
				
				if(table[i][e] != valueTable[e]) {
					iguales = false;
				}
				
			}
			if(iguales) {
				n = i;
			}else {
				iguales = true;
			}
			System.out.println();
		}
		
		String [] columnNames = generateColumnNames();
		
		Object [][] rowData = new Object [(int)Math.pow(2, inputList.size())][inputList.size()+outputList.size()];
		
		for(int i = 0; i < (int)Math.pow(2, inputList.size()); i++) {
			for(int e = 0; e < inputList.size(); e++) {
				rowData[i][e] = table[i][e];
			}
		}
		
		

		calculateEachIteration(table, rowData);
		
		Table t = new Table(rowData, columnNames);
		
		t.setVisible(true);
		
		if(insertValues) {
			t.seleccionarFila(n);
		}
		
	}
	

	
	/**
	 * @param n
	 * @return
	 */
	public int[][] generateTable(int n) {
		
		int number = 1;
		int [][] table = new int[n][(int) Math.pow(2, n)];
		int [][] finalTable = new int[(int) Math.pow(2, n)][n];
		
		for(int i = 0; i < n; i++) {
			
			int counter = 0;
			
			for(int e = 0; e < (int) Math.pow(2, n); e++) {
				
				int repetitions = (int) Math.pow(2, n-i)/2;
				
				if(counter == repetitions) {
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
	
	/*
	 * 
	 */
	/**
	 * @param gate
	 */
	private void removeInsOutsGate(Gate gate) {
		
		outputList.remove(gate.getOutput());
		
		for(int i = 0; i < gate.getInputs().size(); i++) {
			inputList.remove(gate.getInputs().get(i));
		}
		removeInsOutsSons(gate);
	}
	/*
	 * 
	 * elimina los inputs y outputs a los que estaba conectado el gate que se borrara
	 * 
	 */
	/**
	 * @param gate
	 */
	public void removeInsOutsSons(Gate gate) {
		
		if(gate.getOutput() != null) {
			if(gate.getOutput().getGateID().contains("C")) {
				gate.getOutput().getInputs().remove(gate);
				System.out.println("Soy " + gate.getOutput().getGateID() + " y borre mi entrada : " + gate.getGateID());
			}
			
		}

		for(int i = 0; i < gate.getInputs().size(); i++) {
			if(gate.getInputs().get(i).getGateID().contains("C")) {
				if(gate.getInputs().get(i).getOutput().equals(gate)) {
					gate.getInputs().get(i).setOutput(null);
				}
			}
		}
		
	}
	
	/**
	 * @param output
	 * @return
	 */
	public Gate findGateByOutput(String output) {
		
		Gate gate = null;
		
		for(int i = 0; i < gateList.size(); i++) {
			
			if(gateList.get(i).getOutput().getGateID().equals(output)){
				return gateList.get(i);
			}
		}
		return gate;
	}
	
	/**
	 * @param input
	 * @return
	 */
	public Gate findGateByInput(String input) {
		
		Gate gate = null;
		
		for(int i = 0; i < gateList.size(); i++) {
			
			for(int e = 0; e < gateList.get(i).getInputs().size(); e++) {
				
				if(gateList.get(i).getInputs().get(e).getGateID().equals(input)){
					return gateList.get(i);
				}
			}
		}
		return gate;
	}

	/**
	 * @param gate
	 */
	public void addInputsOutputs(Gate gate) {
		
		for(int i = 0; i < gate.getInputs().size(); i++) {
			inputList.add(gate.getInputs().get(i));
		}
		outputList.add(gate.getOutput());
		
	}
	
	/**
	 * 
	 */
	public void printInputs() {
		System.out.print("Ins: ");
		for(int i = 0; i < inputList.size();i++) {
			
			System.out.print(inputList.get(i).getGateID() + " ");
			
		}
		System.out.println();
	}
	
	/**
	 * 
	 */
	public void printOutputs() {
		System.out.print("Outs: ");
		for(int i = 0; i < outputList.size();i++) {
			
			System.out.print(outputList.get(i).getGateID() + " ");
			
		}
		System.out.println();
		System.out.println();
	}
	
    
    
	/**
	 * @param c
	 */
	/**
	 * @param c
	 */
	public void addGate(Gate c) {
		gateList.add(c);
	}
	
	/**
	 * @param n
	 * @param finalTable
	 */
	public static void printTable(int n, int [][]finalTable) {
		
		for(int  i =0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				System.out.print(finalTable[i][e] + " ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * @param gate
	 */
	public void removeGate(Gate gate) {
		removeInsOutsGate(gate);
		gateList.remove(gate);
		printInputs();
		printOutputs();
	}
	
	/**
	 * 
	 */
	public void printLines() {
		System.out.println("Lineas actuales");
		for(int i = 0; i < lineList.size(); i++) {
			System.out.println("Linea: " + i + lineList.get(i).toString());
		}
		System.out.println();
	}
	
	/**
	 * 
	 */
	public void printGates() {
		for(int i = 0; i < gateList.size(); i++) {

			Gate.toString(gateList.get(i));
			
		}
	}
	
	/**
	 * @param l
	 */
	public void removeLine(Line l) {
		for(int i = 0; i < lineList.size(); i++) {
			if(lineList.get(i).getTail().getGateID().equals(l.getTail().getGateID()) && lineList.get(i).getPeak().getGateID().equals(l.getPeak().getGateID())) {
				lineList.remove(i);
				System.out.println("borre la linea " + l.toString());
			}
		}
	}
	
	/**
	 * @return
	 */
	public ArrayList<Gate> getInputList() {
		return inputList;
	}

	/**
	 * @param inputList
	 */
	public void setInputList(ArrayList<Gate> inputList) {
		this.inputList = inputList;
	}

	/**
	 * @return
	 */
	public ArrayList<Gate> getOutputList() {
		return outputList;
	}

	/**
	 * @param outputList
	 */
	public void setOutputList(ArrayList<Gate> outputList) {
		this.outputList = outputList;
	}

	/**
	 * @return
	 */
	public static Model getInstance() {
        return INSTANCE;
    }
	
	/**
	 * @return
	 */
	public GateLinkedList getGateList() {
		return gateList;
	}

	/**
	 * @param gateList
	 */
	public void setGateList(GateLinkedList gateList) {
		this.gateList = gateList;
	}

	/**
	 * @return
	 */
	public ArrayList<Line> getLineList() {
		return lineList;
	}

	/**
	 * @param lineList
	 */
	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
	}

}
