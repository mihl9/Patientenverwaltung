package ch.gbssg.app.bla.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;

public class UserModel {
	private User model;
	private ObservableList<Code> codesData;
	private Scene content;
	
	


	public UserModel(){
		codesData = FXCollections.observableArrayList();
		content = null;
	}
	

	public User getModel() {
		return model;
	}

	public void setModel(User model) {
		this.model = model;
	}
	
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}	
	
	public Scene getContent() {
		return content;
	}


	public void setContent(Scene content) {
		this.content = content;
	}
}
