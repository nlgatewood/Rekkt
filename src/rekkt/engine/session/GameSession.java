package rekkt.engine.session;

import rekkt.engine.command.Command;
import rekkt.engine.command.Parser;
import rekkt.engine.items.ContainerItem;
import rekkt.engine.items.MiscItem;
import rekkt.engine.maps.Room;
import rekkt.engine.maps.WorldMap;
import rekkt.engine.characters.*;

import java.util.Scanner;

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
			player.addContainerItem(new ContainerItem("Bag",5));
			player.addInventoryItem(new MiscItem("trash1"));
			player.addInventoryItem(new MiscItem("trash2"));
			player.addInventoryItem(new MiscItem("trash3"));
			player.addInventoryItem(new MiscItem("trash4"));
			player.addInventoryItem(new MiscItem("trash5"));
			
			
			playGame(); 
		}
		else if(inputLine.equals("2")) {
			
			System.out.println("Thanks for playing!");
		}
	}
	
	/*---------------------------------------------------------------------
	 * playGame() - play game
	 *---------------------------------------------------------------------*/
	private void playGame() {
		
		boolean wantToQuit = false;
		
		//Create World - Set Room
		mapO = new WorldMap();
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
		
		String secondWord = command.getSecondWord();
		
		if(secondWord == null) {
			
			System.out.println("Go where?");
			return;
		}
		
		//Get new Room - Move to new room if found
		String newRoom = currentRoom.getExitRoom(secondWord);
		
		if(newRoom != null) {
			
			currentRoom = mapO.getZone(currentZone).getRoom(newRoom);
			currentRoom.printRoomDescription();
		}
		else {
			System.out.println("Can't go '"+secondWord+"'");
		}
	}

	/*---------------------------------------------------------------------
	 * inspectCmd() - Process the 'inspect' command. Inspect area, inventory,
	 * 				  or character
	 *---------------------------------------------------------------------*/
	private void inspectCmd(Command command) {
		
		String secondWord = command.getSecondWord();
		
		if(secondWord == null) {
			
			System.out.println("Inspect what?");
			return;
		}
		
		if(secondWord.equals("area")) {
			
			currentRoom.printRoomDescription();
		}
		
	}
	
	/*---------------------------------------------------------------------
	 * takeCmd() - Take item from environment
	 *---------------------------------------------------------------------*/
	private void takeCmd(Command command) {
		
		String secondWord = command.getSecondWord();
		
		if(secondWord == null) {
			
			System.out.println("Take what?");
			return;
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
