package ch.gbssg.app.tla;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ch.gbssg.app.ila.admin.AdminController;
import ch.gbssg.app.ila.database.DatabaseController;
import ch.gbssg.app.ila.doctor.DoctorController;
import ch.gbssg.app.ila.export.ExportController;
import ch.gbssg.app.ila.kv.KvController;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;

/**
 * Top-Level-Agent controller. 
 * handles all agents and is on top of hierarchy level
 * 
 * @author pedrett.sandro
 * @version 1.0
 */
public class AppController extends AgentController {

	/**
	 * Constructor for tla controller.
	 */
	public AppController() {
		// create agent hierarchy
		AdminController adminAgent = AgentFactory.getInstance().requestAgent(AdminController.class);
		adminAgent.setParent(this);
		addChild(adminAgent);
		
		DoctorController doctorAgent = AgentFactory.getInstance().requestAgent(DoctorController.class);
		doctorAgent.setParent(this);
		addChild(doctorAgent);
		
		KvController kvAgent = AgentFactory.getInstance().requestAgent(KvController.class);
		kvAgent.setParent(this);
		addChild(kvAgent);
		
		DatabaseController databaseController = AgentFactory.getInstance().requestAgent(DatabaseController.class);
		databaseController.setParent(this);
		addChild(databaseController);
	
		ExportController exportController = AgentFactory.getInstance().requestAgent(ExportController.class);
		exportController.setParent(this);
		addChild(exportController);
	}
	
	@Override
	public boolean setupAgent() {
		model = new AppModel();
		view = new AppView(this);
		
		return true;
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

	/**
	 * represent a model for this agent
	 */
	private AppModel model;
	
	/**
	 * represent a view for this agent.
	 */
	private AppView view;
}
