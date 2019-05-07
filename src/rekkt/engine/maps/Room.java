package rekkt.engine.maps;

import java.util.ArrayList;
import java.util.HashMap;

import rekkt.engine.items.Item;

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
	private ArrayList<Item> roomItems;

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
		this.roomItems = new ArrayList<Item>();
		
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
		
		printRoomItems();
		printRoomExits();
	}
	
	/*---------------------------------------------------------------------
	 * getExit(String direction)
	 *---------------------------------------------------------------------*/
	public String getExitRoom(String direction) {
		
		return exits.get(direction);
	}

	/*---------------------------------------------------------------------
	 * getRoomItem(String itemName) - Retrieve a room item by name
	 *---------------------------------------------------------------------*/
	public Item getRoomItem(String itemName) {
		
		for(Item thisItem : roomItems) {
			if(thisItem.getName().toUpperCase().equals(itemName.toUpperCase())) {

				roomItems.remove(thisItem);
				return thisItem;
			}
		}
		
		return null;
	}
	
	/*---------------------------------------------------------------------
	 * getRoomItemDescription(String itemName) - Retrieve a room item desc
	 *---------------------------------------------------------------------*/
	public String getRoomItemDescription(String itemName) {
		
		for(Item thisItem : roomItems) {

			if(thisItem.getName().toUpperCase().equals(itemName.toUpperCase())) {

				return thisItem.getDescription();
			}
		}
		
		return null;
	}

	/*---------------------------------------------------------------------
	 * addRoomItem(Item item)
	 *---------------------------------------------------------------------*/
	public void addRoomItem(Item item) {
		
		roomItems.add(item);
	}

	/*---------------------------------------------------------------------
	 * printRoomItems() - Print all room items
	 *---------------------------------------------------------------------*/
	public void printRoomItems() {
		
		if(!roomItems.isEmpty()) {
			
			System.out.println("**Room Items**");
		
			for(Item roomItem : roomItems) {
			
				System.out.println(roomItem.getName());
			}
		}
	}

	/*---------------------------------------------------------------------
	 * printRoomExits() - Print all room exits
	 *---------------------------------------------------------------------*/
	public void printRoomExits() {
		
		System.out.print("\nExits:");
		
		if(!exits.isEmpty()) {
			
			ArrayList<String> exitList = new ArrayList<String>();
			
			for(String exit : exits.keySet()) {
				
				exitList.add(exit);
			}
			
			System.out.println(String.join(",", exitList));
		}
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
