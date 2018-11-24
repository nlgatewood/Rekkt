package rekkt.engine.command;

import java.util.Scanner;
/**
 * 
 * @author nlgatewood
 *
 */

public class Parser {
	
	private Scanner reader;
	private CommandValidator commandValidator;

	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Parser() {
		
		reader = new Scanner(System.in);
		commandValidator = new CommandValidator();
	}
	
	/*---------------------------------------------------------------------
	 * createCommand() - Prompt user for command, create command object
	 *---------------------------------------------------------------------*/
	public Command createCommand() {
		
		Command command = null;
		String inputLine;
		String commandWord = null;
		String secondWord = null;
		
		System.out.print("> ");
		
        inputLine = reader.nextLine().toLowerCase();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        
        if(tokenizer.hasNext()) {

            commandWord = tokenizer.next();
            
            if(tokenizer.hasNext()) {
                
                secondWord = tokenizer.next();
            }
        }

    	command = new Command(commandWord, secondWord);
    	
        //Validate  Command
        if(commandValidator.validateCommand(commandWord)) {
      	
        	command.setIsValid(true);
        }
        else {
        	command.setIsValid(false);
        }
               
        tokenizer.close();
		
		return command;
	}
}
