package rekkt.engine.characters;

import java.util.ArrayList;
import rekkt.engine.items.*;

/**
 * 
 * @author nlgatewood
 *
 */

public class Player extends Character {
	
	private ArrayList<ContainerItem> containerItems;
	private int inventoryLimit;
	private int maxBagNum;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Player(String name) {
		
		super(name);
		containerItems = new ArrayList<ContainerItem>();
		inventoryLimit = 4;
		maxBagNum = 4;
	}
	
	/*---------------------------------------------------------------------
	 * addInventoryItem() - Add an item to the inventory if there's enough space
	 * 						in the players inventory and bags
	 *---------------------------------------------------------------------*/
	@Override
	public void addInventoryItem(Item item) {
		
		if(super.getInventorySize() < (inventoryLimit + getTotalContainerSize())) {
		
			super.addInventoryItem(item);
		}
		else {
			System.out.println("Can't add a "+item.getName()+". Inventory limit reached.");
		}
	}
	
	/*---------------------------------------------------------------------
	 * addContainerItem() - Add a Container(bag) to the players contain inventory
	 *---------------------------------------------------------------------*/
	public void addContainerItem(ContainerItem item) {
		
		//Allowed 5 containers per bag
		if(containerItems.size() < maxBagNum) {
			
			containerItems.add(item);
		}
		else {
			System.out.println("Maximum bag limit reached. Only "+maxBagNum+" bags allowed.");
		}
	}
	
	/*---------------------------------------------------------------------
	 * getTotalContainerSize() - Returns the total size of all containers
	 *---------------------------------------------------------------------*/
	public int getTotalContainerSize() {
		
		int totalSize = 0;
		
		for(ContainerItem bag : containerItems) {
			
			totalSize+=bag.getContainerSize();
		}
		
		return totalSize;
	}
}
