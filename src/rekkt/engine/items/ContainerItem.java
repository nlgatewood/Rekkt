package rekkt.engine.items;

import java.util.ArrayList;

/**
 * 
 * @author nlgatewood
 *
 */

public class ContainerItem extends Item {
	
	private int containerSize;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public ContainerItem(String name, int containerSize) {
		
		super(name);
		this.containerSize = containerSize;
	}
	
	/*---------------------------------------------------------------------
	 * getContainerSize() - Get the size of the container
	 *---------------------------------------------------------------------*/
	public int getContainerSize() {
		
		return containerSize;
	}

}
