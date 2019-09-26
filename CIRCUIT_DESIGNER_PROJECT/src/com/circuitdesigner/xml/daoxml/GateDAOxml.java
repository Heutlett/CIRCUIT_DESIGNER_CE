package com.circuitdesigner.xml.daoxml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.circuitdesigner.structures.Gate;
import com.circuitdesigner.xml.dao.GateDAO;
import com.circuitdesigner.xml.persistance.ControlGateXML;

public class GateDAOxml implements GateDAO, Serializable{

	@Override
	public boolean insert(Gate t) {
		
        ArrayList<Gate> gateList = getAll();
        
        gateList.add(t);
        
        return save(gateList);
        		
	}

	@Override
	public Gate get(String id) {
		ArrayList<Gate> gateList = getAll();
		
		for(int i = 0; i < gateList.size(); i++) {
			if(gateList.get(i).getGateID().equals(id)) {
				return gateList.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean modify(Gate t) {
		delete(t.getGateID());
		return insert(t);
	}

	@Override
	public boolean delete(String id) {
		
		ArrayList<Gate> gateList = getAll();
		
		for(int i = 0; i < gateList.size(); i++) {
			if(gateList.get(i).getGateID().equals(id)) {
				gateList.remove(gateList.get(i));
				return save(gateList);
			}
		}
		return false;
	}

	@Override
	public ArrayList<Gate> getAll() {
		return ControlGateXML.readGateXML();
	}

	@Override
	public boolean save(ArrayList<Gate> t) {
		
        try{
            return ControlGateXML.createXML(t);
        }catch(Exception e){
            return false;
        }
        
	}

	
	
	
	
}
