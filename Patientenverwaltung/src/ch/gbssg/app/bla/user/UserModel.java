package ch.gbssg.app.bla.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;
/**
 * Represents the Model for the user agent
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
public class UserModel {
	private User model;
	private ObservableList<Code> codesData;
	private Scene content;
	/**
	 * Constructor
	 */
	public UserModel(){
		codesData = FXCollections.observableArrayList();
		content = null;
	}
	/**
	 * Returns the Data model
	 * @return data model MedicalHistory
	 */
	public User getModel() {
		return model;
	}
	/**
	 * Saves the received data model
	 * @param model
	 */
	public void setModel(User model) {
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
