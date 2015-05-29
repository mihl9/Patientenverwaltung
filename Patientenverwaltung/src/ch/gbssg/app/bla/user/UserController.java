package ch.gbssg.app.bla.user;

import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.UserRoll;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdInsertEntity;
import ch.gbssg.app.util.command.CmdShowUserDialog;
import ch.gbssg.app.util.command.CmdUpdateEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

public class UserController extends AgentController {

	private UserModel model;
	private UserView view;
	
	public UserController() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public boolean setupAgent() {
		model = new UserModel();
		view = new UserView(this);
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdShowUserDialog) {
			CmdShowUserDialog cmdShowUserDialog = (CmdShowUserDialog)messages.poll();
			loadCodes();
			//save the user data
			this.model.setModel(cmdShowUserDialog.getUser());
			showDialog(cmdShowUserDialog.getParent(), this.model.getModel());			
		}
	}
	
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
	
	private void loadCodes(){
		this.model.getCodesData().clear();
		/*
		 * Load the Codes from the DB
		 */
		//prepare the filter
		Code codeFilter = new Code();
		codeFilter.setCodeTypeId(1);
		//get the data
		CmdFilterEntity<Code> codeFilterCommand = new CmdFilterEntity<Code>(Code.class, codeFilter);
		sendAgentMessage(new AgentCommand(codeFilterCommand));
		//save the data
		this.model.getCodesData().addAll(codeFilterCommand.getEntities());
		this.view.fillCombobox(this.model.getCodesData());
	}
	
	private void showDialog(Stage owner, User model){
		final Stage dialog = new Stage();

		dialog.setTitle("Benutzer");
		dialog.initOwner(owner);
		dialog.initModality(Modality.WINDOW_MODAL);
	    dialog.centerOnScreen();
	    
	    //set Events
	    this.view.getBtnCancel().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
	    
	    this.view.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				refreshUserData(model);
			    dialog.close();
			    saveUser(model);
			}
		});
	    if(this.model.getContent()==null){
	    	this.model.setContent(new Scene(this.view.getContent()));
	    }
	    
	    dialog.setScene(this.model.getContent());
	    
	    this.view.displayData(model);
	    dialog.showAndWait();
	}
	
	public void refreshUserData(User model){
		model.setFirstname(this.view.getTxtFirstname().getText());
		model.setLastname(this.view.getTxtlastname().getText());
		model.setLoginname(this.view.getTxtUsername().getText());
		if(this.view.getTxtPassword().getText()!="password"){
			try {
				model.setPassword(model.decryptSha1(this.view.getTxtPassword().getText()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.setHourlyWage(Double.parseDouble(this.view.getTxtHourlyWage().getText()));
		model.setRolle(UserRoll.convert(this.view.getCboCodes().getSelectionModel().getSelectedItem().getId()));
		//Currently this setting is fix
		model.setInactive(false);
	}
	
	public void saveUser(User model){
		if(model.getId()==0){
			//Create new Entry
			CmdInsertEntity<User> cmdInsert = new CmdInsertEntity<User>(User.class, model);
			sendAgentMessage(new AgentCommand(cmdInsert));
		}else{
			//Update existing entry
			CmdUpdateEntity<User> cmdUpdate = new CmdUpdateEntity<User>(User.class, model);
			sendAgentMessage(new AgentCommand(cmdUpdate));
		}
	}
}
