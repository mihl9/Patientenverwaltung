package ch.gbssg.app.ila.kv;

import java.time.LocalDate;

import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Fakturen;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.command.CmdFilterEntity;
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
		Fakturen m1 = new Fakturen();
		m1.setDateFrom(LocalDate.now());
		m1.setBillState(1);
		Fakturen m2 = new Fakturen();
		m2.setDateFrom(LocalDate.now());
		m2.setBillState(3);
		model.getFakturenData().add(m1);
		model.getFakturenData().add(m2);
		view.fillTableData(model.getFakturenData());
		
		/*Load the data from database*/
		//prepare the filter
		Code codeFilter = new Code();
		codeFilter.setCodeTypeId(1);
		//get the data
		CmdFilterEntity<Code> codeFilterCommand = new CmdFilterEntity<Code>(Code.class, codeFilter);
		sendAgentMessage(new AgentCommand(codeFilterCommand));
		
		model.getCodesData().addAll(codeFilterCommand.getEntities());
		
		view.fillCombobox(model.getCodesData());
	}
}