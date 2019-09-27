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
/**
 * 
 * @author Adrian Araya Ramirez
 * 
 * @version 1.8
 *
 */
public class ControlGateXML {

	/**
	 * @param String
	 * @return ArrayList<Gate>
	 */
	public static ArrayList<Gate> readGateXML(String nombre){
		
		ArrayList<Gate> gateFinal = new ArrayList<Gate>();
		
		try {
			File file = new File(nombre + ".xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
			
            document.getDocumentElement().normalize();
			
            NodeList gateList = document.getElementsByTagName("GATE");
            
            for(int i = 0 ; i < gateList.getLength() ; i++) {
                Node nodo = gateList.item(i);
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                	
                	Element element = (Element) nodo;
                	
                	int value = Integer.valueOf(element.getElementsByTagName("VALUE").item(0).getTextContent());
                	boolean locked = Boolean.valueOf(element.getElementsByTagName("LOCKED").item(0).getTextContent());
                	GateType type = devuelveGateType(element.getElementsByTagName("TYPE").item(0).getTextContent());
                	int x = Integer.valueOf(element.getElementsByTagName("X").item(0).getTextContent());
                	int y = Integer.valueOf(element.getElementsByTagName("Y").item(0).getTextContent());
                	
                	
                	ArrayList<Gate> inputs = new ArrayList<Gate>();
                	
                	for(int a = 0; a < element.getElementsByTagName("INPUTS").item(0).getChildNodes().getLength(); a++) {
                		
                		
                    	int value2 = Integer.valueOf(element.getElementsByTagName("INPUTS").item(0).getChildNodes().item(a).getChildNodes().item(0).getTextContent());
                    	boolean locked2 = Boolean.valueOf(element.getElementsByTagName("INPUTS").item(0).getChildNodes().item(a).getChildNodes().item(1).getTextContent());
                    	GateType type2 = devuelveGateType(element.getElementsByTagName("INPUTS").item(0).getChildNodes().item(a).getChildNodes().item(2).getTextContent());
                    	int x2 = Integer.valueOf(element.getElementsByTagName("INPUTS").item(0).getChildNodes().item(a).getChildNodes().item(3).getTextContent());
                    	int y2 = Integer.valueOf(element.getElementsByTagName("INPUTS").item(0).getChildNodes().item(a).getChildNodes().item(4).getTextContent());
                		
                    	inputs.add(new Gate(value2, locked2, type2, x2, y2));
                    	
                    	
                	}
                	int value3 = Integer.valueOf(element.getElementsByTagName("OUTPUT").item(0).getChildNodes().item(0).getTextContent());
                	boolean locked3 = Boolean.valueOf(element.getElementsByTagName("OUTPUT").item(0).getChildNodes().item(1).getTextContent());
                	GateType type3 = devuelveGateType(element.getElementsByTagName("OUTPUT").item(0).getChildNodes().item(2).getTextContent());
                	int x3 = Integer.valueOf(element.getElementsByTagName("OUTPUT").item(0).getChildNodes().item(3).getTextContent());
                	int y3 = Integer.valueOf(element.getElementsByTagName("OUTPUT").item(0).getChildNodes().item(4).getTextContent());
                	
                	
                	
                	Gate gate = new Gate(value, locked, type, x, y);
                	gate.setInputs(inputs);
                	gate.setOutput(new Gate(value3, locked3, type3, x3, y3));
                	
                	gateFinal.add(gate);
                	
                	
                }
                
            }
            
		}catch(Exception e) {
			return gateFinal;
		}
		
		
		return gateFinal;
	}
	
	/**
	 * @param String
	 * @return GateType
	 */
	private static GateType devuelveGateType(String type) {
		if(type.equals("AND")) {
			return GateType.AND;
		}
		if(type.equals("NAND")) {
			return GateType.NAND;
		}
		if(type.equals("OR")) {
			return GateType.OR;
		}
		if(type.equals("NOR")) {
			return GateType.NOR;
		}
		if(type.equals("NOT")) {
			return GateType.NOT;
		}
		if(type.equals("XOR")) {
			return GateType.XOR;
		}
		if(type.equals("XNOR")) {
			return GateType.XNOR;
		}
		if(type.equals("INPUT")) {
			return GateType.INPUT;
		}
		if(type.equals("OUTPUT")) {
			return GateType.OUTPUT;
		}
		return null;
	}
	
	/**
	 * @param ArrayList<Gate>
	 * @param String
	 * @return boolean
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public static boolean createXML(ArrayList<Gate> gateList, String nombreArchivo) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, nombreArchivo, null);
            document.setXmlVersion("1.0");
            
            // NODO RAIZ
            Element raiz = document.getDocumentElement();
            
            for(int i = 0 ; i <gateList.size() ; i++) {
            	
            	Element itemNode = document.createElement("GATE");
            	
            	//ATRIBUTOS NORMALES
                
                Element gateValueNode = document.createElement("VALUE");
                Text nodeGateValueValue =document.createTextNode(String.valueOf(gateList.get(i).getValue()));
                gateValueNode.appendChild(nodeGateValueValue);
                itemNode.appendChild(gateValueNode);
                
                Element gateLockedNode = document.createElement("LOCKED");
                Text nodeGateLockedValue =document.createTextNode(String.valueOf(gateList.get(i).isLocked()));
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
                
                Element gateXNode = document.createElement("X");
                Text nodeGateXValue =document.createTextNode(String.valueOf(gateList.get(i).getGateLabel().getX()));
                gateXNode.appendChild(nodeGateXValue);
                itemNode.appendChild(gateXNode);
                
                Element gateYNode = document.createElement("Y");
                Text nodeGateYValue =document.createTextNode(String.valueOf(gateList.get(i).getGateLabel().getY()));
                gateYNode.appendChild(nodeGateYValue);
                itemNode.appendChild(gateYNode);
                
                Element listaInputsNode = document.createElement("INPUTS");
                
                if(!gateList.get(i).getInputs().isEmpty()){
                	
                	for(int e = 0; e < gateList.get(i).getInputs().size(); e++) {
                		
                		Element elementoListaInputs = document.createElement("INPUT");
                		
                        Element elementoListaInputsValueNode = document.createElement("VALUE");
                        Text nodeElementoListaInputsValueValue =document.createTextNode(String.valueOf(gateList.get(i).getInputs().get(e).getValue()));
                        elementoListaInputsValueNode.appendChild(nodeElementoListaInputsValueValue);
                        elementoListaInputs.appendChild(elementoListaInputsValueNode);
                        
                        Element elementoListaInputsLockedNode = document.createElement("LOCKED");
                        Text nodeElementoListaInputsLockedValue =document.createTextNode(String.valueOf(gateList.get(i).getInputs().get(e).isLocked()));
                        elementoListaInputsLockedNode.appendChild(nodeElementoListaInputsLockedValue);
                        elementoListaInputs.appendChild(elementoListaInputsLockedNode);
                        
                        
                        Text nodeElementoListaInputsTypeValue = null;
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.AND)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("AND");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.NAND)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("NAND");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.OR)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("OR");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.NOR)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("NOR");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.NOT)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("NOT");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.XOR)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("XOR");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.XNOR)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("XNOR");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.INPUT)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("INPUT");
                        }
                        if(gateList.get(i).getInputs().get(e).getType().equals(GateType.OUTPUT)) {
                        	nodeElementoListaInputsTypeValue =document.createTextNode("OUTPUT");
                        }
                        
                        
                        Element elementoListaInputsTypeNode = document.createElement("TYPE");
                        elementoListaInputsTypeNode.appendChild(nodeElementoListaInputsTypeValue);
                        elementoListaInputs.appendChild(elementoListaInputsTypeNode);
                        
                        Element elementoListaInputsXNode = document.createElement("X");
                        Text nodeElementoListaInputsXValue =document.createTextNode(String.valueOf(gateList.get(i).getInputs().get(e).getGateLabel().getX()));
                        elementoListaInputsXNode.appendChild(nodeElementoListaInputsXValue);
                        elementoListaInputs.appendChild(elementoListaInputsXNode);
                        
                        Element elementoListaInputsYNode = document.createElement("Y");
                        Text nodeElementoListaInputsYValue =document.createTextNode(String.valueOf(gateList.get(i).getInputs().get(e).getGateLabel().getY()));
                        elementoListaInputsYNode.appendChild(nodeElementoListaInputsYValue);
                        elementoListaInputs.appendChild(elementoListaInputsYNode);
                        
                        listaInputsNode.appendChild(elementoListaInputs);
                		
                	}
                }
                
                itemNode.appendChild(listaInputsNode);
                
                if(gateList.get(i).getOutput() != null) {
                	
            		Element outputNode = document.createElement("OUTPUT");
            		
                    Element elementoOutputValueNode = document.createElement("VALUE");
                    Text nodeElementoOutputValueValue =document.createTextNode(String.valueOf(gateList.get(i).getOutput().getValue()));
                    elementoOutputValueNode.appendChild(nodeElementoOutputValueValue);
                    outputNode.appendChild(elementoOutputValueNode);
                    
                    Element elementoOutputLockedNode = document.createElement("LOCKED");
                    Text nodeElementoOutputLockedValue =document.createTextNode(String.valueOf(gateList.get(i).getOutput().isLocked()));
                    elementoOutputLockedNode.appendChild(nodeElementoOutputLockedValue);
                    outputNode.appendChild(elementoOutputLockedNode);
                    
                    Text nodeElementoOutputTypeValue = null;
                    if(gateList.get(i).getOutput().getType().equals(GateType.AND)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("AND");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.NAND)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("NAND");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.OR)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("OR");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.NOR)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("NOR");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.NOT)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("NOT");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.XOR)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("XOR");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.XNOR)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("XNOR");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.INPUT)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("INPUT");
                    }
                    if(gateList.get(i).getOutput().getType().equals(GateType.OUTPUT)) {
                    	nodeElementoOutputTypeValue =document.createTextNode("OUTPUT");
                    }
                    
                    
                    Element elementoOutputTypeNode = document.createElement("TYPE");
                    elementoOutputTypeNode.appendChild(nodeElementoOutputTypeValue);
                    outputNode.appendChild(elementoOutputTypeNode);
                    
                    
                    Element elementoOutputXNode = document.createElement("X");
                    Text nodeElementoOutputXValue =document.createTextNode(String.valueOf(gateList.get(i).getOutput().getGateLabel().getX()));
                    elementoOutputXNode.appendChild(nodeElementoOutputXValue);
                    outputNode.appendChild(elementoOutputXNode);
                    
                    Element elementoOutputYNode = document.createElement("Y");
                    Text nodeElementoOutputYValue =document.createTextNode(String.valueOf(gateList.get(i).getOutput().getGateLabel().getY()));
                    elementoOutputYNode.appendChild(nodeElementoOutputYValue);
                    outputNode.appendChild(elementoOutputYNode);
                    
                    itemNode.appendChild(outputNode);
                  
                }
                
                raiz.appendChild(itemNode);
                
            }
            
            // GENERA XML
            Source source = new DOMSource(document);
            
            // DONDE SE GUARDARA
            
            Result result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
			
		}catch(ParserConfigurationException e) {
			return false;
		}
		return true;
	
	}
}
