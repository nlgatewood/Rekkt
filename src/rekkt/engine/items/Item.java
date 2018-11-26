package rekkt.engine.items;

/**
 * 
 * @author nlgatewood
 *
 */

public class Item {
	
	private String name;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Item(String name) {
		
		this.name = name;
	}
	
	/*---------------------------------------------------------------------
	 * getName() - Return Item's Name
	 *---------------------------------------------------------------------*/
	public String getName() {
		
		return name;
	}
}
