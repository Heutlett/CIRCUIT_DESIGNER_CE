package com.circuitdesigner.xml.persistance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import com.circuitdesigner.structures.Gate;
import com.circuitdesigner.structures.Gate.GateType;

public class ControlGateXML {

	public static ArrayList<Gate> readGateXML(){
		
		ArrayList<Gate> gateFinal = new ArrayList<Gate>();
		
		try {
			File file = new File("Gates.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
			
            document.getDocumentElement().normalize();
            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
			
            NodeList gateList = document.getElementsByTagName("GATE");
            
            for(int i = 0 ; i < gateList.getLength() ; i++) {
                Node nodo = gateList.item(i);
                
                
                
            }
            
		}catch(Exception e) {
			
		}
		
		
		return gateFinal;
	}
	
	public static boolean createXML(ArrayList<Gate> gateList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		String nomArchivo = "Gates";
		
		try {
			
			DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, nomArchivo, null);
            document.setXmlVersion("1.0");
            
            // NODO RAIZ
            Element raiz = document.getDocumentElement();
            
            for(int i = 0 ; i <gateList.size() ; i++) {
            	
            	Element itemNode = document.createElement("GATE");
            	
            	//ATRIBUTOS NORMALES
                
                Element gateIdNode = document.createElement("GATEID");
                Text nodeGateIdValue =document.createTextNode(gateList.get(i).getGateID());
                gateIdNode.appendChild(nodeGateIdValue);
                itemNode.appendChild(gateIdNode);
                
                Element gateValueNode = document.createElement("VALUE");
                Text nodeGateValueValue =document.createTextNode(gateList.get(i).getGateID());
                gateValueNode.appendChild(nodeGateValueValue);
                itemNode.appendChild(gateValueNode);
                
                Element gateLockedNode = document.createElement("LOCKED");
                Text nodeGateLockedValue =document.createTextNode(gateList.get(i).getGateID());
                gateLockedNode.appendChild(nodeGateLockedValue);
                itemNode.appendChild(gateLockedNode);
                
                Text nodeGateTypeValue = null;
                if(gateList.get(i).getType().equals(GateType.AND)) {
                	 nodeGateTypeValue =document.createTextNode("AND");
                }
                if(gateList.get(i).getType().equals(GateType.NAND)) {
                	 nodeGateTypeValue =document.createTextNode("NAND");
                }
                if(gateList.get(i).getType().equals(GateType.OR)) {
                	 nodeGateTypeValue =document.createTextNode("OR");
                }
                if(gateList.get(i).getType().equals(GateType.NOR)) {
                	 nodeGateTypeValue =document.createTextNode("NOR");
                }
                if(gateList.get(i).getType().equals(GateType.NOT)) {
                	 nodeGateTypeValue =document.createTextNode("NOT");
                }
                if(gateList.get(i).getType().equals(GateType.XOR)) {
                	 nodeGateTypeValue =document.createTextNode("XOR");
                }
                if(gateList.get(i).getType().equals(GateType.XNOR)) {
                	 nodeGateTypeValue =document.createTextNode("XNOR");
                }
                if(gateList.get(i).getType().equals(GateType.INPUT)) {
                	 nodeGateTypeValue =document.createTextNode("INPUT");
                }
                if(gateList.get(i).getType().equals(GateType.OUTPUT)) {
                	 nodeGateTypeValue =document.createTextNode("OUTPUT");
                }
                
                Element gateTypeNode = document.createElement("TYPE");
                gateTypeNode.appendChild(nodeGateTypeValue);
                itemNode.appendChild(gateTypeNode);
                
                
                
                
            }
            
			
			
		}catch(Exception e) {
			
		}
		return true;
	
	}
}
