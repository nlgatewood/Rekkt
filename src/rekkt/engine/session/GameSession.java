package rekkt.engine.session;

import rekkt.engine.command.Command;
import rekkt.engine.command.Parser;
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
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public GameSession() {
		
		commandParser = new Parser();
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
		
		//Process Command
		if(commandWord.equals("go")) {
			
			moveRoom(command);
		}
		else if(commandWord.equals("quit")) {
			
			wantToQuit = true;
		}
		
		return wantToQuit;
	}
	
	/*---------------------------------------------------------------------
	 * moveRoom() - Move to the specified room
	 *---------------------------------------------------------------------*/
	private void moveRoom(Command command){
		
		String secondWord = command.getSecondWord();
		
		if(secondWord == null) {
			
			System.out.println("Go Where?");
			return;
		}
		
		System.out.println("GO "+secondWord+"!");
		
	}
}
