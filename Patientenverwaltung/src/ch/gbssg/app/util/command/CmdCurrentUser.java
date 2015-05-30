package ch.gbssg.app.util.command;

import ch.gbssg.app.model.User;
import ch.gbssg.core.pac.ICommand;

/**
 * 
 * @author Michael Huber
 *
 * @param <T>
 */
public class CmdCurrentUser implements ICommand {
	
	private User user;
	
	public CmdCurrentUser() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
