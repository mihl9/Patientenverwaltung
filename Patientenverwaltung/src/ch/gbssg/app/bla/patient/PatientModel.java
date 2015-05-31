package ch.gbssg.app.bla.patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Patient;
/**
 * Represents the Model for the Patient agent
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
public class PatientModel {
	private Patient model;
	private ObservableList<Code> codesData;
	private Scene content;
	/**
	 * The Contrsuctor of this class
	 */
	public PatientModel(){
		codesData = FXCollections.observableArrayList();
		content = null;
	}
	
	/**
	 * Returns the Data model
	 * @return data model MedicalHistory
	 */
	public Patient getModel() {
		return model;
	}
	/**
	 * Saves the received data model
	 * @param model
	 */
	public void setModel(Patient model) {
		this.model = model;
	}
	/**
	 * get the Codes data list which is used in this dialog
	 * @return
	 */
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}	
	/**
	 * Get the Content of the GUI
	 * @param content
	 */
	public Scene getContent() {
		return content;
	}

	/**
	 * saves the Content of the GUI
	 * @return
	 */
	public void setContent(Scene content) {
		this.content = content;
	}
}
