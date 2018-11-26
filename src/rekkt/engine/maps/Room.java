package rekkt.engine.maps;

import java.util.HashMap;

/**
 * 
 * @author nlgatewood
 * @Description Instance of a room
 *
 */

public class Room {

	private String id;
	private String name;
	private String description;
	private String xCoord;
	private String yCoord;
	private HashMap<String,String> exits;

	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Room(String id, String xCoord, String yCoord, String name, String description, HashMap<String,String> exits) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.exits = new HashMap<String,String>();
		
		//Map the HashMap
		for(String direction : exits.keySet()) {
			
			this.exits.put(direction, exits.get(direction));
		}
	}
	
	/*---------------------------------------------------------------------
	 * getExit(String direction)
	 *---------------------------------------------------------------------*/
	public void printRoomDescription() {
		
		System.out.println("\n*--------------------------------------------------------\n"+
						   "* "+name+" ["+xCoord+"-"+yCoord+"]\n"+
						   "*--------------------------------------------------------\n"+
						   " "+description+"\n");
	}
	
	/*---------------------------------------------------------------------
	 * getExit(String direction)
	 *---------------------------------------------------------------------*/
	public String getExitRoom(String direction) {
		
		return exits.get(direction);
	}

	/*---------------------------------------------------------------------
	 * getId() - Return Room Id
	 *---------------------------------------------------------------------*/
	public String getId() {
		
		return id;
	}
	
	/*---------------------------------------------------------------------
	 * getName() - Return Room name
	 *---------------------------------------------------------------------*/
	public String getName() {
		
		return name;
	}

	/*---------------------------------------------------------------------
	 * getDescription() - Return Room's description
	 *---------------------------------------------------------------------*/
	public String getDescription() {
		
		return description;
	}
	
	/*---------------------------------------------------------------------
	 * getXCoord() - Return Room's X-Coordinate
	 *---------------------------------------------------------------------*/
	public String getXCoord() {
		
		return xCoord;
	}
	
	/*---------------------------------------------------------------------
	 * getYCoord() - Return Room's Y-Coordinate
	 *---------------------------------------------------------------------*/
	public String getYCoord() {
		
		return yCoord;
	}
}
