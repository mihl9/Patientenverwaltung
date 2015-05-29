package ch.gbssg.app.bla.patient;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.gbssg.app.model.Patient;
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
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();

		if (cmd instanceof CmdShowPatientDialog) {
			CmdShowPatientDialog cmdShowDialog = (CmdShowPatientDialog) messages.poll();
			Stage owner = cmdShowDialog.getParent();				
			Patient patient = cmdShowDialog.getPatient();
			
			showDialog(owner, patient);
		}
	}

	private void showDialog(Stage owner, Patient patient) {
		dialog = new Stage();
		
		dialog.initOwner(owner);
		dialog.setTitle("Patient");
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.centerOnScreen();
		
		view = new PatientView(this);
		view.setData(patient);
		Scene scene = new Scene(view.getContent());
		dialog.setScene(scene);
		
		dialog.showAndWait();
	}
	
	private PatientView view;

	public void closeDialog() {
		dialog.close();
	}

	public void save(Patient patient) {
		List<String> errors = new ArrayList<String>();
		if (!patient.isValid(errors)) {
			view.setError(errors);
			return;
		}
		
		if (patient.getId() == -1) {
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


	
	private Stage dialog;
}
