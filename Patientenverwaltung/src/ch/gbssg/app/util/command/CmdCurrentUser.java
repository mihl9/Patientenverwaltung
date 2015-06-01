package ch.gbssg.app.util.command;

import ch.gbssg.app.model.User;
import ch.gbssg.core.pac.ICommand;

/**
 * Command for requesting the current Authenticated User from the Top level Agent 
 * @author Michael Huber
 * @version 1.0
 */
public class CmdCurrentUser implements ICommand {
	
	private User user;
	
	public CmdCurrentUser() {
		
	}
	/**
	 * gets the current user
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * set the current user
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
