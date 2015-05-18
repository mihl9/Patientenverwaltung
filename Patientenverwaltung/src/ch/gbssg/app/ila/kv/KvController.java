package ch.gbssg.app.ila.kv;

import java.time.LocalDate;
import java.util.Date;

import javafx.scene.layout.Pane;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;


public class KvController extends AgentController {
	
	private KvModel model;
	private KvView view;
	
	public KvController() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public boolean setupAgent() {
		model = new KvModel();
		view = new KvView(this);
		
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
			loadTestData();
		}
		
	}
	
	public void loadTestData(){
		/*test*/
		MedicalHistory m1 = new MedicalHistory();
		m1.setDateFrom(LocalDate.now());
		m1.setBillState(1);
	}
}