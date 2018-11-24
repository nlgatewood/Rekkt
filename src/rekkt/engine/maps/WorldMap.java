package rekkt.engine.maps;

import java.util.HashMap;

/**
 * 
 * @author nlgatewood
 *
 */

public class WorldMap {
	
	private HashMap<String,Zone> map;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public WorldMap() {
		
		map = new HashMap<String,Zone>();
		createWorld();
	}
	
	/*---------------------------------------------------------------------
	 * createWorld() - 
	 *---------------------------------------------------------------------*/
	private void createWorld() {
		
		Zone zoneO = new Zone("zone1");
		
		map.put(zoneO.getId(), zoneO);
	}

	/*---------------------------------------------------------------------
	 * getZone(String zoneId) - 
	 *---------------------------------------------------------------------*/
	public Zone getZone(String zoneId) {
		
		return map.get(zoneId);
	}
}
