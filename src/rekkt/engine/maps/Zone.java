package rekkt.engine.maps;

import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rekkt.engine.items.*;


/**
 * 
 * @author nlgatewood
 * @Description Instance of a zone. Contains Room objects that belong in the zone
 */

public class Zone {
	
	private String zoneId;
	private String zoneName;
	private HashMap<String,Room> rooms;
	private NodeList zoneNodeList;

	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Zone(String zoneFile) {
		
		rooms = new HashMap<String,Room>();
		
		//Read Zone XML File
	    try {
	    	File fXmlFile = new File("src/rekkt/lib/maps/"+zoneFile+".xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    			
	    	doc.getDocumentElement().normalize();
	    	
	    	//Get Zone ID, Name, and NodeList
	    	zoneId = doc.getElementsByTagName("zoneid").item(0).getTextContent();
	    	zoneName = doc.getElementsByTagName("zonename").item(0).getTextContent();
	    	zoneNodeList = doc.getElementsByTagName("room");
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		//Load Rooms in Zone
		createRooms();
	}
	
	/*---------------------------------------------------------------------
	 * loadRooms() - Load all rooms in the zone into the Hash from XML Nodelist
	 *---------------------------------------------------------------------*/
	private void createRooms() {
		
	    for(int x=0; x<zoneNodeList.getLength(); x++) {
	    	
	    	Node nNode = zoneNodeList.item(x);
	    		
	    	if(nNode.getNodeType() == Node.ELEMENT_NODE){
	    			
	    		Element eElement = (Element) nNode;

	    		String id = eElement.getAttribute("id");
	    		String xCoord = eElement.getElementsByTagName("xcoord").item(0).getTextContent();
	    		String yCoord = eElement.getElementsByTagName("ycoord").item(0).getTextContent();
	    		String name = eElement.getElementsByTagName("name").item(0).getTextContent();
	    		String description = eElement.getElementsByTagName("description").item(0).getTextContent();
	    			
	    		//Get rooms exits, add to hash
	    		NodeList exitNode = eElement.getElementsByTagName("exit");
	    		HashMap<String,String> exits = new HashMap<String,String>();
	    			  			
	    		for(int y=0; y<exitNode.getLength(); y++) {

	    			exits.put(exitNode.item(y).getAttributes().item(0).getTextContent(), exitNode.item(y).getTextContent());
	    		}
	    			
	    		//Add room to zone hashMap
	    		rooms.put(id, new Room(id, xCoord, yCoord, name, description, exits));
	    	}
	    }	    	
	}
	
	/*---------------------------------------------------------------------
	 * addDefaultZoneItems() - Add default Items to the Rooms
	 *---------------------------------------------------------------------*/
	public void addDefaultZoneItems(NodeList itemsNodeList) {
		
		//Loop through rooms in this zone
	    for(int x=0; x<zoneNodeList.getLength(); x++) {
	    	
	    	Node zoneNode = zoneNodeList.item(x);
	    			
	    	Element eElement = (Element) zoneNode;

    		String roomId = eElement.getAttribute("id");
	    			
    		//Get rooms exits, add to hash
    		NodeList zoneItemNode = eElement.getElementsByTagName("item");
	    		
    		//Loop through each room Item
    		for(int y=0; y<zoneItemNode.getLength(); y++) {
	    			
    			String itemNum = eElement.getElementsByTagName("item").item(y).getTextContent();
    			
    			System.out.println("item"+itemNum);

    			//Loop through each item from the Items List
    			for(int z=0; z<itemsNodeList.getLength(); z++) {
	    				
    		    	Node itemNNode = itemsNodeList.item(z);
    		    	Element itemEElement = (Element) itemNNode;
    		    	
    		    	//If item number is the same, create the item
    				if(itemNum.equals(itemEElement.getAttribute("id"))) {
	    					
	   					String itemName = itemEElement.getElementsByTagName("name").item(0).getTextContent();
	   					String itemDescription = itemEElement.getElementsByTagName("description").item(0).getTextContent();
	   					String itemType = itemEElement.getElementsByTagName("type").item(0).getTextContent();
	   					Item newItem = null;

	   					//If this is a container Item, create a ContainerItem
	   					if(itemType.equals("contain")) {
	   						
	   						int itemSize = Integer.parseInt(itemEElement.getElementsByTagName("type").item(0).getTextContent());
	   						newItem = new ContainerItem(itemName,itemDescription, itemSize);
	   					}
	   					//Else, this is a misc item
	   					else {
	   						newItem = new MiscItem(itemName, itemDescription);
	   					}
	   					
	   					rooms.get(roomId).addRoomItem(newItem);
	   				}
	   			}	
	   		}   	
	    }	
	}
	
	/*---------------------------------------------------------------------
	 * getRoom(String roomId) - Return Room Object
	 *---------------------------------------------------------------------*/
	public Room getRoom(String roomId) {
		
		return rooms.get(roomId);
	}
	
	/*---------------------------------------------------------------------
	 * getId() - Return the Zone's ID
	 *---------------------------------------------------------------------*/
	public String getId() {
		
		return zoneId;
	}

	/*---------------------------------------------------------------------
	 * getName() - Return Zone's Name
	 *---------------------------------------------------------------------*/
	public String getName() {
		
		return zoneName;
	}
}
