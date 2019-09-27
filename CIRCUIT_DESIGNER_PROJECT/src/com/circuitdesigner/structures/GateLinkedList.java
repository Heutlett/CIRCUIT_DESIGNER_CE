package com.circuitdesigner.structures;
/**
 * 
 * @author Adrian Araya Ramirez
 * 
 * @version 1.8
 *
 */
public class GateLinkedList {
	
	private Node first;
	private Node last;
	private int size = 0;
	
    /**
     * @author Adrian Araya Ramirez
     * 
     * @version 1.8
     *
     */
    private static class Node { 
    	  
        private Gate gate; 
        private Node next; 
  
        /**
         * @param Gate
         */
        public Node(Gate g) 
        { 
            gate = g; 
            next = null; 
        }
    } 
    
    /**
     * 
     * Devuelve true si la lista esta vacia, y false de lo contrario.
     * 
     * @return boolean 
     */
    private boolean isEmpty() {
    	if(first==null) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * 
     * Agrega una compuerta al final de la lista.
     * 
     * @param Gate
     */
    public void add(Gate g) {
    	if(isEmpty()) {
    		this.first = new Node(g);
    	}else {
    		Node aux = first;
    		
    		while(aux.next != null) {
    			aux = aux.next;
    		}
    		aux.next = new Node(g);
    	}
    	size++;
    }
    
    /**
     * 
     * Devuelve la compuerta que se encuentra en el indice pasado por parametro.
     * 
     * @param int
     * @return Gate
     */
    public Gate get(int index) {
    	
    	if(isEmpty()) {
    		return null;
    	}else {
    		Node aux = first;
    		int counter = 0;
    		while(aux != null) {
    			if(counter == index) {
    				return aux.gate;
    			}
    			aux = aux.next;
    			counter++;
    		}
    	}
    	return null;
    }
    
    /**
     * 
     * Busca en la lista una compuerta por ID y la devuelve si la encuentra, devuelve null si no la encuentra.
     * 
     * @param String
     * @return Gate
     */
    public Gate getById(String id) {
    	
    	if(isEmpty()) {
    		return null;
    	}else {
    		Node aux = first;
    		while(aux != null) {
    			if(aux.gate.getGateID().equals(id)) {
    				return aux.gate;
    			}
    			aux = aux.next;
    		}
    	}
    	return null;
    }
    
    /**
     * 
     * Busca la compuerta pasada por parametro en la lista, si la encuentra devuelve true y false de lo contrario.
     * 
     * @param Gate
     * @return boolean
     */
    public boolean find(Gate gate){

        Node aux = first;

        boolean finded = false;


        while(aux != null && finded != true){

            if (gate == aux.gate){

                finded = true;
            }
            else{

                aux = aux.next;
            }
        }

        return finded;
    }
    
    /**
     * 
     * Elimina de la lista la compuerta pasada por parametro.
     * 
     * @param Gate
     */
    public void remove(Gate gate) {
    	
    	if (find(gate)) {

            if (first.gate == gate) {

                first = first.next;
            } else{

                Node aux = first;

                while(aux.next.gate != gate){
                    aux = aux.next;
                }
                
                Node tmp = aux.next.next;
                aux.next = tmp;  
            }
            size--;
        }
    	
    }
    
	/**
	 * 
	 * Devuelve el tamano de la lista.
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}
	
}


