package ch.gbssg.app.util.command;

import javafx.stage.Stage;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.core.pac.ICommand;
/**
 * Command to call the Edit Dialog MedHistory
 * @author Michael Huber
 * @version 1.0
 */
public class CmdShowMedHistoryDialog implements ICommand  {
	private Stage parent;
	private MedicalHistory medHistory;
	
	public CmdShowMedHistoryDialog(Stage owner, MedicalHistory model) {
		setParent(owner);
		setMedHistory(model);
	}

	public Stage getParent() {
		return parent;
	}

	public void setParent(Stage parent) {
		this.parent = parent;
	}

	/**
	 * @return the model
	 */
	public MedicalHistory getMedHistory() {
		return medHistory;
	}

	/**
	 * @param patient the model which should be set
	 */
	public void setMedHistory(MedicalHistory patient) {
		this.medHistory = patient;
	}
}
