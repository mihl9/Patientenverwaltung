package ch.gbssg.app.tla;

import ch.gbssg.app.model.User;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * Represents the Model for the Top Level agent App
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
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
	/**
	 * set the current authenticated User
	 * @return
	 */
	public void setUser(User user){
		this.user = user;
	}
	/**
	 * get the current authenticated User
	 * @return
	 */
	public User getUser(){
		return this.user;
	}
	private Pane window;
}
