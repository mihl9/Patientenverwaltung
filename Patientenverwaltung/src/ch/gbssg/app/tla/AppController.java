package ch.gbssg.app.tla;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;

/**
 * TODO
 * @author pedrett.sandro
 *
 */
public class AppController extends AgentController {

	@Override
	public boolean setupAgent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Show the first window of application.
	 * @param primaryStage the primary stage for this application, into which the application scene can be set. 
	 */
	public void showStartWindow(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../../resource/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
