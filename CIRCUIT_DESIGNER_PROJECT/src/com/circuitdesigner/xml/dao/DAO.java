package com.circuitdesigner.xml.dao;

import java.util.ArrayList;

public interface DAO <T>{
	
    /**
     * Inserta a la base de datos.
     * 
     * @param t parametro a insertar
     * @return si fue insertado
     */
	boolean insert(T t, String nombre);
    
	/**
     * Obtiene el objeto con el id proporcionado, de la base ed datos.
     * 
     * @param id del objeto solicitado
     * @return el objeto solicitado o null si no existe
     */
	T get(String id, String nombre);
    
	/**
     * Modifica el objeto en la base de datos.
     * 
     * @param t objeto a modificar
     * @return si fue modificado
     */
	boolean modify(T t, String nombre);
    
	/**
     * Elimina el objeto de la base de datos.
     * 
     * @param id del objeto a eliminar.
     * @return si fue eliminado
     */
	boolean delete(String id, String nombre);
    
	/**
     * Obtiene todos los objetos de un tipo.
     * 
     * @return una arreglo de todos los objetos de un tipo.
     */
	ArrayList<T> getAll(String nombre);
    
	/**
     * Guarda el objeto en la base de datos.
     * 
     * @param t objeto a guardar
     * @return si se guardo
     */
	boolean save(ArrayList<T> t, String nombre);
	
}
