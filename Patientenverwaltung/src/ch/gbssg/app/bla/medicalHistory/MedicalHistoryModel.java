package ch.gbssg.app.bla.medicalHistory;

import javafx.scene.Scene;
import ch.gbssg.app.model.MedicalHistory;
/**
 * Represents the Model for the Medical history agent
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
public class MedicalHistoryModel {
	private MedicalHistory model;
	private Scene content;
	/**
	 * The Contrsuctor of this class
	 */
	public MedicalHistoryModel(){
		content = null;
	}
	
	/**
	 * Returns the Data model
	 * @return data model MedicalHistory
	 */
	public MedicalHistory getModel() {
		return model;
	}
	
	/**
	 * Saves the received data model
	 * @param model
	 */
	public void setModel(MedicalHistory model) {
		this.model = model;
	}
	
	/**
	 * saves the Content of the GUI
	 * @return
	 */
	public Scene getContent() {
		return content;
	}

	/**
	 * Get the Content of the GUI
	 * @param content
	 */
	public void setContent(Scene content) {
		this.content = content;
	}
}
