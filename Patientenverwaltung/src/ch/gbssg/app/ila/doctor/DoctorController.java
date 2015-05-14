package ch.gbssg.app.ila.doctor;

import javafx.scene.layout.Pane;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

public class DoctorController extends AgentController {

	@Override
	public boolean setupAgent() {
		model = new DoctorModel();
		view = new DoctorView(this);
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();

		if (cmd instanceof CmdShowUi) {
			CmdShowUi cmdShowUi = (CmdShowUi) messages.poll();
			Pane container = cmdShowUi.getPane();		
			container.getChildren().setAll(view.getContent());
		}
	}

	private DoctorModel model;
	private DoctorView view;
}
