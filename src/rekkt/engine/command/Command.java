package rekkt.engine.command;

import java.util.ArrayList;

/**
 * 
 * @author nlgatewood
 *
 */

public class Command {

	private String commandWord;
	private ArrayList<String> nextCommandWords;
	private boolean isValid;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Command(String commandWord, ArrayList<String> nextCommandWords) {
		
		this.commandWord = commandWord;
		this.nextCommandWords = nextCommandWords;
	}
	
	/*---------------------------------------------------------------------
	 * getCommandWord() - Return commandWord
	 *---------------------------------------------------------------------*/
	public String getCommandWord() {
		
		return commandWord;
	}
	
	/*---------------------------------------------------------------------
	 * getNextCommandWords() - Return commandWord
	 *---------------------------------------------------------------------*/
	public ArrayList<String> getNextCommandWords() {
		
		return nextCommandWords;
	}
	
	/*---------------------------------------------------------------------
	 * getNextCommandWord() - Return commandWord
	 *---------------------------------------------------------------------*/
	public String getNextCommandWord(int pos) {
		
		String nextCommand = null;

		if(nextCommandWords.size() >= pos+1) {
			
			nextCommand = nextCommandWords.get(pos);
		}
		
		return nextCommand;
	}
	
	/*---------------------------------------------------------------------
	 * getIsValid() - Is this a valid command
	 *---------------------------------------------------------------------*/
	public boolean getIsValid() {
		
		return isValid;
	}
	
	/*---------------------------------------------------------------------
	 * setIsValid() - Sets the 'isValid' class variable
	 *---------------------------------------------------------------------*/
	public void setIsValid(boolean validFlag) {
		
		isValid = validFlag;
	}
}
