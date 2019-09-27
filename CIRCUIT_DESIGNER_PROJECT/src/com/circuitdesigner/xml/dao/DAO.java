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
     * @param T 
     * @param String 
     * @return boolean
     */
	boolean insert(T t, String nombre);
    
	/**
     * @param String
     * @param String
     * @return T
     */
	T get(String id, String nombre);
    
	/**
     * @param T
     * @param String
     * @return boolean
     */
	boolean modify(T t, String nombre);
    
	/**
     * @param String
     * @param String
     * @return boolean
     */
	boolean delete(String id, String nombre);
    
	/**
     * @param String
     * @return ArrayList<T>
     */
	ArrayList<T> getAll(String nombre);
    
	/**
     * @param ArrayList<T>
     * @param String
     * @return boolean
     */
	boolean save(ArrayList<T> t, String nombre);
	
}
