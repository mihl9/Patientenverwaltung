package ch.gbssg.app.util.command;

import javafx.stage.Stage;
import ch.gbssg.app.model.Patient;
import ch.gbssg.core.pac.ICommand;

public class CmdShowPatientDialog implements ICommand  {
	private Stage parent;
	private Patient patient;
	
	public CmdShowPatientDialog(Stage owner, Patient model) {
		setParent(owner);
		setPatient(model);
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
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
