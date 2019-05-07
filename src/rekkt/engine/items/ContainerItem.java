package rekkt.engine.items;

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
	public ContainerItem(String name, String description, int containerSize) {
		
		super(name, description);
		this.containerSize = containerSize;
	}
	
	/*---------------------------------------------------------------------
	 * getContainerSize() - Get the size of the container
	 *---------------------------------------------------------------------*/
	public int getContainerSize() {
		
		return containerSize;
	}

}
