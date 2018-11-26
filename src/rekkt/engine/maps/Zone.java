package rekkt.engine.maps;

import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author nlgatewood
 * @Description Instance of a zone. Contains Room objects that belong in the zone
 */

public class Zone {
	
	private String zoneFile;
	private String zoneId;
	private String zoneName;
	private HashMap<String,Room> rooms;

	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Zone(String zoneFile) {
		
		this.zoneFile = zoneFile;
		zoneId = null;
		zoneName = null;
		rooms = new HashMap<String,Room>();
		
		//Load Rooms in Zone
		loadRooms();
	}
	
	/*---------------------------------------------------------------------
	 * loadRooms() - Load all rooms in the zone into the Hash
	 *---------------------------------------------------------------------*/
	private void loadRooms() {
		
	    try {
	    	File fXmlFile = new File("src/rekkt/lib/maps/"+zoneFile+".xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    			
	    	doc.getDocumentElement().normalize();
	    	
	    	//Get Zone ID and Name
	    	zoneId = doc.getElementsByTagName("zoneid").item(0).getTextContent();
	    	zoneName = doc.getElementsByTagName("zonename").item(0).getTextContent();

	    	//Get zone rooms
	    	NodeList nList = doc.getElementsByTagName("room");

	    	for(int x=0; x<nList.getLength(); x++) {
	    	
	    		Node nNode = nList.item(x);
	    		
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
	    catch (Exception e) {
	    	e.printStackTrace();
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
