package ch.gbssg.app.ila.doctor;

import ch.gbssg.app.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DoctorModel {
	private ObservableList<Patient> patientsData;
	
	
	public DoctorModel() {
		patientsData = FXCollections.observableArrayList();
	}


	/**
	 * @return the patientsData
	 */
	public ObservableList<Patient> getPatientsData() {
		return patientsData;
	}	
}
