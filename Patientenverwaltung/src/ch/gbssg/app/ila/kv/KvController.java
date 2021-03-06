package ch.gbssg.app.ila.kv;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Faktura;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.util.command.CmdDoExport;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.app.util.command.CmdUpdateEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

/**
 * Represents the Controller for the Intermediate Level Agent KV
 * This agent is the Main Dialog for the User of the type KV
 * It handles all basic action which should be performed
 * @author Michael Huber
 * @version 1.0
 */
public class KvController extends AgentController {
	
	private KvModel model;
	private KvView view;
	/**
	 * Constructor
	 */
	public KvController() {
		
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
	 * Check the Code list and return the Code Model object based on the given Description
	 * @param desc the Code Description to compare which should be returned
	 * @return the code model object
	 */
	public Code getAssignedCode(String desc){
		Code result = null;
		for (Code code : model.getCodesData()) {
			if(code.getDescription()==desc){
				result = code;
				break;
			}
		}
		
		return result;
	}
	/**
	 * Generate the Invoice Export based on the given data model
	 * @param model the data model which should be exported
	 */
	public void generateInvoice(Faktura model){
		CmdDoExport cmd = new CmdDoExport("InvoiceTemplate.docx");
		cmd.addDataModel("faktura", model);
		
		//cmd.setExportType(ExportType.PDF);
		sendAgentMessage(new AgentCommand(cmd));
		
		//change the state of the data
		MedicalHistory medHistory = new MedicalHistory();
		medHistory.setId(model.getId());
		
		CmdFilterEntity<MedicalHistory> getData = new CmdFilterEntity<MedicalHistory>(MedicalHistory.class, medHistory);
		sendAgentMessage(new AgentCommand(getData));
		
		medHistory = getData.getEntities().get(0);
		//check if should be changed
		if(medHistory.getBillState()==4){	
			medHistory.setBillState(5);
			CmdUpdateEntity<MedicalHistory> cmdUpdate = new CmdUpdateEntity<MedicalHistory>(MedicalHistory.class, medHistory);
			sendAgentMessage(new AgentCommand(cmdUpdate));	
		}
		
		loadData();
		
	}
	/**
	 * Change the state of the given Faktura model.
	 * Displays a Dialog.
	 * @param model
	 */
	public void changeState(Faktura model){
		if(model!=null){
			ArrayList<String> selective = new ArrayList<String>();
			MedicalHistory medHistory = new MedicalHistory();
			medHistory.setId(model.getId());
			
			CmdFilterEntity<MedicalHistory> getData = new CmdFilterEntity<MedicalHistory>(MedicalHistory.class, medHistory);
			sendAgentMessage(new AgentCommand(getData));
			
			medHistory = getData.getEntities().get(0);
			
			for (Code code : this.model.getCodesData()) {
				selective.add(code.getDescription());
			}
			
			ChoiceDialog<String> dialog = new ChoiceDialog<>(getAssignedCode(medHistory.getBillState()).getDescription(), selective );
			dialog.setTitle("Neuer Status");
			dialog.setHeaderText("Bitte w�hlen Sie den neuen Status aus.");
			dialog.setContentText("Status:");
			
			Optional<String> result = dialog.showAndWait();
			
			if(result.isPresent()){
				medHistory.setBillState(getAssignedCode(result.get()).getId());
			}
			
			CmdUpdateEntity<MedicalHistory> cmdUpdate = new CmdUpdateEntity<MedicalHistory>(MedicalHistory.class, medHistory);
			sendAgentMessage(new AgentCommand(cmdUpdate));
			
			loadData();
		}
	}
	/**
	 * Filter the Faktura table based on the given criterias
	 * @param state as named
	 * @param dateFrom as named
	 * @param dateTo as named
	 */
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
	/**
	 * Load the data from the Database and save it into the model
	 */
	public void loadData(){
		/*Load the Data from the db*/
		/*
		 * Load the Faktura from Database
		 */
		this.model.getCodesData().clear();
		this.model.getFakturenData().clear();
		
		CmdFilterEntity<Faktura> fakturaRs = new CmdFilterEntity<Faktura>(Faktura.class, null);
		sendAgentMessage(new AgentCommand(fakturaRs));
		//save the Data into the model
		model.getFakturenData().addAll(fakturaRs.getEntities());
		//reset the search Filter
		FilterTable(null, null, null);
		
		/*
		 * Load the Codes from the DB
		 */
		//prepare the filter
		Code codeFilter = new Code();
		codeFilter.setCodeTypeId(2);
		//get the data
		CmdFilterEntity<Code> codeFilterCommand = new CmdFilterEntity<Code>(Code.class, codeFilter);
		sendAgentMessage(new AgentCommand(codeFilterCommand));
		//save the data
		model.getCodesData().addAll(codeFilterCommand.getEntities());
		
		/*Connect the object to the data source*/
		view.fillCombobox(model.getCodesData());
		view.fillTableData(model.getFakturenFilteredData());
	}
}