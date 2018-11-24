package rekkt.engine.command;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * 
 * @author nlgatewood
 *
 */

public class CommandValidator {

	private ArrayList<String> validCommands;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public CommandValidator() {
		
		validCommands = new ArrayList<String>();
		loadValidCommands();
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
}
