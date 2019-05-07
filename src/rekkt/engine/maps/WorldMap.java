package rekkt.engine.maps;

import java.util.HashMap;

import org.w3c.dom.NodeList;

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
	 * addDefaultZoneItems() - Add Default Zone Items
	 *---------------------------------------------------------------------*/
	public void addDefaultZoneItems(NodeList itemsNodeList) {
		
		for(String zoneName : map.keySet()) {
			
			map.get(zoneName).addDefaultZoneItems(itemsNodeList);
		}
	}

	/*---------------------------------------------------------------------
	 * getZone(String zoneId) - 
	 *---------------------------------------------------------------------*/
	public Zone getZone(String zoneId) {
		
		return map.get(zoneId);
	}
}
