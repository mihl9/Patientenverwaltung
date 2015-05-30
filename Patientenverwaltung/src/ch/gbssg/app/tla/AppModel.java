package ch.gbssg.app.tla;

import ch.gbssg.app.model.User;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppModel {
	private Stage stage;
	private User user;
	
	public Pane getWindow() {
		return window;
	}

	public void setWindow(Pane window) {
		this.window = window;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return this.user;
	}
	private Pane window;
}
