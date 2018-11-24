package rekkt.engine.maps;

import java.util.HashMap;

/**
 * 
 * @author nlgatewood
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
			System.out.println(">"+direction+"<"+this.exits.get(direction));
		}
	}
	
	/*---------------------------------------------------------------------
	 * getExit(String direction)
	 *---------------------------------------------------------------------*/
	public String getExitRoom(String direction) {
		
		return exits.get(direction);
	}

	/*---------------------------------------------------------------------
	 * getDescription()
	 *---------------------------------------------------------------------*/
	public String getDescription() {
		
		return description;
	}
}
