package ch.gbssg.app.ila.doctor;

import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DoctorModel {
	private ObservableList<Patient> patientsData;
	
	private ObservableList<MedicalHistory> medicalHistoryData;
	
	public DoctorModel() {
		patientsData = FXCollections.observableArrayList();
		medicalHistoryData = FXCollections.observableArrayList();
	}


	/**
	 * @return the patientsData
	 */
	public ObservableList<Patient> getPatientsData() {
		return patientsData;
	}	
	
	/**
	 * @return the medicalHistoryData
	 */
	public ObservableList<MedicalHistory> getMedicalHistoryData() {
		return medicalHistoryData;
	}	
}
