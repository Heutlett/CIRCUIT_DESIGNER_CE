package com.circuitdesigner.structures;

public class GateLinkedList {
	
	private Node first;
	private Node last;
	private int size = 0;
	
    /**
     * @author carlo
     *
     */
    private static class Node { 
    	  
        private Gate gate; 
        private Node next; 
  
        // Constructor 
        public Node(Gate g) 
        { 
            gate = g; 
            next = null; 
        }
    } 
    
    /**
     * @return
     */
    private boolean isEmpty() {
    	if(first==null) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * @param g
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
     * @param index
     * @return
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
     * @param id
     * @return
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
     * @param gate
     * @return
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
     * @param gate
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
	 * @return
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
	 * @return
	 */
	public Node getLast() {
		return last;
	}

	/**
	 * @param last
	 */
	public void setLast(Node last) {
		this.last = last;
	}

	/**
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
}


