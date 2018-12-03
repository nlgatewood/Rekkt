package rekkt.engine.characters;

import java.util.ArrayList;
import rekkt.engine.items.*;

/**
 * 
 * @author nlgatewood
 *
 */

public class Character {
	
	private String name;
	private ArrayList<Item> inventory;

	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Character(String name) {
		
		this.name = name;
		inventory = new ArrayList<Item>();
	}
	
	/*---------------------------------------------------------------------
	 * addInventory(Item item) - Add to inventory
	 *---------------------------------------------------------------------*/
	protected void addInventoryItem(Item item) {
	
		inventory.add(item);
	}
	
	/*---------------------------------------------------------------------
	 * getInventorySize() - Get number of items in Inventory
	 *---------------------------------------------------------------------*/
	public int getInventorySize() {
		
		return inventory.size();
	}
	
	/*---------------------------------------------------------------------
	 * getName() - Return Characters Name
	 *---------------------------------------------------------------------*/
	public String getName() {
		
		return name;
	}
}
