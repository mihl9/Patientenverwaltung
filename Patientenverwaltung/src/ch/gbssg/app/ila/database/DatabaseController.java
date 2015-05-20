package ch.gbssg.app.ila.database;

import java.util.List;

import ch.gbssg.app.model.User;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

/**
 * TODO
 * @author pedrett.sandro
 *
 */
public class DatabaseController extends AgentController {

	@Override
	public boolean setupAgent() {
		model = new DatabaseModel();
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdFilterEntity) {
			CmdFilterEntity<?> filter = (CmdFilterEntity<?>) cmd;
			
			if (filter.getType() == User.class) {
				List<?> matches = model.getFilteredUser((User) filter.getFilterEntity());
				
				filter.setEntities(matches);
			}
		}
		
	}

	private DatabaseModel model;
}
