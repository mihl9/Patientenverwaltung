package ch.gbssg.app.bla.medicalHistory;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.util.command.CmdInsertEntity;
import ch.gbssg.app.util.command.CmdShowMedHistoryDialog;
import ch.gbssg.app.util.command.CmdUpdateEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;
/**
 * Represents the Controller for the Bottom Level Agent MedicalHistory
 * This agent is for editing/adding Medical History Entries
 * @author Michael Huber
 * @version 1.0
 */
public class MedicalHistoryController extends AgentController {
	/**
	 * Method which is called every time the Agent is initialized
	 */
	@Override
	public boolean setupAgent() {
		view = new MedicalHistoryView(this);
		model = new MedicalHistoryModel();
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();

		if (cmd instanceof CmdShowMedHistoryDialog) {
			CmdShowMedHistoryDialog cmdShowDialog = (CmdShowMedHistoryDialog) messages.poll();
			Stage owner = cmdShowDialog.getParent();				
			MedicalHistory patient = cmdShowDialog.getMedHistory();
			
			this.model.setModel(patient);
			showDialog(owner, this.model.getModel());
		}
	}
	
	private void showDialog(Stage owner, MedicalHistory patient) {
		dialog = new Stage();
		
		dialog.initOwner(owner);
		dialog.setTitle("Krankheitsverlauf");
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.centerOnScreen();
		
		view = new MedicalHistoryView(this);
		
		view.setData(patient);
		Scene scene = new Scene(view.getContent());
		dialog.setScene(scene);
		
		dialog.showAndWait();
	}
	
	private MedicalHistoryView view;
	private MedicalHistoryModel model;
	
	/**
	 * Close the dialog without saving the data
	 */
	public void closeDialog() {
		dialog.close();
	}

	/**
	 * Write all changes into the Model
	 * @param model the model which should be written
	 */
	public void refreshMedHistoryData(MedicalHistory model){
		model.setDateFrom(this.view.getDateFrom().getValue());
		model.setBillDueTo(this.view.getDateFrom().getValue().plusDays(30));
		model.setHour(Double.parseDouble(this.view.getHour().getText()));
		model.setSymptoms(this.view.getSymptoms().getText());
		model.setDiagnostic(this.view.getDiagnostic().getText());
		model.setNotes(this.view.getNotes().getText());
	}
	
	/**
	 * Saves all changes and update it to the Database
	 */
	public void save() {	
		refreshMedHistoryData(this.model.getModel());
		MedicalHistory medHist = this.model.getModel();
		
		List<String> errors = new ArrayList<String>();
		if (!medHist.isValid(errors)) {
			view.setError(errors);
			return;
		}
		
		if (medHist.getId() == 0) {
			//Create new Entry
			medHist.setBillState(4);
			CmdInsertEntity<MedicalHistory> cmdInsert = new CmdInsertEntity<MedicalHistory>(MedicalHistory.class, medHist);
			sendAgentMessage(new AgentCommand(cmdInsert));
		} else {
			//Update existing entry
			CmdUpdateEntity<MedicalHistory> cmdUpdate = new CmdUpdateEntity<MedicalHistory>(MedicalHistory.class, medHist);
			sendAgentMessage(new AgentCommand(cmdUpdate));
		}
		dialog.close();
	}
	
	private Stage dialog;
}
