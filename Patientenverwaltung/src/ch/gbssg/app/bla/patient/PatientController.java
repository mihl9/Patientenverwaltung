package ch.gbssg.app.bla.patient;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Patient;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdInsertEntity;
import ch.gbssg.app.util.command.CmdShowPatientDialog;
import ch.gbssg.app.util.command.CmdUpdateEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

public class PatientController extends AgentController {

	@Override
	public boolean setupAgent() {
		view = new PatientView(this);
		model = new PatientModel();
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();

		if (cmd instanceof CmdShowPatientDialog) {
			CmdShowPatientDialog cmdShowDialog = (CmdShowPatientDialog) messages.poll();
			Stage owner = cmdShowDialog.getParent();				
			Patient patient = cmdShowDialog.getPatient();
			
			this.model.setModel(patient);
			showDialog(owner, this.model.getModel());
		}
	}

	private void loadCodes(){
		this.model.getCodesData().clear();
		/*
		 * Load the Codes from the DB
		 */
		//prepare the filter
		Code codeFilter = new Code();
		codeFilter.setCodeTypeId(3);
		//get the data
		CmdFilterEntity<Code> codeFilterCommand = new CmdFilterEntity<Code>(Code.class, codeFilter);
		sendAgentMessage(new AgentCommand(codeFilterCommand));
		//save the data
		this.model.getCodesData().addAll(codeFilterCommand.getEntities());
		this.view.fillCombobox(this.model.getCodesData());
	}
	
	private void showDialog(Stage owner, Patient patient) {
		dialog = new Stage();
		
		dialog.initOwner(owner);
		dialog.setTitle("Patient");
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.centerOnScreen();
		
		view = new PatientView(this);
		loadCodes();
		view.setData(patient);
		Scene scene = new Scene(view.getContent());
		dialog.setScene(scene);
		
		dialog.showAndWait();
	}
	
	private PatientView view;
	private PatientModel model;
	
	public void closeDialog() {
		dialog.close();
	}

	public void refreshPatientData(Patient model){
		model.setFirstname(this.view.getFirstname().getText());
		model.setLastname(this.view.getLastname().getText());
		model.setBirthday(this.view.getBirthday().getValue());
		model.setAhv(this.view.getAhv().getText());
		model.setPlz(this.view.getPlz().getText());
		model.setPlace(this.view.getPlace().getText());
		model.setAddress(this.view.getAddress().getText());
		model.setGenderCode(this.view.getCboGender().getValue().getId());
	}
	
	public void save() {	
		refreshPatientData(this.model.getModel());
		Patient patient = this.model.getModel();
		
		List<String> errors = new ArrayList<String>();
		if (!patient.isValid(errors)) {
			view.setError(errors);
			return;
		}
		
		if (patient.getId() == 0) {
			//Create new Entry
			CmdInsertEntity<Patient> cmdInsert = new CmdInsertEntity<Patient>(Patient.class, patient);
			sendAgentMessage(new AgentCommand(cmdInsert));
		} else {
			//Update existing entry
			CmdUpdateEntity<Patient> cmdUpdate = new CmdUpdateEntity<Patient>(Patient.class, patient);
			sendAgentMessage(new AgentCommand(cmdUpdate));
		}
		dialog.close();
	}

	public Code getAssignedCode(int id){
		Code result = null;
		for (Code code : model.getCodesData()) {
			if(code.getId()==id){
				result = code;
				break;
			}
		}
		
		return result;
	}
	
	private Stage dialog;
}
