package ch.gbssg.app.ila.kv;

import java.time.LocalDate;

import org.junit.internal.runners.model.EachTestNotifier;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Faktura;
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
	
	public void FilterTable(Code state, LocalDate dateFrom, LocalDate dateTo){
		model.getFakturenFilteredData().clear();
		for (Faktura faktura : model.getFakturenData()) {
			boolean visible=true;
			if(state!=null){
				visible = visible && faktura.getBillState()==state.getId();
			}
			if(visible && dateFrom!=null){
				visible = visible && faktura.getDateFrom().isAfter(dateFrom);
			}
			if(visible && dateTo!=null){
				visible = visible && faktura.getBillDueTo().isBefore(dateTo);
			}
			if(visible==true){
				model.getFakturenFilteredData().add(faktura);
			}
		}
	}
	
	public void loadTestData(){
		/*test*/
		/*
		Faktura m1 = new Faktura();
		m1.setDateFrom(LocalDate.now());
		m1.setFirstname("Hallo");
		m1.setBillState(4);
		m1.setVisible(false);
		
		Faktura m2 = new Faktura();
		m2.setDateFrom(LocalDate.now());
		m2.setFirstname("du");
		m2.setBillState(5);
		m2.setVisible(true);
		
		Faktura m3 = new Faktura();
		m3.setDateFrom(LocalDate.now());
		m3.setFirstname("lappen");
		m3.setBillState(6);
		m3.setVisible(true);
		model.getFakturenData().add(m1);
		model.getFakturenData().add(m2);
		model.getFakturenData().add(m3);
		*/
		/*Load the Data from the db*/
		CmdFilterEntity<Faktura> fakturaRs = new CmdFilterEntity<Faktura>(Faktura.class, null);
		sendAgentMessage(new AgentCommand(fakturaRs));
		
		model.getFakturenData().addAll(fakturaRs.getEntities());
		FilterTable(null, null, null);
		/*Load the data from database*/
		//prepare the filter
		Code codeFilter = new Code();
		codeFilter.setCodeTypeId(2);
		//get the data
		CmdFilterEntity<Code> codeFilterCommand = new CmdFilterEntity<Code>(Code.class, codeFilter);
		sendAgentMessage(new AgentCommand(codeFilterCommand));
		
		model.getCodesData().addAll(codeFilterCommand.getEntities());
		
		/*Connect the object to the datasource*/
		view.fillCombobox(model.getCodesData());
		view.fillTableData(model.getFakturenFilteredData());
	}
}