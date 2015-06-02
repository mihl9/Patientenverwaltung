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
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.command.CmdCurrentUser;
import ch.gbssg.app.util.command.CmdDoExport;
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
/**
 * Represents the Controller for the Intermediate Level Agent Doctor
 * This agent is the Main Dialog for the User of the type Doctor
 * It handles all basic action which should be performed when an Doctor user loggs on
 * @author Michael Huber
 * @version 1.0
 */
public class DoctorController extends AgentController {
	/**
	 * Constrcutor of the class
	 * Creates alls sub Agent (Bottom level Agents)
	 */
	public DoctorController() {
		patientAgent = AgentFactory.getInstance().requestAgent(PatientController.class);
		addChild(patientAgent);
		
		medHistoryAgent = AgentFactory.getInstance().requestAgent(MedicalHistoryController.class);
		addChild(medHistoryAgent);
	}
	/**
	 * Called when the Agent is initialized
	 */
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
	/**
	 * Opens the Patient Dialog and sends the given model
	 * for later processing
	 * @param model
	 */
	public void showPatientChildWindow(Patient model) {
		CmdGetRootWindow cmdRoot = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(cmdRoot));
		
		if(model!=null){
			CmdShowPatientDialog cmdShowPatientDialog = new CmdShowPatientDialog(cmdRoot.getWindow(), model);
			sendAgentMessage(patientAgent, new AgentCommand(cmdShowPatientDialog));
			
			loadData();
		}
	}
	/**
	 * prompt the user and inactivate the Patient
	 * @param model
	 */
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
	/**
	 * Get the Medical history from the Database based on the passed PatientID
	 * Fills the received data into the Table
	 * @param patientId the ID of the Patient
	 */
	public void showMedicalHistory(int patientId) {
		model.getMedicalHistoryData().clear();
		MedicalHistory filter = new MedicalHistory();
		filter.setPatientId(patientId);
		
		CmdFilterEntity<MedicalHistory> cmdMedicalHistory = new CmdFilterEntity<MedicalHistory>(MedicalHistory.class, filter);
		sendAgentMessage(new AgentCommand(cmdMedicalHistory));
		model.getMedicalHistoryData().addAll(cmdMedicalHistory.getEntities());
		
	}
	/**
	 * Open the Patient Dialog for creating a new Patient
	 * Creates the model and saves the PatientID and the UserID into the model
	 * @param patientId the id of the Patient which should be assigned
	 */
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
	/**
	 * Edit the given MedicalHistory model. Send it to the Bottom Level Agent MedicalHistory
	 * Reloads the Data when finished
	 * @param model
	 */
	public void editMedicalHistory(MedicalHistory model){
		CmdGetRootWindow cmdRoot = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(cmdRoot));
		if(model!=null){
			CmdShowMedHistoryDialog cmdShowDialog = new CmdShowMedHistoryDialog(cmdRoot.getWindow(),model);
			sendAgentMessage(new AgentCommand(cmdShowDialog));
			
			showMedicalHistory(model.getPatientId());
		}
	}
	/**
	 * Filter the Patient Table based on the given criteria
	 * If all params are null or empty the display all data
	 * @param firstname as the name say
	 * @param lastname as the name say
	 * @param birthDate as the name say
	 * @param ahv as the name say
	 */
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
	
	public void exportMedHistory(Patient patient, MedicalHistory medHis){
		User doc = null;
		//get the current User
		CmdCurrentUser cmdUser = new CmdCurrentUser();
		sendAgentMessage(new AgentCommand(cmdUser));
		doc = cmdUser.getUser();
		
		if(doc!=null && patient != null && medHis != null){
			//prepare the Export command
			CmdDoExport cmdExport = new CmdDoExport("MedHistoryTemplate.docx");
			cmdExport.addDataModel("doc", doc);
			cmdExport.addDataModel("patient", patient);
			cmdExport.addDataModel("medHis", medHis);
			sendAgentMessage(new AgentCommand(cmdExport));
		}
	}
	
	private PatientController patientAgent;
	private MedicalHistoryController medHistoryAgent;
	
	private DoctorModel model;
	private DoctorView view;
}
