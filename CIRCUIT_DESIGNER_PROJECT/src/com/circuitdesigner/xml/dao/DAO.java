package com.circuitdesigner.xml.dao;

import java.util.ArrayList;
/**
 * 
 * @author Adrian Araya Ramirez
 *
 * @version 1.8
 *
 * @param <T>
 */
public interface DAO <T>{
	
    /**
     * Inserta al final de la lista.
     * 
     * @param T 
     * @param String 
     * @return boolean
     */
	boolean insert(T t, String nombre);
    
	/**
	 * 
	 * Devuelve el elemento con el ID pasado por parametro.
	 * 
     * @param String
     * @param String
     * @return T
     */
	T get(String id, String nombre);
    
	/**
	 * 
	 * Modifica el elemento T
	 * 
     * @param T
     * @param String
     * @return boolean
     */
	boolean modify(T t, String nombre);
    
	/**
	 * 
	 * Elimina el elemento con el ID de la lista.
	 * 
     * @param String
     * @param String
     * @return boolean
     */
	boolean delete(String id, String nombre);
    
	/**
	 * 
	 * Devuelve la lista.
	 * 
     * @param String
     * @return ArrayList<T>
     */
	ArrayList<T> getAll(String nombre);
    
	/**
	 * 
	 * Guarda la lista.
	 * 
     * @param ArrayList<T>
     * @param String
     * @return boolean
     */
	boolean save(ArrayList<T> t, String nombre);
	
}
