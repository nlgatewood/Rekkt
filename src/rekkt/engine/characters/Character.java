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
	 * getInventoryItem(String itemName) - get item from player's inventory
	 *---------------------------------------------------------------------*/
	public Item getInventoryItem(String itemName) {
	
		for(Item item : inventory) {
			
			if(item.getName().toUpperCase().equals(itemName.toUpperCase())) {
				
				inventory.remove(item);
				return item;
			}
		}
		
		return null;
	}
	
	/*---------------------------------------------------------------------
	 * getInventoryItemDescription(String itemName) - get item from player's inventory
	 *---------------------------------------------------------------------*/
	public String getInventoryItemDescription(String itemName) {
	
		for(Item item : inventory) {
			
			if(item.getName().toUpperCase().equals(itemName.toUpperCase())) {
				
				return item.getDescription();
			}
		}
		
		return null;
	}

	/*---------------------------------------------------------------------
	 * printInventoryItems() - Print a list of inventory items
	 *---------------------------------------------------------------------*/
	public void printInventoryItems() {
		
		System.out.println("**************Inventory Items**************");
		
		for(Item item : inventory) {
			
			System.out.println(item.getName());
		}
		
		System.out.println("*******************************************");
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
