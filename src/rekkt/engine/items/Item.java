package rekkt.engine.items;

/**
 * 
 * @author nlgatewood
 *
 */

public class Item {
	
	private String name;
	private String description;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Item(String name, String description) {
		
		this.name = name;
		this.description = description;	
	}
	
	/*---------------------------------------------------------------------
	 * getName() - Return Item's Name
	 *---------------------------------------------------------------------*/
	public String getName() {
		
		return name;
	}
	
	/*---------------------------------------------------------------------
	 * getDescription() - Return Item's description
	 *---------------------------------------------------------------------*/
	public String getDescription() {
		
		return description;
	}
}
