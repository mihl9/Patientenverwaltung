package ch.gbssg.app.util.command;

import javafx.stage.Stage;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.core.pac.ICommand;

public class CmdShowMedHistoryDialog implements ICommand  {
	private Stage parent;
	private MedicalHistory patient;
	
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
	 * @return the patient
	 */
	public MedicalHistory getMedHistory() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setMedHistory(MedicalHistory patient) {
		this.patient = patient;
	}
}
