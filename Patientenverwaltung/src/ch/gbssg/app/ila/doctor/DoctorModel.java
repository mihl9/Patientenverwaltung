package ch.gbssg.app.ila.doctor;

import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Represents the Model for the Intermediate Level agent Doctor
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
public class DoctorModel {
	private ObservableList<Patient> patientsData;
	private ObservableList<Patient> patientsFilteredData;
	
	private ObservableList<MedicalHistory> medicalHistoryData;
	/**
	 * Constructor
	 */
	public DoctorModel() {
		patientsData = FXCollections.observableArrayList();
		medicalHistoryData = FXCollections.observableArrayList();
		patientsFilteredData = FXCollections.observableArrayList();
	}

	/**
	 * @return the raw patientsData
	 */
	public ObservableList<Patient> getPatientsData() {
		return patientsData;
	}	
	
	/**
	 * @return the Filtered patientsData
	 */
	public ObservableList<Patient> getPatientsFilteredData() {
		return patientsFilteredData;
	}	
	
	/**
	 * @return the medicalHistoryData
	 */
	public ObservableList<MedicalHistory> getMedicalHistoryData() {
		return medicalHistoryData;
	}	
}
