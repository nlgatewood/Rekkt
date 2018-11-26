package rekkt.engine.characters;

import java.awt.Container;
import java.util.ArrayList;

import rekkt.engine.items.Item;

/**
 * 
 * @author nlgatewood
 *
 */

public class Player extends Character {
	
	private ArrayList<Container> containerItems;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Player(String name) {
		
		super(name);
		containerItems = new ArrayList<Container>();
	}
	
	/*---------------------------------------------------------------------
	 * addContainerItem() - Add an item to the container Items
	 *---------------------------------------------------------------------*/
	public void addInventoryItem(Item item) {
		
	}
	
	/*---------------------------------------------------------------------
	 * addContainerItem() - Add an item to the container Items
	 *---------------------------------------------------------------------*/
	public void addContainerItem(Container item) {
		
		//Allowed 5 containers per bag
		if(containerItems.size() < 5) {
			
			containerItems.add(item);
		}
		else {
			System.out.println("Maximum bag limit reached. Only 5 bags allowed.");
		}
	}
	
	/*---------------------------------------------------------------------
	 * addInventory() - Add an item to their inventory
	 *---------------------------------------------------------------------*/
	public void addContainerItems(Container item) {
		
		containerItems.add(item);
	}
}
