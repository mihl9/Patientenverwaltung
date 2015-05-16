package ch.gbssg.app.ila.doctor;

import java.time.LocalDate;

import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Patient;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

public class DoctorController extends AgentController {

	@Override
	public boolean setupAgent() {
		model = new DoctorModel();
		view = new DoctorView(this, model);
		
		Patient p1 = new Patient();
		p1.setFirstname("Sandro");
		p1.setLastname("Pedrett");
		p1.setBirthday(LocalDate.now());
		
		Patient p2 = new Patient();
		p2.setFirstname("Aline");
		p2.setLastname("Schweizer");
		p2.setPlace("Hemberg");
		
		model.getPatientsData().add(p1);
		model.getPatientsData().add(p2);
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
