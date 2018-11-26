package rekkt.engine.items;

import java.util.ArrayList;

/**
 * 
 * @author nlgatewood
 *
 */

public class Container extends Item {
	
	private int containerSize;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Container(String name, int containerSize) {
		
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
