package ch.gbssg.app.ila.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;


public class AdminModel {
	private ObservableList<User> userData;
	private ObservableList<Code> codesData;
	
	public AdminModel(){
		userData = FXCollections.observableArrayList();
		codesData = FXCollections.observableArrayList();
	}
	
	public ObservableList<User> getUserData(){
		return this.userData;
	}
	
	
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}
}
