package ch.gbssg.app.ila.doctor;

import java.time.LocalDate;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import ch.gbssg.app.bla.medicalHistory.MedicalHistoryController;
import ch.gbssg.app.bla.patient.PatientController;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.model.Patient;
import ch.gbssg.app.util.command.CmdCurrentUser;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdGetRootWindow;
import ch.gbssg.app.util.command.CmdShowMedHistoryDialog;
import ch.gbssg.app.util.command.CmdShowPatientDialog;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.app.util.command.CmdUpdateEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;
import ch.gbssg.core.pac.ICommand;

public class DoctorController extends AgentController {

	public DoctorController() {
		patientAgent = AgentFactory.getInstance().requestAgent(PatientController.class);
		addChild(patientAgent);
		
		medHistoryAgent = AgentFactory.getInstance().requestAgent(MedicalHistoryController.class);
		addChild(medHistoryAgent);
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
		model.getPatientsData().clear();
		model.getMedicalHistoryData().clear();
		
		Patient filter = new Patient();
		filter.setInactive(false);
		CmdFilterEntity<Patient> cmdPatient = new CmdFilterEntity<Patient>(Patient.class, filter);
		sendAgentMessage(new AgentCommand(cmdPatient));
		
		model.getPatientsData().addAll(cmdPatient.getEntities());
		FilterTable(null,null,null,null);
	}
	
	public void showPatientChildWindow(Patient model) {
		CmdGetRootWindow cmdRoot = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(cmdRoot));
		
		if(model!=null){
			CmdShowPatientDialog cmdShowPatientDialog = new CmdShowPatientDialog(cmdRoot.getWindow(), model);
			sendAgentMessage(patientAgent, new AgentCommand(cmdShowPatientDialog));
			
			loadData();
		}
	}
	
	public void deletePatient(Patient model){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		alert.setTitle("Sind Sie sicher?");
		alert.setHeaderText("Der Patient wird gelöscht und kann nicht mehr wiederhergestellt werden. Dieser vorgang kann nicht rückgängig gemacht werden.");
		alert.setContentText("Sind Sie damit Einverstanden?");
		
		Optional<ButtonType> result = alert.showAndWait();		
		if(result.get() == ButtonType.OK && model!=null){
			model.setInactive(true);
			//CmdDeleteEntity<Patient> cmdDelete = new CmdDeleteEntity<Patient>(Patient.class, model);
			CmdUpdateEntity<Patient> cmdUpdate = new CmdUpdateEntity<Patient>(Patient.class, model);
			sendAgentMessage(new AgentCommand(cmdUpdate));
			
			loadData();
		}
	}
	
	public void showMedicalHistory(int patientId) {
		model.getMedicalHistoryData().clear();
		MedicalHistory filter = new MedicalHistory();
		filter.setPatientId(patientId);
		
		CmdFilterEntity<MedicalHistory> cmdMedicalHistory = new CmdFilterEntity<MedicalHistory>(MedicalHistory.class, filter);
		sendAgentMessage(new AgentCommand(cmdMedicalHistory));
		model.getMedicalHistoryData().addAll(cmdMedicalHistory.getEntities());
		
	}

	public void createMedicalHistory(int patientId){
		CmdCurrentUser cmdCurUser = new CmdCurrentUser();
		sendAgentMessage(new AgentCommand(cmdCurUser));
		
		CmdGetRootWindow cmdRoot = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(cmdRoot));
		
		MedicalHistory medHist = new MedicalHistory();
		medHist.setId(0);
		medHist.setPatientId(patientId);
		medHist.setUserId(cmdCurUser.getUser().getId());
		medHist.setBillState(4);
		
		CmdShowMedHistoryDialog cmdShowDialog = new CmdShowMedHistoryDialog(cmdRoot.getWindow(),medHist);
		sendAgentMessage(new AgentCommand(cmdShowDialog));
		
		showMedicalHistory(patientId);
	}
	
	public void editMedicalHistory(MedicalHistory model){
		CmdGetRootWindow cmdRoot = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(cmdRoot));
		if(model!=null){
			CmdShowMedHistoryDialog cmdShowDialog = new CmdShowMedHistoryDialog(cmdRoot.getWindow(),model);
			sendAgentMessage(new AgentCommand(cmdShowDialog));
			
			showMedicalHistory(model.getPatientId());
		}
	}
	
	public void FilterTable(String firstname, String lastname, LocalDate birthDate, String ahv){
		model.getPatientsFilteredData().clear();
		if((firstname!=null && !firstname.isEmpty()) || (lastname!=null && !lastname.isEmpty()) || (ahv!=null && !ahv.isEmpty()) || birthDate!=null){
			for (Patient patient : model.getPatientsData()) {
				boolean visible=true;
				if(firstname!=null && !firstname.isEmpty()){
					visible = visible && patient.getFirstname().toLowerCase().contains(firstname.toLowerCase());
				}
				if(visible && birthDate!=null){
					visible = visible && patient.getBirthday().isEqual(birthDate);
				}
				if(visible && lastname!=null && !lastname.isEmpty()){
					visible = visible && patient.getLastname().toLowerCase().contains(lastname.toLowerCase());
				}
				if(visible && ahv!=null && !ahv.isEmpty()){
					visible = visible && patient.getAhv().toLowerCase().contains(ahv.toLowerCase());
				}
				if(visible==true){
					model.getPatientsFilteredData().add(patient);
				}
			}
		}else{
			model.getPatientsFilteredData().addAll(model.getPatientsData());
		}
		
	}
	
	private PatientController patientAgent;
	private MedicalHistoryController medHistoryAgent;
	
	private DoctorModel model;
	private DoctorView view;
}
