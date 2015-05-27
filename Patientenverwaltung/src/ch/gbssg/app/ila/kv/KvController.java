package ch.gbssg.app.ila.kv;

import java.time.LocalDate;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Faktura;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.util.command.CmdDoExport;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdShowUi;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

/**
 * 
 * @author Michael Huber
 *
 */
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
	
	public void generateInvoice(Faktura model){
		CmdDoExport cmd = new CmdDoExport("InvoiceTemplate.docx");
		cmd.addDataModel("faktura", model);
		
		//cmd.setExportType(ExportType.PDF);
		sendAgentMessage(new AgentCommand(cmd));
		
	}
	
	public void changeState(Faktura model){
		if(model!=null){
			MedicalHistory medHistory = new MedicalHistory();
			medHistory.setId(model.getId());
			
			CmdFilterEntity<MedicalHistory> getData = new CmdFilterEntity<MedicalHistory>(MedicalHistory.class, medHistory);
			sendAgentMessage(new AgentCommand(getData));
			
			medHistory = getData.getEntities().get(0);
			
			ChoiceDialog<Code> dialog = new ChoiceDialog<>(getAssignedCode(medHistory.getBillState()), this.model.getCodesData());
			dialog.setTitle("Neuer Status");
			dialog.setHeaderText("Bitte wählen Sie den neuen Status aus.");
			dialog.setContentText("Status:");
			
			Optional<Code> result = dialog.showAndWait();
			
			result.ifPresent(choice -> System.out.println(choice.getId()));
			
		}
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
	
	public void loadData(){
		/*Load the Data from the db*/
		/*
		 * Load the Faktura from Database
		 */
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