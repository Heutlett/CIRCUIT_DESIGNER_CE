package com.circuitdesigner.structures;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import com.circuitdesigner.view.Table;
import com.circuitdesigner.xml.persistance.ControlGateXML;
/**
 * @autor Adrian Araya Ramirez
 * 
 * @version 1.8
 * 
 */
public class Model {
	
	private static final Model INSTANCE = new Model();
	
	private GateLinkedList gateList;
	private ArrayList<Line> lineList;
	private ArrayList<Gate> inputList;
	private ArrayList<Gate> outputList;
	private Gate outputGate;

	/**
	 * Constructor del modelo.
	 */
	private Model() {
		
		gateList = new GateLinkedList();
		lineList = new ArrayList<Line>();
		inputList = new ArrayList<Gate>();
		outputList = new ArrayList<Gate>();
		
	}
	
	/**
	 * 
	 * Guarda el circuito en un archivo XML (Parsing).
	 * 
	 * @param String
	 */
	public void guardarCircuito(String nombre) {
		
		try {
			ControlGateXML.createXML(convertirArrayList(), nombre);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Convierte la lista enlazada de compuertas en un ArrayList para el parsing.
	 * 
	 * @return ArrayList<Gate>
	 */
	private ArrayList<Gate> convertirArrayList() {
		
		ArrayList<Gate> lista = new ArrayList<Gate>();
		
		int i = 0;
		
		while(gateList.get(i) != null) {
			
			lista.add(gateList.get(i));
			
			i++;
			
		}
		
		return lista;
		
	}

	/**
	 * 
	 * Devuelve un arreglo de Strings que almacena los nombres de las columnas de la tabla resultante (i<0>, i<1>, o<0>).
	 * 
	 * @return String[]
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
	 * 
	 * Hace el calculo de la combinacion de valores segun el indice que se pasa y agrega el valor resultante de salida.
	 * 
	 * @param int
	 * @param Object[][]
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
	 * 
	 * Pasa a la funcion calculate cada iteracion/combinacion de la tabla de proposiones para que haga el calculo y llene los 
	 * resultados de las salidas.
	 * 
	 * @param int [][]
	 * @param Object[][]
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
	 * 
	 * Se encarga de mostrar la tabla con los resultados en pantalla, si se le pasa por parametro que el usuario desea ingresar 
	 * los valores de las compuertas se le muestran showInputDialogs para que ingrese estos valores.
	 * 
	 * @param boolean
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
	 * 
	 * Genera y devuelve una tabla con las 2^n cantidad de combinaciones posibles segun la cantidad de entradas.
	 * 
	 * @param int
	 * @return int[][]
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

	/**
	 * 
	 * Elimina de la lista de entradas y salidas, las entradas y salidas de la compuerta pasada por parametro.
	 * 
	 * @param Gate
	 */
	private void removeInsOutsGate(Gate gate) {
		
		outputList.remove(gate.getOutput());
		
		for(int i = 0; i < gate.getInputs().size(); i++) {
			inputList.remove(gate.getInputs().get(i));
		}
		removeInsOutsSons(gate);
	}

	/**
	 * 
	 * La compuerta pasada por parametro busca si otras compuertas se enlazan con ella, y se elimina la referencia de ser asi.
	 * 
	 * @param Gate
	 */
	public void removeInsOutsSons(Gate gate) {
		
		if(gate.getOutput() != null) {
			if(gate.getOutput().getGateID().contains("C")) {
				gate.getOutput().getInputs().remove(gate);
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
	 * 
	 * Busca y devuelve una compuerta si esta tiene en su salida la salida pasada por parametro.
	 * 
	 * @param String
	 * @return Gate
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
	 * 
	 * Busca y devuelve una compuerta si esta tiene en sus entradas la entrada pasada por parametro.
	 * 
	 * @param String
	 * @return Gate
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
	 * 
	 * Agrega a las listas de inputs y outputs las entradas y salidas de la compuerta pasada por parametro.
	 * 
	 * @param Gate
	 */
	public void addInputsOutputs(Gate gate) {
		
		for(int i = 0; i < gate.getInputs().size(); i++) {
			inputList.add(gate.getInputs().get(i));
		}
		outputList.add(gate.getOutput());
		
	}

	/**
	 * Imprime en consola las entradas que existen en el programa.
	 */
	public void printInputs() {
		System.out.print("Ins: ");
		for(int i = 0; i < inputList.size();i++) {
			
			System.out.print(inputList.get(i).getGateID() + " ");
			
		}
		System.out.println();
	}

	/**
	 * Imprime en consola las salidas que existen en el programa.
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
	 * 
	 * Agrega a la lista de compuertas la compuerta pasada por parametro.
	 * 
	 * @param Gate
	 */
	public void addGate(Gate c) {
		gateList.add(c);
	}

	/**
	 * 
	 * Imprime en consola la tabla pasada por parametro.
	 * 
	 * @param int
	 * @param int [][]
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
	 * 
	 * ELimina la compuerta pasada por parametro de la lista de compuertas.
	 * 
	 * @param Gate
	 */
	public void removeGate(Gate gate) {
		removeInsOutsGate(gate);
		gateList.remove(gate);
		printInputs();
		printOutputs();
	}

	/**
	 * Imprime en consola las lineas que existen en el programa.
	 */
	public void printLines() {
		System.out.println("Lineas actuales");
		for(int i = 0; i < lineList.size(); i++) {
			System.out.println("Linea: " + i + lineList.get(i).toString());
		}
		System.out.println();
	}

	/**
	 * Imprime en pantalla la informacion de todas las compuertas que existen en el programa.
	 */
	public void printGates() {
		for(int i = 0; i < gateList.size(); i++) {

			Gate.toString(gateList.get(i));
			
		}
	}

	/**
	 * 
	 * Elimina la linea pasada por parametro de la lista de lineas.
	 * 
	 * @param Line
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
	 * 
	 * Devuelve la lista de entradas.
	 * 
	 * @return ArrayList<Gate>
	 */
	public ArrayList<Gate> getInputList() {
		return inputList;
	}

	/**
	 * 
	 * Asigna la lista de entradas.
	 * 
	 * @param ArrayList<Gate>
	 */
	public void setInputList(ArrayList<Gate> inputList) {
		this.inputList = inputList;
	}

	/**
	 * 
	 * Devuelve la lista de salidas.
	 * 
	 * @return ArrayList<Gate>
	 */
	public ArrayList<Gate> getOutputList() {
		return outputList;
	}

	/**
	 * 
	 * Asigna la lista de salidas.
	 * 
	 * @param outputList
	 */
	public void setOutputList(ArrayList<Gate> outputList) {
		this.outputList = outputList;
	}

	/**
	 * 
	 * Metodo de Singleton que devuelve la instancia de la clase.
	 * 
	 * @return INSTANCE
	 */
	public static Model getInstance() {
        return INSTANCE;
    }

	/**
	 * 
	 * Devuelve la lista de compuertas.
	 * 
	 * @return GateLinkedList
	 */
	public GateLinkedList getGateList() {
		return gateList;
	}

	/**
	 * 
	 * Asigna la lista de compuertas.
	 * 
	 * @param gateList
	 */
	public void setGateList(GateLinkedList gateList) {
		this.gateList = gateList;
	}

	/**
	 * 
	 * Devuelve la lista de lineas.
	 * 
	 * @return ArrayList<Line>
	 */
	public ArrayList<Line> getLineList() {
		return lineList;
	}

	/**
	 * 
	 * Asigna la lista de lineas.
	 * 
	 * @param ArrayList<Line>
	 */
	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
	}

	/**
	 * 
	 * Busca una linea en la lista de lineas segun los parametros y devuelve true si la encuentra y false de lo contrario.
	 * 
	 * @param String
	 * @param String
	 * @return boolean
	 */
	public boolean buscaLineaPorTailPeak(String tailID, String peakID) {
		
		for(int i = 0; i < lineList.size(); i++) {
			if((lineList.get(i).getTail().getGateID().equals(tailID) && lineList.get(i).getPeak().getGateID().equals(peakID)) || ((lineList.get(i).getTail().getGateID().equals(peakID) && lineList.get(i).getPeak().getGateID().equals(tailID)))) {
				
				return true;
				
			}
		}
		
		return false;
		
	}
}
