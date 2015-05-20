package ch.gbssg.app.ila.admin;

import javafx.scene.layout.Pane;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

public class AdminController extends AgentController {
	private AdminModel model;
	private AdminView view;
	
	public AdminController() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public boolean setupAgent() {
		model = new AdminModel();
		view = new AdminView(this);
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdShowUi) {
			CmdShowUi cmdShowUi = (CmdShowUi)messages.poll();
			Pane container = cmdShowUi.getPane();
			container.getChildren().clear();
			container.getChildren().add(view.getContent());
		}
	}

}
