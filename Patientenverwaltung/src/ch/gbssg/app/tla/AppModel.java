package ch.gbssg.app.tla;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppModel {
	private Stage stage;
	
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

	private Pane window;
}
