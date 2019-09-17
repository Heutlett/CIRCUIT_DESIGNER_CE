package com.circuitdesigner.estructuras;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.circuitdesigner.view.Tabla;

public class Sistema {
	
	private static final Sistema INSTANCE = new Sistema();
	
	private ArrayList<Compuerta> listaCompuertas;
	private ArrayList<Linea> listaLineas;
	private ArrayList<Compuerta> listaIns;
	private ArrayList<Compuerta> listaOuts;

	private Sistema() {
		
		listaCompuertas = new ArrayList<Compuerta>();
		listaLineas = new ArrayList<Linea>();
		listaIns = new ArrayList<Compuerta>();
		listaOuts = new ArrayList<Compuerta>();
		
		//
		//imprimirTabla(4, tabla);
		
	}
	
	private String[] generarNombresColumnas() {
		
		String [] nombresColumnas = new String[listaIns.size()+1];
		
		for(int i = 0; i < listaIns.size(); i++) {
			nombresColumnas[i] = listaIns.get(i).getIdProposicion();
		}
		nombresColumnas[listaIns.size()] = listaOuts.get(0).getIdProposicion();
		return nombresColumnas;
	}
	
	private void calcular(int [][] tabla, Object [][] resultado) {
	
		int cantTrues = 0;
		int cantFalses = 0;
		
		for(int i = 0; i < (int)Math.pow(2, listaIns.size()); i++) {
			
			
			for(int e = 0; e < listaIns.size(); e++) {
				
				listaIns.get(e).setValorEntrada(tabla[i][e]);
				
			}
			//si es and
			for(int e = 0; e < listaIns.size(); e++) {
				
				if(listaIns.get(e).getValorEntrada() == 0) {
					cantFalses++;
				}
				
			}
			if(cantFalses > 0) {
				cantFalses = 0;
				resultado[i][listaIns.size()] = 0;
			}else {
				cantFalses = 0;
				resultado[i][listaIns.size()] = 1;
			}
			
			
		}
		
	}
	
	public void calcularTabla() {
		
		if(listaIns.size()<1) {
			JOptionPane.showMessageDialog(null, "No se puede ejecutar si se encuentra vacio!!!");
			return;
		}
		
		if(listaOuts.size()>1) {
			JOptionPane.showMessageDialog(null, "Solo puede existir una salida!!!");
			return;
		}
		
		int [][] tabla = generarTabla(listaIns.size());
		
		String [] nombresColumnas = generarNombresColumnas();
		
		Object [][] datosFila = new Object [(int)Math.pow(2, listaIns.size())][listaIns.size()+1];
		
		for(int i = 0; i < (int)Math.pow(2, listaIns.size()); i++) {
			for(int e = 0; e < listaIns.size(); e++) {
				datosFila[i][e] = tabla[i][e];
			}
		}
		
		/*
		Object [][] datosFila = {
				
				{"asda", 123,123,false},
				{"asda", 123,123,false},
				{"asda", 123,123,false},
				{"asda", 123,123,false},
				{"asda", 123,123,false},
				{"asda", 123,123,false},
				{"asda", 123,123,false},
				{"asda", 123,123,false}
				
				
		};
		*/
				//new Object[2][3];
		
		//datosFila[0][0] = "1";
		//datosFila[0][1] = "2";
		//datosFila[0][2] = "3";
		//datosFila[1][0] = "4";
		//datosFila[1][1] = "5";
		//datosFila[1][2] = "6";
		
		calcular(tabla, datosFila);
		
		Tabla t = new Tabla(datosFila, nombresColumnas);
		t.setVisible(true);
		
	}
	
	public void borrarIn(String nombre) {
		for(int i = 0; i < listaIns.size();i++) {
			if(listaIns.get(i).getIdProposicion().equals(nombre)){
				listaIns.remove(i);
			}
		}
	}
	
	public void borrarOut(String nombre) {
		for(int i = 0; i < listaOuts.size();i++) {
			if(listaOuts.get(i).getIdProposicion().equals(nombre)){
				listaOuts.remove(i);
			}
		}
	}
	
	public Compuerta buscarCompuertaPorSalida(String salida) {
		
		Compuerta c = null;
		
		for(int i = 0; i < listaCompuertas.size(); i++) {
			
			if(listaCompuertas.get(i).getSalida().getIdProposicion().equals(salida)){
				
				return listaCompuertas.get(i);
				
			}
		}
		
		return c;
		
	}
	
	public Compuerta buscarCompuertaPorEntrada(String entrada) {
		
		Compuerta c = null;
		
		for(int i = 0; i < listaCompuertas.size(); i++) {
			
			for(int e = 0; e < listaCompuertas.get(i).getEntradas().size(); e++) {
				
				if(listaCompuertas.get(i).getEntradas().get(e).getIdProposicion().equals(entrada)){
					
					return listaCompuertas.get(i);
					
				}
				
			}
		}
		
		return c;
		
	}
	
	public void agregarInsOuts(Compuerta c) {
		
		for(int i = 0; i < c.getEntradas().size(); i++) {
			listaIns.add(c.getEntradas().get(i));
		}
		listaOuts.add(c.getSalida());
		
	}
	
	public void imprimirIns() {
		System.out.print("Ins: ");
		for(int i = 0; i < listaIns.size();i++) {
			
			System.out.print(listaIns.get(i).getIdProposicion() + " ");
			
		}
		System.out.println();
	}
	
	public void imprimirOuts() {
		System.out.print("Outs: ");
		for(int i = 0; i < listaOuts.size();i++) {
			
			System.out.print(listaOuts.get(i).getIdProposicion() + " ");
			
		}
		System.out.println();
		System.out.println();
	}
	
    public ArrayList<Compuerta> getListaIns() {
		return listaIns;
	}

	public void setListaIns(ArrayList<Compuerta> listaIns) {
		this.listaIns = listaIns;
	}

	public ArrayList<Compuerta> getListaOuts() {
		return listaOuts;
	}

	public void setListaOuts(ArrayList<Compuerta> listaOuts) {
		this.listaOuts = listaOuts;
	}

	public static Sistema getInstance() {
        return INSTANCE;
    }
    
	public void addCompuertas(Compuerta c) {
		listaCompuertas.add(c);
	}
	
	public static void imprimirTabla(int n, int [][]tablaFinal) {
		
		for(int  i =0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				System.out.print(tablaFinal[i][e] + " ");
			}
			System.out.println();
		}
		
	}
	
	public static int[][] generarTabla(int n) {
		
		int numero = 1;
		int [][] tabla = new int[n][(int) Math.pow(2, n)];
		int [][] tablaFinal = new int[(int) Math.pow(2, n)][n];
		
		for(int i = 0; i < n; i++) {
			
			int contador = 0;
			
			for(int e = 0; e < (int) Math.pow(2, n); e++) {
				
				int trueOrFalse = (int) Math.pow(2, n-i)/2;
				
				if(contador == trueOrFalse) {
					if(numero == 1) {
						numero = 0;
					}else {
						numero = 1;
					}
					contador = 0;
				}
				contador++;
				tabla[i][e] = numero;
			}
		}
		
		for(int i = 0; i < (int) Math.pow(2, n); i++) {
			
			for(int e = 0; e < n; e++) {
				tablaFinal[i][e] = tabla[e][i];
			}
			
		}

		return tablaFinal;
	}
	
	public ArrayList<Compuerta> getListaCompuertas() {
		return listaCompuertas;
	}

	public void setListaCompuertas(ArrayList<Compuerta> listaCompuertas) {
		this.listaCompuertas = listaCompuertas;
	}

	public ArrayList<Linea> getListaLineas() {
		return listaLineas;
	}

	public void setListaLineas(ArrayList<Linea> listaLineas) {
		this.listaLineas = listaLineas;
	}


}
