package com.circuitdesigner.xml.daoxml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.circuitdesigner.structures.Gate;
import com.circuitdesigner.xml.dao.GateDAO;
import com.circuitdesigner.xml.persistance.ControlGateXML;

public class GateDAOxml implements GateDAO, Serializable{

	@Override
	public boolean insert(Gate t, String nombre) {
		
        ArrayList<Gate> gateList = getAll(nombre);
        
        gateList.add(t);
        
        return save(gateList, nombre);
        		
	}

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

	@Override
	public boolean modify(Gate t, String nombre) {
		delete(t.getGateID(), nombre);
		return insert(t, nombre);
	}

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

	@Override
	public ArrayList<Gate> getAll(String nombre) {
		return ControlGateXML.readGateXML(nombre);
	}

	@Override
	public boolean save(ArrayList<Gate> t, String nombre) {
		
        try{
            return ControlGateXML.createXML(t, nombre);
        }catch(Exception e){
            return false;
        }
        
	}

	
	
	
	
}
