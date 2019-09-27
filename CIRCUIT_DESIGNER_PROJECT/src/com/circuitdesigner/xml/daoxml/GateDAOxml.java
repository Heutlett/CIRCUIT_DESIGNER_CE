package com.circuitdesigner.xml.daoxml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.circuitdesigner.structures.Gate;
import com.circuitdesigner.xml.dao.GateDAO;
import com.circuitdesigner.xml.persistance.ControlGateXML;

public class GateDAOxml implements GateDAO, Serializable{

	/**
	 * 
	 * Inserta una compuerta al final de la lista.
	 * 
	 * @param Gate
	 * @param String
	 * @return boolean
	 *
	 */
	@Override
	public boolean insert(Gate t, String nombre) {
		
        ArrayList<Gate> gateList = getAll(nombre);
        
        gateList.add(t);
        
        return save(gateList, nombre);
        		
	}

	/**
	 * 
	 * Devuelve la compuerta con el ID pasado por parametro.
	 * 
	 * @param String
	 * @param String
	 * @return Gate
	 */
	@Override
	public Gate get(String id, String nombre) {
		ArrayList<Gate> gateList = getAll(nombre);
		
		for(int i = 0; i < gateList.size(); i++) {
			if(gateList.get(i).getGateID().equals(id)) {
				return gateList.get(i);
			}
		}
		return null;
	}

	/**
	 * 
	 * Modifica la compuerta pasada por parametro.
	 * 
	 * @param Gate
	 * @param String
	 * @return boolean
	 */
	@Override
	public boolean modify(Gate t, String nombre) {
		delete(t.getGateID(), nombre);
		return insert(t, nombre);
	}

	/**
	 * 
	 * Elimina la compuerta con el ID pasado por parametro de la lista.
	 * 
	 * @param String
	 * @param String
	 * @return boolean
	 */
	@Override
	public boolean delete(String id, String nombre) {
		
		ArrayList<Gate> gateList = getAll(nombre);
		
		for(int i = 0; i < gateList.size(); i++) {
			if(gateList.get(i).getGateID().equals(id)) {
				gateList.remove(gateList.get(i));
				return save(gateList, nombre);
			}
		}
		return false;
	}

	/**
	 * 
	 * Devuelve la lista de compuertas.
	 * 
	 * @param String
	 * @return ArrayList<Gate>
	 */
	@Override
	public ArrayList<Gate> getAll(String nombre) {
		return ControlGateXML.readGateXML(nombre);
	}

	/**
	 * 
	 * Guarda la lista.
	 * 
	 * @param ArrayList<Gate>
	 * @param String
	 * @return boolean
	 */
	@Override
	public boolean save(ArrayList<Gate> t, String nombre) {
		
        try{
            return ControlGateXML.createXML(t, nombre);
        }catch(Exception e){
            return false;
        }
        
	}
	
}
