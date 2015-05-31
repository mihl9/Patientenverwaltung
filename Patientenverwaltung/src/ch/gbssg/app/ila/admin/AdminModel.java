package ch.gbssg.app.ila.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;

/**
 * Represents the Model for the Intermediate Level agent Admin
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
public class AdminModel {
	private ObservableList<User> userData;
	private ObservableList<Code> codesData;
	/**
	 * Constructor
	 * create the instances of the ObservableList objects
	 */
	public AdminModel(){
		userData = FXCollections.observableArrayList();
		codesData = FXCollections.observableArrayList();
	}
	/**
	 * get the UserData list
	 * @return list of user data
	 */
	public ObservableList<User> getUserData(){
		return this.userData;
	}
	
	/**
	 * get the Codes Data list
	 * @return get a list of Codes
	 */
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}
}
