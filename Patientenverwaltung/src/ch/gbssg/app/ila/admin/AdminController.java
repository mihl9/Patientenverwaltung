package ch.gbssg.app.ila.admin;

import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

public class AdminController extends AgentController {
	private AdminModel model;
	private AdminView view;
	
	public AdminController() {
		// TODO Auto-generated constructor stub
		
	}
	
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
	
	public void loadData(){
		CmdFilterEntity<User> cmdUsers = new CmdFilterEntity<User>(User.class, null);
		sendAgentMessage(new AgentCommand(cmdUsers));
		
		this.model.getUserData().addAll(cmdUsers.getEntities());
		
		Code filter = new Code();
		filter.setCodeTypeId(1);
		CmdFilterEntity<Code> cmdCodes = new CmdFilterEntity<Code>(Code.class, filter);
		sendAgentMessage(new AgentCommand(cmdCodes));
		
		this.model.getCodesData().addAll(cmdCodes.getEntities());
		
		this.view.fillTableData(this.model.getUserData());
	}
}
