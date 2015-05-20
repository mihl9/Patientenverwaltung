package ch.gbssg.app;

	
import javafx.application.Application;
import javafx.stage.Stage;
import ch.gbssg.app.tla.AppController;
import ch.gbssg.core.pac.AgentFactory;

/**
 * entry point for application.
 * @author pedrett.sandro
 * @version 1.0
 */
public class App extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		AppController appAgent = AgentFactory.getInstance().requestAgent(AppController.class);
		appAgent.initializeStage(primaryStage);
		appAgent.showLoginPane();
	}
}