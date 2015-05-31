package ch.gbssg.app.ila.admin;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import ch.gbssg.app.bla.user.UserController;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdGetRootWindow;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.app.util.command.CmdShowUserDialog;
import ch.gbssg.app.util.command.CmdUpdateEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;
import ch.gbssg.core.pac.ICommand;
/**
 * Represents the Controller for the Intermediate Level Agent Admin
 * This agent is the Main Dialog for the User of the type Admin
 * It handles all basic action which should be performed
 * @author Michael Huber
 * @version 1.0
 */
public class AdminController extends AgentController {
	private UserController userAgent;
	
	private AdminModel model;
	private AdminView view;
	/**
	 * Constrcutor of the Class
	 */
	public AdminController() {
		userAgent = AgentFactory.getInstance().requestAgent(UserController.class);
		userAgent.setParent(this);
		addChild(userAgent);		
	}
	/**
	 * Method which is called every time the Agent is initialized
	 */
	@Override
	public boolean setupAgent() {
		model = new AdminModel();
		view = new AdminView(this);
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdShowUi) {
			CmdShowUi cmdShowUi = (CmdShowUi)messages.poll();
			Pane container = cmdShowUi.getPane();
			container.getChildren().clear();
			container.getChildren().add(view.getContent());
			loadData();
		}
	}
	/**
	 * Check the Code list and return the Code Model object based on the given ID
	 * @param id the Code ID which should be returned
	 * @return the code model object
	 */
	public Code getAssignedCode(int id){
		Code result = null;
		for (Code code : model.getCodesData()) {
			if(code.getId()==id){
				result = code;
				break;
			}
		}
		
		return result;
	}
	/**
	 * Loads the Data of this formular.
	 * Make a direct Query from the Database and sets the standard Filter for the table
	 */
	public void loadData(){
		this.model.getCodesData().clear();
		this.model.getUserData().clear();
		
		User userFilter = new User();
		userFilter.setInactive(false);
		
		CmdFilterEntity<User> cmdUsers = new CmdFilterEntity<User>(User.class, userFilter);
		sendAgentMessage(new AgentCommand(cmdUsers));
		
		this.model.getUserData().addAll(cmdUsers.getEntities());
		
		Code filter = new Code();
		filter.setCodeTypeId(1);
		CmdFilterEntity<Code> cmdCodes = new CmdFilterEntity<Code>(Code.class, filter);
		sendAgentMessage(new AgentCommand(cmdCodes));
		
		this.model.getCodesData().addAll(cmdCodes.getEntities());
		
		this.view.fillTableData(this.model.getUserData());
	}
	/**
	 * Edit the given user. Sends a command to the Bottom Level agent User, for editing
	 * @param model the model which should be edited
	 */
	public void editUser(User model){
		CmdGetRootWindow cmdRoot = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(cmdRoot));
		
		CmdShowUserDialog cmdShowUser = new CmdShowUserDialog(cmdRoot.getWindow(), model);
		sendAgentMessage(new AgentCommand(cmdShowUser));
		
		loadData();
	}
	/**
	 * Create a new user.
	 * Create a empty User model and sends it to the Bottom level Agent User, for creating this object
	 */
	public void addUser(){
		CmdGetRootWindow cmdRoot = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(cmdRoot));
		
		User newUser = new User();
		newUser.setId(0);
		newUser.setInactive(false);
		
		CmdShowUserDialog cmdShowUser = new CmdShowUserDialog(cmdRoot.getWindow(), newUser);
		sendAgentMessage(new AgentCommand(cmdShowUser));
		
		loadData();
	}
	/**
	 * Prompt the user and set the given model inactive on the database
	 * @param model model which should be deleted
	 */
	public void delUser(User model){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		alert.setTitle("Sind Sie sicher?");
		alert.setHeaderText("Der Benutzer wird Deaktiviert und kann nicht mehr benutzt werden. Dieser vorgang kann nicht rückgängig gemacht werden.");
		alert.setContentText("Sind Sie damit Einverstanden?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
			model.setInactive(true);
			CmdUpdateEntity<User> cmdUpdate = new CmdUpdateEntity<User>(User.class, model);
			sendAgentMessage(new AgentCommand(cmdUpdate));
			
			loadData();
		}else{
			
		}
	}
}
