package rekkt.engine.command;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author nlgatewood
 *
 */

public class CommandValidator {

	private ArrayList<String> validCommands;
	private HashMap<String,String> validCommandAliases;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public CommandValidator() {
		
		validCommands = new ArrayList<String>();
		validCommandAliases = new HashMap<String,String>();
		loadValidCommands();
		loadCommandAliases();
	}
	
	/*---------------------------------------------------------------------
	 * validateCommand(commandWord) - Compare commands with valid commands
	 *---------------------------------------------------------------------*/
	public boolean validateCommand(String commandWord) {
		
		//Loop through valid commands; return true if matched
		for(String validCommandWord : validCommands) {
			
			if(commandWord.equals(validCommandWord)) {
				
				return true;
			}
		}
		
		return false;
	}
	
	/*---------------------------------------------------------------------
	 * getCommandAlias(commandWord) - 
	 *---------------------------------------------------------------------*/
	public String getCommandAlias(String commandWord) {
		
		String aliasedCommand = null;
		
		if(validCommandAliases.containsKey(commandWord)) {
			
			aliasedCommand = validCommandAliases.get(commandWord);
		}
		else {
			aliasedCommand = commandWord;
		}
		

		return aliasedCommand;
	}
	
	/*---------------------------------------------------------------------
	 * loadValidCommands() - Loads the arraylist with valid commands 
	 *---------------------------------------------------------------------*/
	private void loadValidCommands() {
				
	    try {

	    	File fXmlFile = new File("src/rekkt/lib/command/ValidCommands.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    			
	    	doc.getDocumentElement().normalize();
	    			
	    	NodeList nList = doc.getElementsByTagName("command");

	    	for(int x=0; x<nList.getLength(); x++) {
	    	
	    		Node nNode = nList.item(x);
	    		
	    		if(nNode.getNodeType() == Node.ELEMENT_NODE){
	    			
	    			Element eElement = (Element) nNode;
	    			
	    			validCommands.add(eElement.getElementsByTagName("value").item(0).getTextContent());
	    		}
	    	}	    	
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }  
	}
	
	/*---------------------------------------------------------------------
	 * loadCommandAliases() - 
	 *---------------------------------------------------------------------*/
	private void loadCommandAliases() {
		
	    try {

	    	File fXmlFile = new File("src/rekkt/lib/command/ValidCommandAliases.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    			
	    	doc.getDocumentElement().normalize();
	    			
	    	NodeList nList = doc.getElementsByTagName("command");

	    	for(int x=0; x<nList.getLength(); x++) {
	    	
	    		Node nNode = nList.item(x);
	    		
	    		if(nNode.getNodeType() == Node.ELEMENT_NODE){
	    			
	    			Element eElement = (Element) nNode;
	    			
	    			String command = eElement.getElementsByTagName("value").item(0).getTextContent();
	    			String alias = eElement.getElementsByTagName("alias").item(0).getTextContent();
	    			
	    			validCommandAliases.put(alias, command);
	    		}
	    	}	    	
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }  
	}
}
