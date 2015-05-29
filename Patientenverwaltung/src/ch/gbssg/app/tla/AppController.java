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
import ch.gbssg.app.util.command.CmdCloseApplication;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdGetRootWindow;
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
 * Top-Level-Agent controller. handles all agents and is on top of hierarchy
 * level
 * 
 * @author pedrett.sandro
 * @version 1.0
 */
public class AppController extends AgentController {

	private AdminController adminAgent;
	private DoctorController doctorAgent;
	private KvController kvAgent;

	private DatabaseController databaseController;
	private ExportController exportController;

	/**
	 * Constructor for tla controller.
	 */
	public AppController() {
		// create agent hierarchy
		adminAgent = AgentFactory.getInstance().requestAgent(
				AdminController.class);
		addChild(adminAgent);

		doctorAgent = AgentFactory.getInstance().requestAgent(
				DoctorController.class);
		addChild(doctorAgent);

		kvAgent = AgentFactory.getInstance().requestAgent(KvController.class);
		addChild(kvAgent);

		databaseController = AgentFactory.getInstance().requestAgent(
				DatabaseController.class);
		addChild(databaseController);

		exportController = AgentFactory.getInstance().requestAgent(
				ExportController.class);
		addChild(exportController);
	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * 
	 * @see ch.gbssg.core.pac.AgentController#processMessage(ch.gbssg.core.pac.
	 * AgentCommand)
	 */
	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if(cmd instanceof CmdGetRootWindow){
			CmdGetRootWindow getRootWindow = (CmdGetRootWindow) cmd;
			getRootWindow.setWindow(model.getStage());
		}else if(cmd instanceof CmdCloseApplication){
			this.model.getStage().close();
		}
	}

	/**
	 * set the stage for root window ui in javafx
	 * 
	 * IMPORTANT: stage must be set before run a pane! window will be not shown
	 * after that!
	 * 
	 * @param stage
	 *            top level element of javafx container
	 */
	public void initializeStage(Stage stage) {
		model.setStage(stage);
	}

	/**
	 * show login pane with default stage values.
	 * 
	 * show window!
	 */
	public void showLoginPane() {	
		setWindowLayout(getClass().getResource("Login.fxml"));
		
		model.getStage().setHeight(370);
		model.getStage().setWidth(500);
		model.getStage().setResizable(false);
		
		model.getStage().centerOnScreen();
		model.getStage().show();
	}

	public void closeApp(){
		this.model.getStage().close();
	}
	
	public void logout(){
		model = new AppModel();
		view = new AppView(this);
		
		showLoginPane();
	}
	/**
	 * check if user login valid and redirect to other page. otherwise if false,
	 * then display an error code on screen.
	 * 
	 * @param user
	 *            user which will be logged in
	 * @throws Exception 
	 */
	public void LoginValid(User user) {
		List<String> errors = new ArrayList<String>();

		// check errors in model
		if (!user.isValid(errors)) {
			view.setErrors(errors);
			return;
		}

		CmdFilterEntity<User> userFilterCommand = new CmdFilterEntity<User>(User.class, user);
		sendAgentMessage(databaseController, new AgentCommand(userFilterCommand));
			
		// check if user exist and set it
		if (userFilterCommand.getEntities().size() != 1) {
			view.setError("Benutzername/Passwort ist falsch");
			return;
		} else if (userFilterCommand.getEntities().size() == 1) {
			user = userFilterCommand.getEntities().get(0);
		}
		
		// redirect to correct frame
		
		
		// set new stage values
		setWindowLayout(getClass().getResource("RootWindow.fxml"));
		
		model.getStage().setResizable(true);
		if(model.getStage().isMaximized()==true){
			model.getStage().setMaximized(false);
		}
		model.getStage().setMaximized(true);
		// set ui filename
		switch (user.getRolle()) {
		case ADMIN:
			sendAgentMessage(adminAgent,
					new AgentCommand(new CmdShowUi(view.getContent())));
			break;
		case KV:
			sendAgentMessage(kvAgent,
					new AgentCommand(new CmdShowUi(view.getContent())));
			break;
		case DOCTOR:
			sendAgentMessage(doctorAgent,
					new AgentCommand(new CmdShowUi(view.getContent())));
			break;
		default:
			System.out.println("Don't found a Roll for this User");
		}	
		
	}

	/**
	 * set the windows layout. <br>
	 * 
	 * exit method if stage don't found.
	 * @param url
	 *            to fxml ui file
	 */
	private void setWindowLayout(URL url) {
		if (model.getStage() == null) {
			return;
		}

		try {
			// get root window
			FXMLLoader loader = new FXMLLoader(url);
			loader.setController(view);
			model.setWindow((Pane) loader.load());
			
			Scene scene = new Scene(model.getWindow());
			scene.getStylesheets().add(getClass().getResource("../../resource/application.css").toExternalForm());
			model.getStage().setScene(scene);
		} catch (IOException e) {
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
