package ch.gbssg.app.ila.doctor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ch.gbssg.app.bla.patient.PatientController;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.model.Patient;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;
import ch.gbssg.core.pac.ICommand;

public class DoctorController extends AgentController {

	public DoctorController() {
		patientAgent = AgentFactory.getInstance().requestAgent(PatientController.class);
	}
	
	@Override
	public boolean setupAgent() {
		model = new DoctorModel();
		view = new DoctorView(this, model);
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();

		if (cmd instanceof CmdShowUi) {
			CmdShowUi cmdShowUi = (CmdShowUi) messages.poll();
			Pane container = cmdShowUi.getPane();		
			container.getChildren().setAll(view.getContent());
			
			loadData();
		}
	}

	private void loadData() {
		CmdFilterEntity<Patient> cmdPatient = new CmdFilterEntity<Patient>(Patient.class, null);
		sendAgentMessage(new AgentCommand(cmdPatient));
		
		model.getPatientsData().addAll(cmdPatient.getEntities());
	}
	
	public void showChildWindow() {
		//sendAgentMessage(patientAgent, new AgentCommand(new CmdShowUi(pane)));
	}
	
	public void showMedicalHistory(int patientId) {
		model.getMedicalHistoryData().clear();
		MedicalHistory filter = new MedicalHistory();
		filter.setPatientId(patientId);
		
		CmdFilterEntity<MedicalHistory> cmdMedicalHistory = new CmdFilterEntity<MedicalHistory>(MedicalHistory.class, filter);
		sendAgentMessage(new AgentCommand(cmdMedicalHistory));
		model.getMedicalHistoryData().addAll(cmdMedicalHistory.getEntities());
	}

	private PatientController patientAgent;
	
	private DoctorModel model;
	private DoctorView view;
}
