package com.circuitdesigner.structures;

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
     * @return boolean 
     */
    private boolean isEmpty() {
    	if(first==null) {
    		return true;
    	}
    	return false;
    }
    
    /**
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
	 * @return Node
	 */
	public Node getFirst() {
		return first;
	}

	/**
	 * @param first
	 */
	public void setFirst(Node first) {
		this.first = first;
	}

	/**
	 * @return Node
	 */
	public Node getLast() {
		return last;
	}

	/**
	 * @param Node
	 */
	public void setLast(Node last) {
		this.last = last;
	}

	/**
	 * @return int
	 */
	public int size() {
		return size;
	}

	/**
	 * @param int
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
}


