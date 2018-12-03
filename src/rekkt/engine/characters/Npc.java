package rekkt.engine.characters;

import rekkt.engine.items.Item;

/**
 * 
 * @author nlgatewood
 *
 */

public class Npc extends Character {
	
	private int inventoryLimit;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Npc(String name) {
		
		super(name);
		inventoryLimit = 5;
	}
	
	/*---------------------------------------------------------------------
	 * addInventoryItem() - Add an item to the inventory if there's enough space
	 * 						in the NPC inventory
	 *---------------------------------------------------------------------*/
	@Override
	public void addInventoryItem(Item item) {
		
		if(super.getInventorySize() < inventoryLimit) {
		
			super.addInventoryItem(item);
		}
		else {
			System.out.println("Inventory limit reached.");
		}
	}

}
