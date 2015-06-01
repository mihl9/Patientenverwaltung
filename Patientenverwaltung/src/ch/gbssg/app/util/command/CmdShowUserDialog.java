package ch.gbssg.app.util.command;

import ch.gbssg.app.model.User;
import ch.gbssg.core.pac.ICommand;
import javafx.stage.Stage;
/**
 * Command to call the Edit Dialog User
 * @author Michael Huber
 * @version 1.0
 */
public class CmdShowUserDialog implements ICommand  {
	private Stage parent;
	private User user;
	
	public CmdShowUserDialog(Stage parent, User model) {
		setParent(parent);
		setUser(model);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Stage getParent() {
		return parent;
	}

	public void setParent(Stage parent) {
		this.parent = parent;
	}
}
