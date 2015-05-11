package ch.gbssg.app.tla;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ch.gbssg.app.ila.admin.AdminController;
import ch.gbssg.app.ila.database.DatabaseController;
import ch.gbssg.app.ila.doctor.DoctorController;
import ch.gbssg.app.ila.export.ExportController;
import ch.gbssg.app.ila.kv.KvController;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.UserRoll;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;
import ch.gbssg.core.pac.ICommand;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	
	/*
	 * (non-Javadoc)
	 * @see ch.gbssg.core.pac.AgentController#setupAgent()
	 */
	@Override
	public boolean setupAgent() {
		model = new AppModel();
		view = new AppView(this);
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ch.gbssg.core.pac.AgentController#processMessage(ch.gbssg.core.pac.AgentCommand)
	 */
	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdShowUi) {
			CmdShowUi cmdShowUi = (CmdShowUi)messages.poll();
			cmdShowUi.setPane(view.getContainerPane());
		}
	}

	/**
	 * set the stage for root window ui in javafx
	 * 
	 * IMPORTANT: stage must be set before run a pane! window will be not shown after that!
	 * @param stage top level element of javafx container
	 */
	public void initializeStage(Stage stage) {
		model.setStage(stage);
		
		try {
			// get root window
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RootWindow.fxml"));
			loader.setController(view);
			model.setWindow((Pane)loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * show login pane with default stage values. 
	 * 
	 * show window!
	 */
	public void showLoginPane() {
		if (model.getStage() == null) {
			return;
		}
		
		Scene scene = new Scene(model.getWindow());
		model.getStage().setScene(scene);
		
		model.getStage().setHeight(370);
		model.getStage().setWidth(500);
		model.getStage().setResizable(false);
		
		addConatinerPane(getClass().getResource("Login.fxml"));
		
		model.getStage().show();
	}
	
	/**
	 * check if user login valid and redirect to other page. otherwise if 
	 * false, then display an error code on screen.
	 * @param user user which will be logged in
	 */
	public void LoginValid(User user) {
		List<String> errors = new ArrayList<String>();
		
		// check errors in model
		if (!user.isValid(errors)) {
			view.setError(errors);
			return;
		}
		
		// TODO Login (optimize)
		// 1) check if user exist
		// 1.1) if user not exist, call view.setError()
		// 1.2) if user exist, send user object to database and check it. --> get user roll
		user.setRolle(UserRoll.DOCTOR);
		
		// redirect to correct frame
		String filename = "";
		
		// set ui filename
		switch (user.getRolle()) {
			case ADMIN:
				filename = "Admin.fxml";
				break;
			case KV:
				filename = "Kv.fxml";
				break;
			case DOCTOR:
				filename = "Doctor.fxml";
				break;
			default:
				break;
		}
		
		// set new stage values
		model.getStage().setHeight(768);
		model.getStage().setWidth(1024);
		model.getStage().setResizable(true);
		
		addConatinerPane(getClass().getResource(filename));
	}

	/**
	 * add a pane to root window container.
	 * 
	 * @param url to fxml ui file
	 */
	private void addConatinerPane(URL url) {
		if (model.getStage() == null) {
			return;
		}
		
		Pane windowContainer = view.getContainerPane();
		Pane pane = null;
				
		try {
			// get root window
			FXMLLoader loader = new FXMLLoader(url);
			loader.setController(view);
			pane = (Pane)loader.load();
			
			windowContainer.getChildren().add(pane);
		} catch (Exception e) {
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
