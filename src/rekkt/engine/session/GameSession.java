package rekkt.engine.session;

import rekkt.engine.command.Command;
import rekkt.engine.command.Parser;
import rekkt.engine.items.ContainerItem;
import rekkt.engine.items.Item;
import rekkt.engine.items.MiscItem;
import rekkt.engine.maps.Room;
import rekkt.engine.maps.WorldMap;
import rekkt.engine.characters.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 
 * @author nlgatewood
 *
 */

public class GameSession {
	
	private Parser commandParser;
	private Player player;
	private WorldMap mapO;
	private String currentZone;
	private Room currentRoom;
	private NodeList itemsNodeList;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public GameSession() {
		
		commandParser = new Parser();
		currentZone = "1";
	}
	
	/*---------------------------------------------------------------------
	 * startGame() - Begin game
	 *---------------------------------------------------------------------*/
	public void startGame() {
		
		Scanner reader = new Scanner(System.in);
		String inputLine = null;
		
		System.out.print("Welcome to Rekkt!\n"+
						 "----------------------\n"+
						 "1 - New Game\n"+
						 "2 - Quit\n"+
						 "> ");
		
		inputLine = reader.nextLine();
		 
		if(inputLine.equals("1")) {
			
			System.out.print("Please enter your name:");
			inputLine = reader.nextLine();
			
			player = new Player(inputLine);
			
			loadGame(); 
		}
		else if(inputLine.equals("2")) {
			
			System.out.println("Thanks for playing!");
		}
	}
	
	/*---------------------------------------------------------------------
	 * loadItemList() - 
	 *---------------------------------------------------------------------*/
	private NodeList loadItemList() {
		
		NodeList itemsList = null;
		
	    try {
	    	File fXmlFile = new File("src/rekkt/lib/items/items.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    			
	    	doc.getDocumentElement().normalize();
	    	
	    	itemsList = doc.getElementsByTagName("item");
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return itemsList;
	}
	
	/*---------------------------------------------------------------------
	 * playGame() - play game
	 *---------------------------------------------------------------------*/
	private void loadGame() {
		
		boolean wantToQuit = false;
		
		mapO = new WorldMap();			//Create World Map
		itemsNodeList = loadItemList(); // Create Item NodeList
		
		//Load Items into Map
		mapO.addDefaultZoneItems(itemsNodeList);
		
		currentRoom = mapO.getZone(currentZone).getRoom("1001");
		

		currentRoom.printRoomDescription();
		
		do {
			Command command = commandParser.createCommand();
			wantToQuit = processCommand(command);
			
		} while(wantToQuit == false);
		
		System.out.println("Thank you for playing.  Good bye.");
	}
	
	/*---------------------------------------------------------------------
	 * processCommand() - process the entered command
	 *---------------------------------------------------------------------*/
	private boolean processCommand(Command command) {
		
		boolean wantToQuit = false;
		
		boolean commandIsValid = command.getIsValid();
		String commandWord = command.getCommandWord();
		
		
		//ensure command is valid
		if(!commandIsValid) {
			
			System.out.println("'"+commandWord+"' command not recognized");
		}
		
		//Process "go" Command
		if(commandWord.equals("go")) {
			
			moveCmd(command);
		}
		else if(commandWord.equals("inspect")) {
			
			inspectCmd(command);
		}
		else if(commandWord.equals("take")) {
			
			takeCmd(command);
		}
		else if(commandWord.equals("drop")) {
			
			dropCmd(command);
		}	
		else if(commandWord.equals("help")) {
			
			helpCmd(command);
		}
		else if(commandWord.equals("quit")) {
			
			wantToQuit = true;
		}
		
		return wantToQuit;
	}
	
	/*---------------------------------------------------------------------
	 * moveCmd() - Process the 'go' command. Move to the specified room
	 *---------------------------------------------------------------------*/
	private void moveCmd(Command command){
		
		ArrayList<String> nextCommandWords = command.getNextCommandWords();
		
		if(nextCommandWords.size() == 0) {
			
			System.out.println("Go where?");
			return;
		}
		
		//run through the list of directions
		for(String nextCommand : nextCommandWords) {
			
			//Get new Room - Move to new room if found
			String newRoom = currentRoom.getExitRoom(nextCommand);
			
			if(newRoom != null) {
				
				currentRoom = mapO.getZone(currentZone).getRoom(newRoom);
				currentRoom.printRoomDescription();
			}
			else {
				System.out.println("Can't go '"+nextCommand+"'");
			}
			
		}
	}

	/*---------------------------------------------------------------------
	 * inspectCmd() - Process the 'inspect' command. Inspect area, inventory,
	 * 				  or character
	 *---------------------------------------------------------------------*/
	private void inspectCmd(Command command) {
		
		String secondWord = command.getNextCommandWord(0);
		
		if(secondWord == null) {
			
			System.out.println("Inspect what?");
			return;
		}
		
		//Inspect the area
		if(secondWord.equals("room")) {
			
			String thirdCommandWord = command.getNextCommandWord(1);

			//If no item specified, inspect the room
			if(thirdCommandWord != null) {
				
				String inspectItem = currentRoom.getRoomItemDescription(thirdCommandWord);
				
				if(inspectItem != null) {
					
					System.out.println(inspectItem);
				}
				else {
					System.out.println("Can't find a "+thirdCommandWord+" to inspect.");
				}
			}
			// Inspect the room
			else {
				currentRoom.printRoomDescription();
			}
		}
		//Inspect the players inventory
		else if(secondWord.equals("inventory")) {
			
			player.printInventoryItems();
		}
		//Else, idk...
		else {
			System.out.println("Can't find a "+secondWord+" to inspect.");
		}
	}
	
	/*---------------------------------------------------------------------
	 * takeCmd() - Take item from environment
	 *---------------------------------------------------------------------*/
	private void takeCmd(Command command) {
		
		String secondWord = command.getNextCommandWord(0);
		
		if(secondWord == null) {
			
			System.out.println("Take what?");
			return;
		}
		
		//Take an item from your current room
		if(secondWord.equals("room")) {
			
			String thirdCommandWord = command.getNextCommandWord(1);
			
			if(thirdCommandWord != null) {
				
				//Take item from environment
				Item takeItem = currentRoom.getRoomItem(thirdCommandWord);
					
				if(takeItem != null) {
						
					player.addInventoryItem(takeItem);
					System.out.println(takeItem.getName()+" added to your inventory");
				}
				else {
					System.out.println("Can't find a "+thirdCommandWord+"to take");
				}
			}
			//If item is not specified
			else {
				System.out.println("Take what from room?");
			}
		}
		else {
			System.out.println("Take what from where?");
		}
	}
	
	/*---------------------------------------------------------------------
	 * dropCmd() - Drop item from your inventory into the current room
	 *---------------------------------------------------------------------*/
	private void dropCmd(Command command) {
		
		String secondWord = command.getNextCommandWord(0);
		
		if(secondWord == null) {
			
			System.out.println("Drop what?");
			return;
		}
		
		Item item = player.getInventoryItem(secondWord);
		
		if(item != null) {
			
			currentRoom.addRoomItem(item);
		}
		else {
			System.out.println(secondWord+" not found in player inventory");
		}
	}
	
	/*---------------------------------------------------------------------
	 * helpCmd() - Process the 'help' command. Display Help screen
	 *---------------------------------------------------------------------*/
	private void helpCmd(Command command){
		
		System.out.println("\nHelp: Controls\n"+
                "----------------------------------------------------------------------------------------------------------------\n"+
                "go <north|south|east|west>                                   Move across the map\n"+
                "quit                                                         Quit the game\n"+
                "help                                                         Display the help screen\n"+
                "inspect <area|inventory|[item name]|[character name]>        Inspect details about an area or item\n"+
                "use [item name]                                              Use an item.  Must be in possession of that item\n"+
                "take [item name]                                             Take an item from the area and put it in your inventory\n"+
                "drop [item name]                                             Drop and item from your inventory into the area\n"+
                "talk [character name]                                        Talk to a character in an area\n"+
                "drink [item name]                                            Drink the specified item\n"+
                "----------------------------------------------------------------------------------------------------------------\n");
	}
}
