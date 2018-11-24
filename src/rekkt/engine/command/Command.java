package rekkt.engine.command;

/**
 * 
 * @author nlgatewood
 *
 */

public class Command {

	private String commandWord;
	private String secondWord;
	private boolean isValid;
	
	/*---------------------------------------------------------------------
	 * CONSTRUCTOR
	 *---------------------------------------------------------------------*/
	public Command(String commandWord, String secondWord) {
		
		this.commandWord = commandWord;
		this.secondWord = secondWord;
	}
	
	/*---------------------------------------------------------------------
	 * getCommandWord() - Return commandWord
	 *---------------------------------------------------------------------*/
	public String getCommandWord() {
		
		return commandWord;
	}
	
	/*---------------------------------------------------------------------
	 * getsecondWord() - Return commandWord
	 *---------------------------------------------------------------------*/
	public String getSecondWord() {
		
		return secondWord;
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
